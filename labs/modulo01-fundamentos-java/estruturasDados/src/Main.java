import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Produto> produtoList = new ArrayList<>();
        produtoList.add(new Produto(1, "Computador", 1500.0));
        produtoList.add(new Produto(2, "Mouse", 50.0));
        produtoList.add(new Produto(3, "Teclado", 100.0));
        produtoList.add(new Produto(1, "Computador", 1500.0));
        produtoList.add(new Produto(1, "Computador", 1500.0));
        // duplica Produto  "Computador"
        System.out.println(produtoList);

        Set<Produto> conjunto = new HashSet<>();
        conjunto.add(new Produto(1, "Computador", 1500.0));
        conjunto.add(new Produto(1, "Computador", 1500.0)); // adição skipada
        // sem o equals, ele exibe um item duplicado, porque nao tem garantia para saber como comparar os objetos
        System.out.println(conjunto);

        Map<Integer, Produto> mapa = new HashMap<Integer, Produto>();
        mapa.put(1, new Produto(1, "Computador", 1500.0));
        mapa.put(2, new Produto(2, "Mouse", 50.0));
        mapa.put(3, new Produto(3, "Teclado", 100.0));
        mapa.put(4,new Produto(1, "Computador", 1500.0)); // chave diferente, mesmo produto
        mapa.put(1, new Produto(1, "Mesa", 200.0)); // substitui na mesma chave
        System.out.println(mapa);

        benchmarkList(100000);
        benchmarkMapa(100000);
    }

    public static void benchmarkList(int tamanho) {
        List<Produto> lista = new ArrayList<>();
        for (int i=0;i<tamanho;i++) {
            lista.add(new Produto(i+1,"produto "+(i+1), (i+1)*10.0));
        }

        int itemBusca = tamanho -1;

        long ini, fim;
        ini = System.currentTimeMillis();
        for (int count = 1; count <= 1000; count++) {
            for(Produto p: lista) {
                if (p.getId()==itemBusca)
                    break;
            }
        }
        fim = System.currentTimeMillis();

        System.out.println("Demorou "+(fim-ini)+"ms para a busca");
    }

    public static void benchmarkMapa(int tamanho) {
        Map<Integer, Produto> mapa = new HashMap<>();

        for (int i=0;i<tamanho;i++) {
            mapa.put(i+1, new Produto(i+1,"produto "+(i+1), (i+1)*10.0));
        }

        int itemBusca = tamanho -1;
        double ini, fim;
        ini = System.currentTimeMillis();
        for (int count=0;count<1000;count++) {
            if (mapa.get(itemBusca)!= null) {
                // codigo para achar
            }
        }
        fim = System.currentTimeMillis();
        System.out.println("Demorou "+(fim-ini)+"ms para a busca");
    }

}