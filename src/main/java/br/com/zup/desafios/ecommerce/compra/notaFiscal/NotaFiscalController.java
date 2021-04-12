package br.com.zup.desafios.ecommerce.compra.notaFiscal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.zup.desafios.ecommerce.util.Path.NOTA_FISCAL;

@RestController
@RequestMapping(value = NOTA_FISCAL)
public class NotaFiscalController {

    @PostMapping
    public ResponseEntity<String> criaNota(@Valid @RequestBody NotaFiscalPersist notaFiscalPersist) throws InterruptedException {
        String nota = "\n==================== Nota Fiscal aqui ====================" +
                "\nidComra: " + notaFiscalPersist.getIdCompra() +
                "\nidUsuario: " + notaFiscalPersist.getIdUsuario() +
                "\n==========================================================";
        System.out.println(nota);
        Thread.sleep(200);
        return ResponseEntity.ok(nota);
    }
}
