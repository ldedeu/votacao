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

        if (pautaAssociado.getPauta() != null && pautaAssociado.getPauta().getId() != null)
            optPauta = pautaRepository.findById(pautaAssociado.getPauta().getId());

        if (optPauta == null || !optPauta.isPresent()) {
            response.setMessage("Nao existe uma pauta válida.");
            return response;
        }

        Pauta pauta = optPauta.get();

        Optional<Associado> associado = null;
        if (pautaAssociado.getAssociado() != null && pautaAssociado.getAssociado().getId() != null)
            associado = associadoRepository.findById(pautaAssociado.getAssociado().getId());

        if (associado == null || !associado.isPresent()) {
            response.setMessage("Nao existe um associado válido.");
            return response;
        }

        List<PautaAssociado> associadoList = pauta.getAssociados();
        Optional<PautaAssociado> optionalPautaAssociado = associadoList.stream().
                filter(x -> x.getAssociado().getId().equals(pautaAssociado.getAssociado().
                        getId())).findFirst();

        if (optionalPautaAssociado.isPresent()) {
            Associado associadoPresent = optionalPautaAssociado.get().getAssociado();
            response.setMessage("O associado ".concat(associadoPresent.getNome() + " "
                    + associadoPresent.getSobrenome() + " "
                    + "voto" + " " + optionalPautaAssociado.get().getVoto() + " "
                    + "nao pode voltar votar nesta pauta"));
            return response;
        }

        PautaAssociadoId pautaAssociadoId = new PautaAssociadoId(pautaAssociado.getPauta().getId(),
                pautaAssociado.getAssociado().getId());
        pautaAssociado.setId(pautaAssociadoId);
        pautaAssociadoRepository.save(pautaAssociado);
        response.setMessage("O voto foi corretamente executado.");
        return response;
    }
}