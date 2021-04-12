package br.com.zup.desafios.ecommerce.compra.notaFiscal;

import br.com.zup.desafios.ecommerce.compra.Compra;
import br.com.zup.desafios.ecommerce.compra.EventoCompraSucesso;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static br.com.zup.desafios.ecommerce.util.Path.NOTA_FISCAL;

@Component
public class NotaFiscal implements EventoCompraSucesso {

    private String URL_RANKING = "http://localhost:8080" + NOTA_FISCAL;

    @Override
    public void process(Compra compra){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(URL_RANKING, new NotaFiscalPersist(compra.getId(), compra.getComprador().getId()), String.class);
    }

}
