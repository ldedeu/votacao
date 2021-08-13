package south.system.test.sessaovotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import south.system.test.sessaovotacao.model.Associado;

/**
 * Interface repositorio para a Entidad Associado
 *
 * @author lauren.dedeu
 */
public interface IAssociadoRepository extends JpaRepository<Associado, Long> {

}
