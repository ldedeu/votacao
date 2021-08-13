package south.system.test.sessaovotacao.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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

    /**
     * Método que se executa a cada um minuto para a sessao de votacao das pautas.
     *
     * @author lauren.dedeu
     */
    @Scheduled(cron = "* * * * * *")
    public void sessaoVotacao() {

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
                pautaAssociadoService.votar(pautaAssociado);
            }

            System.out.println(pautaService.resultadoVotacao(temp.getId()));
        }
    }
}
