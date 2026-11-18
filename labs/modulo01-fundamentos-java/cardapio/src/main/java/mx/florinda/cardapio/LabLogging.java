package mx.florinda.cardapio;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LabLogging {

    private static final Logger logger = Logger.getLogger(LabLogging.class.getName());

    public static void main(String[] args) {
        logger.severe("SEVERE  - erro grave");
        logger.warning("WARNING - algo estranho");
        logger.info("INFO    - evento normal (nível padrão do console)");
        logger.config("CONFIG  - configuração");
        logger.fine("FINE    - detalhe de depuração");
        logger.finer("FINER   - mais detalhe");
        logger.finest("FINEST  - detalhe máximo");

        // Supplier: a mensagem só é montada se o nível estiver ligado
        logger.finer(() -> "custo de montar isto só existe se FINER estiver ativo: " + caro());

        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException ex) {
            logger.log(Level.SEVERE, "Falha ao converter valor", ex);   // passa a exceção, não só ex.getMessage()
        }
    }

    static String caro() {
        return "resultado";
    }
}
