package south.system.test.sessaovotacao.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "associado")
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String nome;

    @Column(length = 50)
    private String sobrenome;

//    @ManyToMany(mappedBy = "associadosJ", fetch = FetchType.LAZY)
//    private List<Pauta> pautasJ = new ArrayList<>();

    @OneToMany(
            mappedBy = "associado",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PautaAssociado> pautas = new ArrayList<>();

    public Associado() {
    }

    public Associado(Long id, String nome, String sobrenome) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public List<PautaAssociado> getPautas() {
        return pautas;
    }

//    public List<Pauta> getPautasJ() {
//        return pautasJ;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Associado associado = (Associado) o;
        return id.equals(associado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
