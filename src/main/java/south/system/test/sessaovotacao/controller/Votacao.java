package south.system.test.sessaovotacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import south.system.test.sessaovotacao.model.Associado;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.model.PautaAssociado;
import south.system.test.sessaovotacao.service.AssociadoService;
import south.system.test.sessaovotacao.service.PautaAssociadoService;
import south.system.test.sessaovotacao.service.PautaService;

/**
 * API para executar uma sessao de votacao.
 *
 * @author lauren.dedeu
 */
@RestController
@RequestMapping("/votacao")
public class Votacao {

    @Autowired
    PautaService pautaService;

    @Autowired
    AssociadoService associadoService;

    @Autowired
    PautaAssociadoService pautaAssociadoService;

    /**
     * Método para cadastrar uma pauta
     *
     * @param pauta Pauta a cadastrar
     */
    @PostMapping(path = "/pauta/cadastrar_pauta", consumes = "application/json", produces = "application/json")
    public void cadastrarPauta(@RequestBody Pauta pauta) {
        pautaService.savePauta(pauta);
    }

    /**
     * Método para cadastrar um associado
     *
     * @param associado Associado a cadastrar
     */
    @PostMapping(path = "/associado/cadastrar_associado", consumes = "application/json", produces = "application/json")
    public void cadastrarAssociado(@RequestBody Associado associado) {
        associadoService.saveAssociado(associado);
    }

    /**
     * Método para fazer uma votacao de um Associado em uma Pauta dada.
     *
     * @param pautaAssociado Dados da Pauta e o Associado para fazer a votacao
     * @return String com mensagem do resultado da votacao do Associado na Pauta
     */
    @PostMapping(path = "/pauta/voto", consumes = "application/json", produces = "application/json")
    public String voto(@RequestBody PautaAssociado pautaAssociado) {
        return pautaAssociadoService.votar(pautaAssociado);
    }

    /**
     * Método para obter os resultados de uma sessao de votacao em uma pauta dada.
     *
     * @param pautaId ID da pauta
     * @return String com mensagem dos resultados da sessao de votacao de uma pauta.
     * Total de eleitores, Total SIM e Total NAO
     */
    @GetMapping(path = "/pauta/voto/resultados/{pautaId}", produces = "application/json")
    public String resultadosVotacao(@PathVariable(value = "pautaId") Long pautaId) {
        return pautaService.resultadoVotacao(pautaId);
    }

}
