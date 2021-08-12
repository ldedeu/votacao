package south.system.test.sessaovotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import south.system.test.sessaovotacao.model.Pauta;

public interface IPautaRepository extends JpaRepository<Pauta, Long> {
}
