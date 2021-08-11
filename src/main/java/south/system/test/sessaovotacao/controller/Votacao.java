package south.system.test.sessaovotacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.service.PautaService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/votacao")
public class Votacao {

    @Autowired
    PautaService pautaService;

  //  @PostMapping("/pauta/cadastrar_pauta")
    @PostMapping(path = "/pauta/cadastrar_pauta", consumes = "application/json", produces = "application/json")
    public void cadastrarPauta(@RequestBody Pauta pauta) {
        pautaService.savePauta(pauta);
    }

    @GetMapping("/pauta/{id}")
    public ResponseEntity<Pauta> get(@PathVariable Integer id) {
        try {
            Pauta pauta = pautaService.getPauta(id);
            return new ResponseEntity<>(pauta, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
