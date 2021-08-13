package south.system.test.sessaovotacao.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import south.system.test.sessaovotacao.util.UtilResponse;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.model.PautaAssociado;
import south.system.test.sessaovotacao.repository.IPautaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    private Gson gson = new Gson();

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
    public void savePauta(Pauta pauta) {
        pautaRepository.save(pauta);
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
    public String resultadoVotacao(Long idPauta) {

        Pauta pauta = this.getPauta(idPauta);
        UtilResponse response = new UtilResponse();
        List<PautaAssociado> associados = pauta.getAssociados();

        int totalEleitores = associados.size();
        final long totalSIM = associados.stream().filter(x -> x.getVoto().
                equalsIgnoreCase("sim")).count();
        final long totalNAO = associados.stream().filter(x -> x.getVoto().
                equalsIgnoreCase("nao")).count();

        response.setMessage("Na pauta con ID: " + " " + pauta.getId() + " votaram um total de: " + totalEleitores + " eleitores, "
                + "Votos SIM: " + totalSIM + " "
                + "Votos NAO: " + totalNAO);

        return gson.toJson(response);
    }


}
