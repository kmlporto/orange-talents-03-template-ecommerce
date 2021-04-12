package br.com.zup.desafios.ecommerce.compra.ranking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.zup.desafios.ecommerce.util.Path.RANKING;

@RestController
@RequestMapping(value = RANKING)
public class RankingController {

    @PostMapping
    public ResponseEntity<String> registra(@Valid @RequestBody RankingPersist rankingPersist) throws InterruptedException {
        String mensagem = "\nVendedor " + rankingPersist.getIdVendedor() + " registrado no ranking de vendas";
        System.out.println(mensagem);
        Thread.sleep(200);
        return ResponseEntity.ok(mensagem);
    }
}
