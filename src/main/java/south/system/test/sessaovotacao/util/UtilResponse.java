package south.system.test.sessaovotacao.util;

/**
 * Classe para construir mensagem de resposta nos cenários necessários .
 *
 * @author lauren.dedeu
 * @version 1.0
 */
public class UtilResponse {

    private String message;

    public UtilResponse() {
    }

    public UtilResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
