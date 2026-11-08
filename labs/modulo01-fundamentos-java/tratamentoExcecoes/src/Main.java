import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // O throws é declarativo e obrigatório caso o método propague uma Exceção Checked (Verificada).
    // Se fosse uma RuntimeException (Não verificada), não seria obrigatório declarar no cabeçalho.
    public static void main(String[] args) throws MyException {

        // =========================================================================
        // TRY-WITH-RESOURCES (Introduzido no Java 7)
        // Fecha automaticamente qualquer recurso que implemente AutoCloseable (como o Scanner),
        // eliminando a necessidade de um bloco finally para chamar scanner.close().
        // =========================================================================
        try (Scanner scanner = new Scanner(System.in)){
            int a, b, r;

            System.out.println("Digite a:");
            a = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite b:");
            b = scanner.nextInt();

            r = a / b;
            System.out.println("Valor de r = " + r);

        } catch (ArithmeticException e) {
            // Tratamento específico para divisão por zero
            System.out.println("Erro - Divisão por zero não permitida.");

        } catch (InputMismatchException | NumberFormatException e) {
            // MULTICATCH: Trata diferentes exceções com a mesma lógica de negócio
            System.out.println("Erro - Você deveria digitar números inteiros válidos.");

        } catch (Exception e) {
            // REGRA DE OURO: Sempre ordene os catches da MAIS ESPECÍFICA para a MAIS GENÉRICA.
            System.out.println("Erro inesperado não mapeado!");
        }

        // Exemplo de modelagem básica (futuramente integrada com validações por exceções customizadas)
        Produto p = new Produto();
        p.setId(0);
        p.setNome(null);
        p.setPreco(10);
        System.out.println(p);
    }
}