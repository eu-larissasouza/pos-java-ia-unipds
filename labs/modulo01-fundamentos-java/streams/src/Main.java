import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Veiculo> veiculoList = new ArrayList<>() {{
            add(new Veiculo("Corsa", "Cinza", 25000, 1998, 160));
            add(new Veiculo("Corolla", "Prata", 70000, 2020, 200));
            add(new Veiculo("Corolla", "Preto", 100000, 2025, 210));
            add(new Veiculo("X1", "Branco", 250000, 2023, 250));
            add(new Veiculo("GLA200", "Prata", 300000, 2025, 280));
        }};

        // =========================================================================
        // 1. ORDENAÇÃO E COLEÇÃO MUTÁVEL
        // Usa Method Reference (Veiculo::getMarca) e .collect(Collectors.toList())
        // para garantir uma lista resultante mutável.
        // =========================================================================
        List<Veiculo> ordened = veiculoList.stream()
                .sorted(Comparator.comparing(Veiculo::getMarca).reversed())
                .collect(Collectors.toList());
        ordened.add(new Veiculo("ZX", "Branco", 250000, 2023, 250);
        System.out.println("Ordenados decrescente: " + ordened);

        // =========================================================================
        // 2. FILTRAGEM E LISTA IMUTÁVEL
        // O .toList() moderno (Java 16+) gera uma lista imutável (tamanho fixo).
        // =========================================================================
        List<Veiculo> corolas = veiculoList.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase("corolla"))
                .toList();
        System.out.println("Filtro Corolla: " + corolas);

        // =========================================================================
        // 3. OPERAÇÕES DE AGREGAÇÃO (AVG, MAX, MIN)
        // Mapeia para um fluxo de doubles (mapToDouble) para extrair métricas.
        // O orElse(0.0) previne falhas caso a lista esteja vazia.
        // =========================================================================
        double precoMedio = veiculoList.stream()
                .mapToDouble(Veiculo::getPreco)
                .average()
                .orElse(0.0);
        System.out.println("Preço Médio = " + precoMedio);

        double maximo = veiculoList.stream()
                .mapToDouble(Veiculo::getPreco)
                .max()
                .orElse(0.0);
        System.out.println("Preço Máximo = " + maximo);

        double minimo = veiculoList.stream()
                .mapToDouble(Veiculo::getPreco)
                .min()
                .orElse(0.0); // Boa prática substituído por orElse para evitar NoSuchElementException
        System.out.println("Preço Mínimo = " + minimo);

        // =========================================================================
        // 4. COMBINAÇÃO DE FILTRO + AGREGAÇÃO
        // Filtra elementos específicos antes de calcular a média.
        // =========================================================================
        double mediaDosCorolas = veiculoList.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase("corolla"))
                .mapToDouble(Veiculo::getPreco)
                .average()
                .orElse(0.0);
        System.out.println("Média dos Corollas = " + mediaDosCorolas);

        // =========================================================================
        // 5. TRANSFORMAÇÃO (MAP)
        // Aplica uma função de transformação em cada elemento do stream.
        // =========================================================================
        List<Veiculo> veiculoListCorolaMaiusculo = veiculoList.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase("Corolla"))
                .map(Main::converterParaMaiusculo) // Transformando objetos via metodo auxiliar
                .toList();
        System.out.println("Corollas em Maiúsculo: " + veiculoListCorolaMaiusculo);
    }

    /**
     * Metodo auxiliar para transformar os dados de um veículo (Exemplo de mapeamento).
     */
    public static Veiculo converterParaMaiusculo(Veiculo v) {
        return new Veiculo(
                v.getMarca().toUpperCase(),
                v.getCor().toUpperCase(),
                v.getPreco(),
                v.getAno(),
                v.getVelMaxima()
        );
    }
}