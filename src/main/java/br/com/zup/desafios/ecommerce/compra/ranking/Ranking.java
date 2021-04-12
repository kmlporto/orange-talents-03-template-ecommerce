package br.com.zup.desafios.ecommerce.compra.ranking;

import br.com.zup.desafios.ecommerce.compra.Compra;
import br.com.zup.desafios.ecommerce.compra.EventoCompraSucesso;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static br.com.zup.desafios.ecommerce.util.Path.RANKING;

@Component
public class Ranking implements EventoCompraSucesso {
    private String URL_RANKING = "http://localhost:8080" + RANKING;

    @Override
    public void process(Compra compra){
        Set<Long> idsVendedores = compra.getIdsDonos();
        idsVendedores.forEach(idVendedor -> {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForEntity(URL_RANKING, new RankingPersist(compra.getId(), idVendedor), String.class);
        });
    }

}
