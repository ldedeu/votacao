package south.system.test.sessaovotacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import south.system.test.sessaovotacao.model.Associado;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.model.PautaAssociado;
import south.system.test.sessaovotacao.model.PautaAssociadoId;
import south.system.test.sessaovotacao.repository.IAssociadoRepository;
import south.system.test.sessaovotacao.repository.IPautaAssociadoRepository;
import south.system.test.sessaovotacao.repository.IPautaRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
//@Transactional
public class PautaAssociadoService {

    @Autowired
    private IPautaRepository pautaRepository;

    @Autowired
    private IAssociadoRepository associadoRepository;

    @Autowired
    private IPautaAssociadoRepository pautaAssociadoRepository;

    private Logger logger;


    /**
     * Método para ejecutar una votacion.
     *
     * @param pautaAssociado parametro de la clase de muchos a muchos
     * @author lauren.dedeu
     */
    public void votar(PautaAssociado pautaAssociado) {
        try {
            Optional<Pauta> optPauta = null;
            if (pautaAssociado.getPauta() != null && pautaAssociado.getPauta().getId() != null)
                optPauta = pautaRepository.findById(pautaAssociado.getPauta().getId());

            if (optPauta != null && !optPauta.isPresent()) {
                logger.log(Level.INFO, "Nao existe uma pauta valida.");
                return;
            }

            Optional<Associado> associado = null;
            if (pautaAssociado.getAssociado() != null && pautaAssociado.getAssociado().getId() != null)
                associado = associadoRepository.findById(pautaAssociado.getAssociado().getId());

            if (associado != null && !associado.isPresent()) {
                logger.log(Level.INFO, "Nao existe um associado valido.");
                return;
            }
            PautaAssociadoId pautaAssociadoId = new PautaAssociadoId(pautaAssociado.getPauta().getId(),
                    pautaAssociado.getAssociado().getId());
            pautaAssociado.setId(pautaAssociadoId);
            pautaAssociadoRepository.save(pautaAssociado);
            pautaAssociadoRepository.flush();
        } catch (Exception exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        }

    }
}
