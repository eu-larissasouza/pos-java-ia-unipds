package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import mx.florinda.cardapio.ItemCardapio.CategoriaCardapio;

/**
 * Mostra o PADRÃO do JDBC com um SELECT parametrizado e um INSERT que devolve o id gerado.
 * Os "TODO" do SQLDatabase (alteraPreco, remove...) ficam para você resolver a partir daqui.
 *
 * Precisa do MySQL do docker-compose rodando e do banco "cardapio" criado.
 */
public class LabJdbc {

    private static final String URL = "jdbc:mysql://localhost:3306/cardapio";
    private static final String USER = "root";
    private static final String PASSWORD = "senha123";

    // SELECT com parâmetro: Connection, PreparedStatement e ResultSet fecham sozinhos (try-with-resources)
    static Optional<ItemCardapio> buscaPorId(long id) {
        String sql = "SELECT id, nome, descricao, categoria, preco, preco_promocional FROM item_cardapio WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);                                  // parâmetro: nunca concatene valores no SQL
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(new ItemCardapio(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        CategoriaCardapio.valueOf(rs.getString("categoria")),   // ENUM do banco -> enum Java
                        rs.getBigDecimal("preco"),                              // DECIMAL -> BigDecimal
                        rs.getBigDecimal("preco_promocional")));                // NULL do banco -> null no Java
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // INSERT sem informar o id: o AUTO_INCREMENT gera e o driver devolve
    static long insere(String nome, String descricao, CategoriaCardapio categoria, BigDecimal preco) {
        String sql = "INSERT INTO item_cardapio (nome, descricao, categoria, preco) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nome);
            ps.setString(2, descricao);
            ps.setString(3, categoria.name());
            ps.setBigDecimal(4, preco);
            ps.executeUpdate();                                 // INSERT/UPDATE/DELETE usam executeUpdate

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                chaves.next();
                return chaves.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(buscaPorId(1L));
        long novoId = insere("Tacos de Carnitas", "Tacos recheados", CategoriaCardapio.PRATOS_PRINCIPAIS, new BigDecimal("25.90"));
        System.out.println("id gerado: " + novoId);
    }
}
