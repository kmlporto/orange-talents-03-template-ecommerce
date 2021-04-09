package br.com.zup.desafios.ecommerce.compra;

import br.com.zup.desafios.ecommerce.produto.ProdutoRepository;
import br.com.zup.desafios.ecommerce.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static br.com.zup.desafios.ecommerce.util.Path.COMPRAS;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@RestController
@RequestMapping(value = V1 + COMPRAS)
public class CompraController {

    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;

    public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<?> compra(@Valid @RequestBody CompraPersist compraPersist, @AuthenticationPrincipal Usuario usuarioLogado, UriComponentsBuilder uriComponentsBuilder){
        Compra compra = compraPersist.convert(produtoRepository, usuarioLogado);
        compra = compraRepository.save(compra.abaterEstoque());

        GatewayPagamento gatewayPagamento = compra.getGatewayPagamento();
        String mensagem = gatewayPagamento.criaUrlRetorno(compra, uriComponentsBuilder);

        return new ResponseEntity<>(mensagem, HttpStatus.FOUND);
    }

}
