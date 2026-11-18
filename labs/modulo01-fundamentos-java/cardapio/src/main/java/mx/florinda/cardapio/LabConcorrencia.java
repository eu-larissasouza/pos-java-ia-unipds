package mx.florinda.cardapio;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class LabConcorrencia {

    public static void main(String[] args) throws Exception {
        atendimento();
        condicaoDeCorrida();
    }

    static void atendeCliente() {
        try {
            Thread.sleep(250);                      // simula I/O (banco, arquivo, rede)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static long medir(Runnable r) {
        long ini = System.nanoTime();
        r.run();
        return (System.nanoTime() - ini) / 1_000_000;
    }

    // 1) 20 "clientes" de 250 ms cada: uma thread, uma thread por cliente, pool fixo
    static void atendimento() throws Exception {
        System.out.println("== 20 clientes de 250 ms");

        System.out.println("sequencial        : " + medir(() -> {
            for (int i = 0; i < 20; i++) atendeCliente();
        }) + " ms");

        System.out.println("thread por cliente: " + medir(() -> {
            var threads = new Thread[20];
            for (int i = 0; i < 20; i++) (threads[i] = new Thread(LabConcorrencia::atendeCliente)).start();
            for (Thread t : threads) {
                try { t.join(); } catch (InterruptedException e) { throw new RuntimeException(e); }
            }
        }) + " ms");

        System.out.println("pool de 5 threads : " + medir(() -> {
            try (ExecutorService pool = Executors.newFixedThreadPool(5)) {   // close() espera terminar (Java 19+)
                for (int i = 0; i < 20; i++) pool.execute(LabConcorrencia::atendeCliente);
            }
        }) + " ms");

        System.out.println("virtual threads   : " + medir(() -> {
            try (ExecutorService pool = Executors.newVirtualThreadPerTaskExecutor()) {
                for (int i = 0; i < 20; i++) pool.execute(LabConcorrencia::atendeCliente);
            }
        }) + " ms");
    }

    // 2) Várias threads mexendo no mesmo dado
    static void condicaoDeCorrida() {
        System.out.println("\n== 50 threads x 1000 incrementos (esperado: 50000)");
        int[] inseguro = {0};
        AtomicInteger atomico = new AtomicInteger();
        Map<String, Integer> mapa = new ConcurrentHashMap<>();

        try (ExecutorService pool = Executors.newFixedThreadPool(50)) {
            for (int t = 0; t < 50; t++) {
                pool.execute(() -> {
                    for (int i = 0; i < 1000; i++) {
                        inseguro[0]++;                        // read-modify-write não é atômico
                        atomico.incrementAndGet();
                        mapa.merge("total", 1, Integer::sum); // operação atômica no ConcurrentHashMap
                    }
                });
            }
        }
        System.out.println("int comum        : " + inseguro[0]);
        System.out.println("AtomicInteger    : " + atomico.get());
        System.out.println("ConcurrentHashMap: " + mapa.get("total"));
    }
}
