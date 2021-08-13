package south.system.test.sessaovotacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import south.system.test.sessaovotacao.model.Associado;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.model.PautaAssociado;
import south.system.test.sessaovotacao.service.AssociadoService;
import south.system.test.sessaovotacao.service.PautaAssociadoService;
import south.system.test.sessaovotacao.service.PautaService;
import south.system.test.sessaovotacao.util.Erro;

/**
 * API para executar uma sessao de votacao.
 *
 * @author lauren.dedeu
 */
@RestController
@RequestMapping("/votacao/v1")
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
    public ResponseEntity<?> cadastrarPauta(@RequestBody Pauta pauta) {
        try {
            return new ResponseEntity<>(pautaService.savePauta(pauta), HttpStatus.CREATED);
        } catch (Exception exception) {
            return gerarError(exception);
        }
    }

    /**
     * Método para cadastrar um associado
     *
     * @param associado Associado a cadastrar
     */
    @PostMapping(path = "/associado/cadastrar_associado", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> cadastrarAssociado(@RequestBody Associado associado) {
        try {
            return new ResponseEntity<>(associadoService.saveAssociado(associado), HttpStatus.CREATED);
        } catch (Exception exception) {
            return gerarError(exception);
        }

    }

    /**
     * Método para fazer uma votacao de um Associado em uma Pauta dada.
     *
     * @param pautaAssociado Dados da Pauta e o Associado para fazer a votacao
     * @return String com mensagem do resultado da votacao do Associado na Pauta
     */
    @PostMapping(path = "/pauta/voto", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> voto(@RequestBody PautaAssociado pautaAssociado) {
        try {
            return new ResponseEntity<>(pautaAssociadoService.votar(pautaAssociado), HttpStatus.CREATED);
        } catch (Exception exception) {
            return gerarError(exception);
        }
    }

    /**
     * Método para obter os resultados de uma sessao de votacao em uma pauta dada.
     *
     * @param pautaId ID da pauta
     * @return String com mensagem dos resultados da sessao de votacao de uma pauta.
     * Total de eleitores, Total SIM e Total NAO
     */
    @GetMapping(path = "/pauta/voto/resultados/{pautaId}", produces = "application/json")
    public ResponseEntity<?> resultadosVotacao(@PathVariable(value = "pautaId") Long pautaId) {
        try {
            return new ResponseEntity<>(pautaService.resultadoVotacao(pautaId), HttpStatus.CREATED);
        } catch (Exception exception) {
            return gerarError(exception);
        }
    }

    /**
     * Método para criar erro dada uma exception
     *
     * @param exception Exception lancada.
     * @return ResponseEntity<Erro> con os dados do Erro
     * @auto
     */
    private ResponseEntity<Erro> gerarError(Exception exception) {
        Erro erro = new Erro(exception.getCause().getCause().getMessage(), exception.getCause().getCause().getClass().getName());
        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
