package south.system.test.sessaovotacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import south.system.test.sessaovotacao.model.Associado;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.model.PautaAssociado;
import south.system.test.sessaovotacao.service.AssociadoService;
import south.system.test.sessaovotacao.service.PautaAssociadoService;
import south.system.test.sessaovotacao.service.PautaService;

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
     * Metodo para cadastrar pauta
     * @param pauta
     */
    @PostMapping(path = "/pauta/cadastrar_pauta", consumes = "application/json", produces = "application/json")
    public void cadastrarPauta(@RequestBody Pauta pauta) {
        pautaService.savePauta(pauta);
    }

    /**
     * Metodo para cadastrar pauta
     * @param pauta
     */
    @PostMapping(path = "/associado/cadastrar_associado", consumes = "application/json", produces = "application/json")
    public void cadastrarAssociado(@RequestBody Associado associado) {
        associadoService.saveAssociado(associado);
    }

    @PostMapping(path = "/pauta/voto", consumes = "application/json", produces = "application/json")
    public void voto(@RequestBody PautaAssociado pautaAssociado) {
        pautaAssociadoService.votar(pautaAssociado);
    }

}
