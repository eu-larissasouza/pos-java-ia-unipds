import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {
        String fileName = "benchmark.txt";
        String linhaBase = "Linha Base - Testando I/O de arquivos\n";
        long tamanho = 49L * 1024 * 1024; // No Max. 200mb

        try {
            long tamanhoAtual = 0;
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            while ( tamanhoAtual < tamanho){
                writer.write(linhaBase);
                tamanhoAtual += linhaBase.getBytes().length;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}