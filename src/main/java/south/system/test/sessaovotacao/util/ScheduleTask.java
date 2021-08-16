package south.system.test.sessaovotacao.util;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import south.system.test.sessaovotacao.kafka.config.KafkaConfigFactory;
import south.system.test.sessaovotacao.model.Associado;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.model.PautaAssociado;
import south.system.test.sessaovotacao.model.PautaAssociadoId;
import south.system.test.sessaovotacao.service.AssociadoService;
import south.system.test.sessaovotacao.service.PautaAssociadoService;
import south.system.test.sessaovotacao.service.PautaService;

import java.util.List;

/**
 * Classe para executar a cada um minuto a sessao de votacao nas pautas da base dados.
 *
 * @author lauren.dedeu
 * @version 1.0
 */
@Component
public class ScheduleTask {
    @Autowired
    PautaAssociadoService pautaAssociadoService;

    @Autowired
    PautaService pautaService;

    @Autowired
    AssociadoService associadoService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private Gson gson;

    @Autowired
    KafkaConfigFactory consumer;

    private final String URL_USER_VALIDO = "https://user-info.herokuapp.com/users/{cpf}";
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    /**
     * Método que se executa a cada um minuto para a sessao de votacao das pautas.
     *
     * @author lauren.dedeu
     */
    @Scheduled(cron = "* * * * * *")
    private void sessaoVotacao() {

        try {
            List<Pauta> pautas = pautaService.findAll();
            List<Associado> associados = associadoService.findAll();
            String voto = "";

            for (Pauta temp : pautas) {
                for (Associado tempA : associados) {
                    if ("".equalsIgnoreCase(voto))
                        voto = "SIM";
                    else if ("SIM".equalsIgnoreCase(voto))
                        voto = "NAO";
                    else
                        voto = "SIM";

                    PautaAssociado pautaAssociado = new PautaAssociado(temp, tempA, voto);
                    PautaAssociadoId id = new PautaAssociadoId(temp.getId(), tempA.getId());
                    pautaAssociado.setId(id);
                    String isValidCPF = "";
                    try {
                        isValidCPF = webClientBuilder.build()
                                .get()
                                .uri(URL_USER_VALIDO, pautaAssociado.getAssociado().getCpf())
                                .retrieve()
                                .bodyToMono(String.class)
                                .block();
                    } catch (WebClientException e) {
                        if (e instanceof WebClientResponseException) {
                            if (((WebClientResponseException) e).getStatusCode().value() == 404)
                                logger.error("O associado com ID " + pautaAssociado.getAssociado().getId() +
                                        ", " + "seu CPF:" + " " + pautaAssociado.getAssociado().getCpf() +
                                        " " + "nao é válido.");
                        } else
                            logger.error(e.getMessage(), e);
                    }

                    if (!"".equalsIgnoreCase(isValidCPF)) {
                        ResponseUserAvailable responseUserAvailable = gson.fromJson(isValidCPF, ResponseUserAvailable.class);
                        if ("ABLE_TO_VOTE".equalsIgnoreCase(responseUserAvailable.getStatus()))
                            pautaAssociadoService.votar(pautaAssociado);
                        else if ("UNABLE_TO_VOTE".equalsIgnoreCase(responseUserAvailable.getStatus()))
                            logger.info("O usuario" + " " + pautaAssociado.getAssociado().getNome() + " " +
                                    pautaAssociado.getAssociado().getSobrenome() + " " + "nao pode executar o voto.", temp);
                    }
                }
                pautaService.resultadoVotacao(temp.getId()).getMessage();
                consumer.consumerFactory();
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
        }
    }

}
