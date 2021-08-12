package south.system.test.sessaovotacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.repository.IPautaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
//@Transactional
public class PautaService {

    @Autowired
    private IPautaRepository pautaRepository;

    public List<Pauta> listAllPauta() {
        return pautaRepository.findAll();
    }

    public void savePauta(Pauta pauta) {
        pautaRepository.save(pauta);
    }

    public Pauta getPauta(Long id) {
        return pautaRepository.findById(id).get();
    }

    public void deleteAssociado(Long id) {
        pautaRepository.deleteById(id);
    }


}
