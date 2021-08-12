package south.system.test.sessaovotacao.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PautaAssociadoId implements Serializable {

    //@Column(name = "pauta_id")
    private Long pautaId;

   //@Column(name = "associado_id")
    private Long associadoId;

    private PautaAssociadoId() {
    }

    public PautaAssociadoId(Long pautaId, Long associadoId) {
        this.pautaId = pautaId;
        this.associadoId = associadoId;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public Long getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(Long associadoId) {
        this.associadoId = associadoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PautaAssociadoId that = (PautaAssociadoId) o;
        return pautaId.equals(that.pautaId) && associadoId.equals(that.associadoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pautaId, associadoId);
    }
}
