package br.com.zup.desafios.ecommerce.compra;

import br.com.zup.desafios.ecommerce.externalService.gateway.pagamento.GatewayFake;
import br.com.zup.desafios.ecommerce.produto.ProdutoRepository;
import br.com.zup.desafios.ecommerce.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;

import static br.com.zup.desafios.ecommerce.util.Path.COMPRAS;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@RestController
@RequestMapping(value = V1 + COMPRAS)
public class CompraController {

    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;
    private final GatewayFake gatewayFake;

    public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository, GatewayFake gatewayFake) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
        this.gatewayFake = gatewayFake;
    }

    @PostMapping
    public ResponseEntity<?> compra(@Valid @RequestBody CompraPersist compraPersist, @AuthenticationPrincipal Usuario usuarioLogado){
        Compra compra = compraPersist.convert(produtoRepository, usuarioLogado);
        compra = compraRepository.save(compra.abaterEstoque());

        URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(compra.getId()).toUri();
        String mensagem = gatewayFake.pagar(compra, url);

        return new ResponseEntity<>(mensagem, HttpStatus.FOUND);
    }

}
