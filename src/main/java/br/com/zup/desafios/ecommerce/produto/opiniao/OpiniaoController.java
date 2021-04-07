package br.com.zup.desafios.ecommerce.produto.opiniao;

import br.com.zup.desafios.ecommerce.produto.Produto;
import br.com.zup.desafios.ecommerce.produto.ProdutoRepository;
import br.com.zup.desafios.ecommerce.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.zup.desafios.ecommerce.util.Path.ID;
import static br.com.zup.desafios.ecommerce.util.Path.OPINIOES;
import static br.com.zup.desafios.ecommerce.util.Path.PRODUTOS;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@RestController
@RequestMapping(V1 + PRODUTOS)
public class OpiniaoController {
    private final ProdutoRepository produtoRepository;
    private final OpiniaoRepository opiniaoRepository;

    public OpiniaoController(ProdutoRepository produtoRepository, OpiniaoRepository opiniaoRepository) {
        this.produtoRepository = produtoRepository;
        this.opiniaoRepository = opiniaoRepository;
    }

    @PostMapping(value = ID + OPINIOES)
    public ResponseEntity<OpiniaoResponse> adicionaOpiniao(@PathVariable Long id, @RequestBody @Valid OpiniaoPersist opiniaoPersist, @AuthenticationPrincipal Usuario usuarioLogado){
        if(!produtoRepository.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        Produto produto = produtoRepository.getOne(id);
        Opiniao opiniao = opiniaoRepository.save(opiniaoPersist.convert(usuarioLogado, produto));

        return ResponseEntity.ok(OpiniaoResponse.convert(opiniao));
    }
}
