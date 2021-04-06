package br.com.zup.desafios.ecommerce.produto;

import br.com.zup.desafios.ecommerce.categoria.Categoria;
import br.com.zup.desafios.ecommerce.categoria.CategoriaRepository;
import br.com.zup.desafios.ecommerce.produto.caracteristica.Caracteristica;
import br.com.zup.desafios.ecommerce.produto.caracteristica.CaracteristicaRepository;
import br.com.zup.desafios.ecommerce.produto.imagem.ImagemPersist;
import br.com.zup.desafios.ecommerce.produto.imagem.ImagemProdutoResponse;
import br.com.zup.desafios.ecommerce.produto.imagem.upload.UploaderImage;
import br.com.zup.desafios.ecommerce.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static br.com.zup.desafios.ecommerce.util.Path.ID;
import static br.com.zup.desafios.ecommerce.util.Path.IMAGENS;
import static br.com.zup.desafios.ecommerce.util.Path.PRODUTOS;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@RestController
@RequestMapping(V1 + PRODUTOS)
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CaracteristicaRepository caracteristicaRepository;
    private final CategoriaRepository categoriaRepository;
    private final UploaderImage uploaderImage;

    public ProdutoController(ProdutoRepository produtoRepository, CaracteristicaRepository caracteristicaRepository, CategoriaRepository categoriaRepository, UploaderImage uploaderImage) {
        this.produtoRepository = produtoRepository;
        this.caracteristicaRepository = caracteristicaRepository;
        this.categoriaRepository = categoriaRepository;
        this.uploaderImage = uploaderImage;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastra(@RequestBody @Valid ProdutoPersist produtoPersist){
        Usuario dono = getUsuario();
        List<Caracteristica> caracteristicas = caracteristicaRepository.findAllById(produtoPersist.getCaracteristicas_id());

        Categoria categoria = categoriaRepository.getOne(produtoPersist.getCategoria_id());
        Produto produto = produtoRepository.save(produtoPersist.convert(Set.copyOf(caracteristicas), categoria, dono));

        return ResponseEntity.ok(ProdutoResponse.convert(produto));
    }

    @PostMapping(value = ID + IMAGENS)
    public ResponseEntity<?> adicionaImagens(@PathVariable Long id, @Valid ImagemPersist imagemPersist){
        Optional<Produto> optional = produtoRepository.findById(id);
        Usuario usuarioLogado = getUsuario();

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Produto produto = optional.get();

        if(!produto.pertenceAo(usuarioLogado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Set<String> links = uploaderImage.envia(imagemPersist.getImagens());
        produto.addImagens(links);

        produto = produtoRepository.save(produto);

        return ResponseEntity.ok(ImagemProdutoResponse.convert(produto));
    }

    public Usuario getUsuario(){
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
