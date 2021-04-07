package br.com.zup.desafios.ecommerce.produto;

import br.com.zup.desafios.ecommerce.categoria.Categoria;
import br.com.zup.desafios.ecommerce.categoria.CategoriaRepository;
import br.com.zup.desafios.ecommerce.produto.caracteristica.Caracteristica;
import br.com.zup.desafios.ecommerce.produto.caracteristica.CaracteristicaRepository;
import br.com.zup.desafios.ecommerce.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;
import java.util.Set;

import static br.com.zup.desafios.ecommerce.util.Path.PRODUTOS;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@RestController
@RequestMapping(V1 + PRODUTOS)
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CaracteristicaRepository caracteristicaRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoController(ProdutoRepository produtoRepository, CaracteristicaRepository caracteristicaRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.caracteristicaRepository = caracteristicaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastra(@RequestBody @Valid ProdutoPersist produtoPersist, @AuthenticationPrincipal Usuario usuarioLogado){
        List<Caracteristica> caracteristicas = caracteristicaRepository.findAllById(produtoPersist.getCaracteristicas_id());

        Categoria categoria = categoriaRepository.getOne(produtoPersist.getCategoria_id());
        Produto produto = produtoRepository.save(produtoPersist.convert(Set.copyOf(caracteristicas), categoria, usuarioLogado));

        return ResponseEntity.ok(ProdutoResponse.convert(produto));
    }

}
