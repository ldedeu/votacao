package south.system.test.sessaovotacao.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.*;

@Entity(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;

    @Column(length = 50)
    private String local;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PautaAssociado> associados = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "pauta_associado",
//            joinColumns = {@JoinColumn(name = "pauta_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "associado_id", referencedColumnName = "id")})
//    private List<Associado> associadosJ = new ArrayList<>();

    public Pauta() {
    }

    public Pauta(Long id, Date datetime, String local) {
        this.id = id;
        this.datetime = datetime;
        this.local = local;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<PautaAssociado> getAssociados() {
        return associados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return id.equals(pauta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

//    public void addAssociado(Associado associado, String voto) {
//        PautaAssociado pautaAssociado = new PautaAssociado(this, associado, voto);
//        associados.add(pautaAssociado);
//        associado.getPautas().add(pautaAssociado);
//    }
//
//    public void removeAssociado(Associado associado) {
//        for (Iterator<PautaAssociado> iterator = associados.iterator();
//             iterator.hasNext(); ) {
//            PautaAssociado pautaAssociado = iterator.next();
//
//            if (pautaAssociado.getPauta().equals(this) &&
//                    pautaAssociado.getAssociado().equals(associado)) {
//                iterator.remove();
//                pautaAssociado.getAssociado().getPautas().remove(pautaAssociado);
//                pautaAssociado.setPauta(null);
//                pautaAssociado.setAssociado(null);
//            }
//        }
//    }
}
