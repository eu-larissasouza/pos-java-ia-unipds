package mx.florinda.cardapio;

import java.util.*;
import java.math.BigDecimal;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class LabColecoes {
    public static void main(String[] args) {
        Database database = new Database();

        // =========================================================================
        // 1. LISTAS E ORDENAÇÃO DE ITENS
        // =========================================================================
        List<ItemCardapio> itens = database.listaItensCardapio();
        System.out.println("=== LISTA DE ITENS DO CARDÁPIO ===");
        itens.forEach(System.out::println);
        System.out.println("----------------------------------");

        // =========================================================================
        // 2. BUSCA POR ID USANDO OPTIONAL
        // =========================================================================
        System.out.println("=== BUSCA POR ID (1L) ===");
        Optional<ItemCardapio> optionalItem = database.itemCardapioPorId(1L);
        if (optionalItem.isPresent()) {
            System.out.println("Encontrado: " + optionalItem.get());
        } else {
            System.out.println("Não encontrado");
        }
        System.out.println("----------------------------------");

        // =========================================================================
        // 3. ENUMSET (Manipulação eficiente de conjuntos de Enums)
        // O EnumSet usa vetores de bits internamente, sendo extremamente rápido.
        // =========================================================================
        System.out.println("=== TESTANDO ENUMSET ===");
        Set<ItemCardapio.CategoriaCardapio> categoriaCardapios = EnumSet.of(SOBREMESA, ENTRADAS);
        categoriaCardapios.add(PRATOS_PRINCIPAIS); // Permite mutabilidade controlada
        categoriaCardapios.forEach(cat -> System.out.println("Categoria ativa: " + cat));
        System.out.println("----------------------------------");

        // =========================================================================
        // 4. ENUMMAP (Mapa otimizado para chaves do tipo Enum)
        // =========================================================================
        System.out.println("=== TESTANDO ENUMMAP ===");
        Map<ItemCardapio.CategoriaCardapio, String> promocoes = new EnumMap<>(ItemCardapio.CategoriaCardapio.class);
        promocoes.put(SOBREMESA, "O doce perfeito para você!");
        promocoes.put(ENTRADAS, "Comece sua refeição com um toque de sabor!");
        promocoes.forEach((cat, desc) -> System.out.println(cat + " => " + desc));
        System.out.println("----------------------------------");

        // =========================================================================
        // 5. HISTÓRICO DE VISUALIZAÇÕES (Uso de WeakHashMap no backend)
        // =========================================================================
        System.out.println("=== HISTÓRICO DE VISUALIZAÇÕES ===");
        HistoricoVisualizacao historico = new HistoricoVisualizacao(database);
        historico.registrarVisualizacao(1L); // Refresco
        historico.registrarVisualizacao(2L); // Sanduíche
        historico.registrarVisualizacao(6L); // Pipoca
        historico.registrarVisualizacao(1L); // Refresco de novo

        historico.listaVisualizacoes();
        historico.totalItensVisualizados();
        System.out.println("----------------------------------");

        // =========================================================================
        // 6. ALTERAÇÃO DE PREÇO E AUDITORIA (IdentityHashMap)
        // =========================================================================
        System.out.println("=== TESTANDO ALTERAÇÃO DE PREÇO E AUDITORIA ===");
        boolean alterado = database.alteraPrecoItemCardapio(1L, new BigDecimal("3.49"));
        if (alterado) {
            System.out.println("Preço do item 1 atualizado com sucesso!");
        }

        // Dispara o rastro da auditoria interna usando IdentityHashMap
        database.rastroAuditoriaPrecos();
    }
}