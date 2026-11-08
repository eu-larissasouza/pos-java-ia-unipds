package benchmarkio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("benchmark.txt");

        // =========================================================================
        // 1. JAVA IO (Clássico - Desde o Java 1.0)
        // Abordagem baseada em streams e blocos de caracteres/bytes bloqueantes.
        // =========================================================================
        long t1_ini = System.currentTimeMillis();

        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            while (br.readLine() != null) {
                // Apenas consome o arquivo linha por linha
            }
        }

        long t1_fim = System.currentTimeMillis();
        System.out.println("JAVA IO - Demorou " + (t1_fim - t1_ini) + " ms");


        // =========================================================================
        // 2. JAVA NIO (FileChannel + ByteBuffer - Desde o Java 1.4)
        // Alta performance: utiliza canais diretos de baixo nível e buffers de memória.
        // Ideal para arquivos gigantescos devido ao controle refinado de blocos.
        // =========================================================================
        long t2_ini = System.currentTimeMillis();

        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            // Aloca um buffer de 8 KB (8192 bytes), tamanho ideal para otimizar iterações de leitura
            ByteBuffer buffer = ByteBuffer.allocate(8192);

            while (channel.read(buffer) != -1) {
                // Prepara o buffer para ser lido/processado (alterna do modo de escrita para leitura)
                buffer.flip();

                // Limpa o buffer para receber novos dados na próxima iteração do loop
                buffer.clear();
            }
        }

        long t2_fim = System.currentTimeMillis();
        System.out.println("JAVA NIO Channel - Demorou " + (t2_fim - t2_ini) + " ms");


        // =========================================================================
        // 3. JAVA NIO.2 (Files.readAllLines - Desde o Java 7)
        // Altamente produtivo e limpo, porém carrega O ARQUIVO INTEIRO PARA A MEMÓRIA.
        // Perigoso para arquivos muito grandes (risco de OutOfMemoryError).
        // =========================================================================
        long t3_ini = System.currentTimeMillis();

        // Lê todas as linhas de uma única vez para uma List<String>
        List<String> lines = Files.readAllLines(path);

        long t3_fim = System.currentTimeMillis();
        System.out.println("JAVA NIO2 ReadAllLines - Demorou " + (t3_fim - t3_ini) + " ms");
    }
}