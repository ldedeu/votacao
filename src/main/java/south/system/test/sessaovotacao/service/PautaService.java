package south.system.test.sessaovotacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import south.system.test.sessaovotacao.kafka.KafkaProducerService;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.model.PautaAssociado;
import south.system.test.sessaovotacao.repository.IPautaRepository;
import south.system.test.sessaovotacao.util.Response;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Classe servico para a entidad Pauta
 *
 * @author lauren.dedeu
 * @version 1.0
 */
@Service
@Transactional
public class PautaService {

    @Autowired
    private IPautaRepository pautaRepository;

    @Autowired
    private KafkaProducerService producerService;

    /**
     * Método para obter todas as pautas
     *
     * @return Lista das pautas
     */
    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    /**
     * Método para cadastrar na base de dados uma pauta
     *
     * @param pauta Pauta a cadastrar
     */
    public Pauta savePauta(Pauta pauta) throws Exception {
        return pautaRepository.save(pauta);
    }

    /**
     * Método para obter uma pauta dado seu ID.
     *
     * @param id Id da pauta.
     * @return A Pauta.
     */
    public Pauta getPauta(Long id) {
        return pautaRepository.findById(id).get();
    }

    /**
     * Método para obter os resultados de uma sessao de votacao em uma Pauta.
     *
     * @param idPauta Id da pauta.
     * @return String com mensagem dos resultados (Total de eleitores, Total SIM e Total NAO)
     */
    public Response resultadoVotacao(Long idPauta) throws Exception {

        Pauta pauta = this.getPauta(idPauta);
        Response response = new Response();
        List<PautaAssociado> associados = pauta.getAssociados();

        int totalEleitores = associados.size();
        final long totalSIM = associados.stream().filter(x -> x.getVoto().
                equalsIgnoreCase("sim")).count();
        final long totalNAO = associados.stream().filter(x -> x.getVoto().
                equalsIgnoreCase("nao")).count();
        String message = "Na pauta con ID: " + " " + pauta.getId() + " votaram um total de: " + totalEleitores + " eleitores, "
                + "Votos SIM: " + totalSIM + " "
                + "Votos NAO: " + totalNAO;
        response.setMessage(message);
        producerService.sendMessage(message);
        return response;
    }


}
