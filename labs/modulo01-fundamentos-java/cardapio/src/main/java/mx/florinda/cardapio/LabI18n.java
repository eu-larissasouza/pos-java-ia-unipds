package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class LabI18n {

    public static void main(String[] args) {
        System.out.println("Locale padrão da JVM: " + Locale.getDefault());

        Locale ptBR = Locale.of("pt", "BR");      // Locale.of: Java 19+ (antes: new Locale(...))
        Locale enUS = Locale.US;
        BigDecimal preco = new BigDecimal("1234.50");

        // Moeda
        System.out.println("\nMoeda");
        System.out.println(" pt-BR: " + NumberFormat.getCurrencyInstance(ptBR).format(preco));
        System.out.println(" en-US: " + NumberFormat.getCurrencyInstance(enUS).format(preco));

        // Data e hora
        var dataHora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        var mesAno = DateTimeFormatter.ofPattern("MMMM/yyyy");
        System.out.println("\nData");
        System.out.println(" pt-BR: " + dataHora.withLocale(ptBR).format(ZonedDateTime.now()) + " | " + mesAno.withLocale(ptBR).format(YearMonth.now()));
        System.out.println(" en-US: " + dataHora.withLocale(enUS).format(ZonedDateTime.now()) + " | " + mesAno.withLocale(enUS).format(YearMonth.now()));

        // Textos (mensagens.properties = base/inglês, mensagens_pt_BR.properties = português)
        System.out.println("\nResourceBundle");
        System.out.println(" pt-BR: " + ResourceBundle.getBundle("mensagens", ptBR).getString("categoria.cardapio.bebidas"));
        System.out.println(" en-US: " + ResourceBundle.getBundle("mensagens", enUS).getString("categoria.cardapio.sobremesa"));
    }
}
