package south.system.test.sessaovotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import south.system.test.sessaovotacao.model.PautaAssociado;
import south.system.test.sessaovotacao.model.PautaAssociadoId;

/**
 * Interface repositorio para a Entidad PautaAssociado
 *
 * @author lauren.dedeu
 */
public interface IPautaAssociadoRepository extends JpaRepository<PautaAssociado, PautaAssociadoId> {
}
