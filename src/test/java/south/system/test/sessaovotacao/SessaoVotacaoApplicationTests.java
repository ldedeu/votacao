package south.system.test.sessaovotacao;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import south.system.test.sessaovotacao.model.Associado;
import south.system.test.sessaovotacao.model.Pauta;
import south.system.test.sessaovotacao.model.PautaAssociado;
import south.system.test.sessaovotacao.util.Erro;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SessaoVotacaoApplicationTests {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testCadastrarAssociadoSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/votacao/v1/associado/cadastrar_associado";
        URI uri = new URI(baseUrl);

        Associado associado = new Associado("Angela", "Carrasco", 23924714860L);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Associado> request = new HttpEntity<>(associado, headers);

        ResponseEntity<Associado> result = restTemplate.postForEntity(uri, request, Associado.class);

        //Verify request succeed
        Assertions.assertEquals(201, result.getStatusCodeValue());
    }

}
