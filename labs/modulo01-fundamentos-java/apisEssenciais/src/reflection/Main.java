package reflection;

public class Main  {
    public static void main(String[] args) throws Exception{
        Produto p = new Produto(1, "Computador", 1000.0);
        Cliente c = new Cliente(1,"Isidro","987.654.321-00","isidro@isidro.com");
        ClassExplorer.exploreMetadata(c);
    }
}