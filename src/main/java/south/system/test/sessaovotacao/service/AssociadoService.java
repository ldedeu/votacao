package south.system.test.sessaovotacao.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import south.system.test.sessaovotacao.model.Associado;
import south.system.test.sessaovotacao.repository.IAssociadoRepository;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Classe servico para a entidad Associado
 *
 * @author lauren.dedeu
 * @version 1.0
 */
@Service
@Transactional
public class AssociadoService {

    @Autowired
    private IAssociadoRepository IAssociadoRepository;

    /**
     * Método para obter todos os associados
     *
     * @return Lista dos associados
     */
    public List<Associado> findAll() {
        return IAssociadoRepository.findAll();
    }

    /**
     * Método para cadastrar um associado
     *
     * @param associado Associado a cadastrar
     */
    public Associado saveAssociado(Associado associado) throws Exception {
        return IAssociadoRepository.save(associado);
    }
}
