package south.system.test.sessaovotacao.util;

/**
 * Classe para manejar os erroes.
 *
 * @author lauren.dedeu
 * @version 1.0
 */
public class Erro {

    private String message;
    private String tipo;
    private String codigo;

    public Erro(String message) {
        this.message = message;
    }

    public Erro(String message, String tipo) {
        this.message = message;
        this.tipo = tipo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
