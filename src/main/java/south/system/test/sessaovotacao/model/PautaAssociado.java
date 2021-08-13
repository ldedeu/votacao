package south.system.test.sessaovotacao.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad resultado do relacionamento muitos para muitos entre pauta e associado.
 *
 * @author lauren dedeu
 * @version 1.0
 */
@Entity(name = "pauta_associado")
public class PautaAssociado {

    @EmbeddedId
    private PautaAssociadoId id;

    @ManyToOne
    @MapsId("pautaId")
    private Pauta pauta;

    @ManyToOne
    @MapsId("associadoId")
    private Associado associado;

    @Column(length = 3)
    private String voto;

    private PautaAssociado() {
    }

    public PautaAssociado(Pauta pauta, Associado associado, String voto) {
        this.pauta = pauta;
        this.associado = associado;
        this.voto = voto;
    }

    public PautaAssociadoId getId() {
        return id;
    }

    public void setId(PautaAssociadoId id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PautaAssociado that = (PautaAssociado) o;
        return id.equals(that.id) && pauta.equals(that.pauta) && associado.equals(that.associado) && voto.equals(that.voto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pauta, associado, voto);
    }
}
