package south.system.test.sessaovotacao.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pauta_associado",
            joinColumns = {@JoinColumn(name = "pauta_id")},
            inverseJoinColumns = {@JoinColumn(name = "associado_id")})
    private List<Associado> associados = new ArrayList<>();

    public Pauta() {
    }

    public Pauta(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
