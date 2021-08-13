package south.system.test.sessaovotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import south.system.test.sessaovotacao.model.Pauta;

/**
 * Interface repositorio para a Entidad Pauta
 *
 * @author lauren.dedeu
 */
public interface IPautaRepository extends JpaRepository<Pauta, Long> {
}
