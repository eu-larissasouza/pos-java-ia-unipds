package mx.florinda.cardapio;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.BEBIDAS;

public class LabSerializacao {

    // Classe "de verdade" (não record) para ver serialVersionUID e transient
    static class Usuario implements Serializable {
        private static final long serialVersionUID = 1L;   // versão do "contrato" dos bytes
        String nome;
        transient String senha;                             // NÃO vai para os bytes

        Usuario(String nome, String senha) {
            this.nome = nome;
            this.senha = senha;
        }
    }

    public static void main(String[] args) throws Exception {
        // 1) Objeto -> bytes -> objeto
        var itens = new ArrayList<>(List.of(
                new ItemCardapio(1L, "Refresco do Chaves", "Suco", BEBIDAS, new BigDecimal("2.99"), null)));

        byte[] bytes = serializa(itens);
        System.out.println("bytes gerados: " + bytes.length);
        System.out.println("lido de volta: " + desserializa(bytes));

        // 2) transient
        Usuario u = (Usuario) desserializa(serializa(new Usuario("Chaves", "segredo")));
        System.out.println("nome=" + u.nome + " | senha=" + u.senha);

        // 3) Filtro: só aceita as classes que você espera (defesa contra desserialização maliciosa)
        try (var ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            ois.setObjectInputFilter(ObjectInputFilter.Config.createFilter(
                    "mx.florinda.cardapio.*;java.util.*;java.lang.*;java.math.*;!*"));
            System.out.println("com filtro OK: " + ois.readObject());
        }
        try (var ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            ois.setObjectInputFilter(ObjectInputFilter.Config.createFilter("java.util.*;!*"));
            ois.readObject();
        } catch (InvalidClassException e) {
            System.out.println("filtro restritivo bloqueou: " + e.getMessage());
        }
    }

    static byte[] serializa(Object o) throws IOException {
        var bos = new ByteArrayOutputStream();
        try (var oos = new ObjectOutputStream(bos)) {
            oos.writeObject(o);
        }
        return bos.toByteArray();
    }

    static Object desserializa(byte[] bytes) throws IOException, ClassNotFoundException {
        try (var ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return ois.readObject();
        }
    }
}
