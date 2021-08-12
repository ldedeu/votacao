package south.system.test.sessaovotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import south.system.test.sessaovotacao.model.Associado;

public interface IAssociadoRepository extends JpaRepository<Associado, Long> {

}
