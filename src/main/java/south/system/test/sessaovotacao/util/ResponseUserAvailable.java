package south.system.test.sessaovotacao.util;

/**
 * Classe para o usuario accesivel para executar o voto.
 *
 * @author lauren.dedeu
 * @version 1.0
 */
public class ResponseUserAvailable {

    /**
     * Status
     */
    private String status;

    public ResponseUserAvailable(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
