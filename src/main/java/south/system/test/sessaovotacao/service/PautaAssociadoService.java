package south.system.test.sessaovotacao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import south.system.test.sessaovotacao.model.Associado;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.model.PautaAssociado;
import south.system.test.sessaovotacao.model.PautaAssociadoId;
import south.system.test.sessaovotacao.repository.IAssociadoRepository;
import south.system.test.sessaovotacao.repository.IPautaAssociadoRepository;
import south.system.test.sessaovotacao.repository.IPautaRepository;
import south.system.test.sessaovotacao.util.Response;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Classe servico para o relacionamento entre Pauta e Associado
 *
 * @author lauren.dedeu
 * @version 1.0
 */
@Service
@Transactional
public class PautaAssociadoService {

    @Autowired
    private IPautaRepository pautaRepository;

    @Autowired
    private IAssociadoRepository associadoRepository;

    @Autowired
    private IPautaAssociadoRepository pautaAssociadoRepository;

    private static final Logger logger = LoggerFactory.getLogger(PautaAssociadoService.class);

    /**
     * Método para executar uma votacao.
     *
     * @param pautaAssociado parâmetro de la classe do relacionamento
     *                       de muitos a muitos entre Pauta e Associado
     * @return String com mensagem do resultado da votacao do Associado na Pauta.
     * @author lauren.dedeu
     */
    public Response votar(PautaAssociado pautaAssociado) throws Exception {

        Response response = new Response();
        Optional<Pauta> optPauta = null;
        String message;
        if (pautaAssociado.getPauta() != null && pautaAssociado.getPauta().getId() != null)
            optPauta = pautaRepository.findById(pautaAssociado.getPauta().getId());

        if (optPauta == null || !optPauta.isPresent()) {
            message = "Nao existe uma pauta válida.";
            logger.info(message, pautaAssociado);
            response.setMessage(message);
            return response;
        }

        Pauta pauta = optPauta.get();

        Optional<Associado> associado = null;
        if (pautaAssociado.getAssociado() != null && pautaAssociado.getAssociado().getId() != null)
            associado = associadoRepository.findById(pautaAssociado.getAssociado().getId());

        if (associado == null || !associado.isPresent()) {
            message = "Nao existe um associado válido.";
            logger.info(message, pautaAssociado);
            response.setMessage(message);
            return response;
        }

        List<PautaAssociado> associadoList = pauta.getAssociados();
        Optional<PautaAssociado> optionalPautaAssociado = associadoList.stream().
                filter(x -> x.getAssociado().getId().equals(pautaAssociado.getAssociado().
                        getId())).findFirst();

        if (optionalPautaAssociado.isPresent()) {
            Associado associadoPresent = optionalPautaAssociado.get().getAssociado();
            message = "O associado ".concat(associadoPresent.getNome() + " "
                    + associadoPresent.getSobrenome() + " "
                    + "voto" + " " + optionalPautaAssociado.get().getVoto() + " "
                    + "nao pode voltar votar nesta pauta");
            logger.info(message, pautaAssociado);
            response.setMessage(message);
            return response;
        }

        PautaAssociadoId pautaAssociadoId = new PautaAssociadoId(pautaAssociado.getPauta().getId(),
                pautaAssociado.getAssociado().getId());
        pautaAssociado.setId(pautaAssociadoId);
        pautaAssociadoRepository.save(pautaAssociado);
        message = "O voto foi corretamente executado.";
        response.setMessage(message);
        logger.info(message, pautaAssociado);
        return response;
    }
}