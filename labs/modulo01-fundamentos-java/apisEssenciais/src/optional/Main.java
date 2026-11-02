package optional;

public class Main {
    public static void main(String[] args) {
        ProdutoRepo repo = new ProdutoRepo();

        Produto p = repo.findById(1).orElseThrow(()-> new RuntimeException("Produto inexistente"));
        System.out.println(p.getNome());
    }
}