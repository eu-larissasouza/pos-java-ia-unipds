package mx.florinda.cardapio;

import java.io.Serializable;
import java.math.BigDecimal;

public record ItemCardapio(Long id, String nome, String descricao, ItemCardapio.CategoriaCardapio categoria,
                           BigDecimal preco, BigDecimal precoComDesconto) implements Serializable {

    public enum CategoriaCardapio {
        ENTRADAS, PRATOS_PRINCIPAIS, BEBIDAS, SOBREMESA;
    }

    public ItemCardapio alteraPreco(BigDecimal novoPreco) {
        return new ItemCardapio(id, nome, descricao, categoria, novoPreco, precoComDesconto);
    }


}
