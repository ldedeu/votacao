package south.system.test.sessaovotacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import south.system.test.sessaovotacao.model.Associado;
import south.system.test.sessaovotacao.repository.IAssociadoRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
//@Transactional
public class AssociadoService {

    @Autowired
    private IAssociadoRepository IAssociadoRepository;

    public List<Associado> listAllAssociado() {
        return IAssociadoRepository.findAll();
    }

    public void saveAssociado(Associado associado) {
        IAssociadoRepository.save(associado);
    }

    public Associado getAssociado(Long id) {
        return IAssociadoRepository.findById(id).get();
    }

    public void deleteAssociado(Long id) {
        IAssociadoRepository.deleteById(id);
    }
}
