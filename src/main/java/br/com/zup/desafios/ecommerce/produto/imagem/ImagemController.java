package br.com.zup.desafios.ecommerce.produto.imagem;

import br.com.zup.desafios.ecommerce.produto.Produto;
import br.com.zup.desafios.ecommerce.produto.ProdutoRepository;
import br.com.zup.desafios.ecommerce.externalService.uploader.image.UploaderImage;
import br.com.zup.desafios.ecommerce.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;

import static br.com.zup.desafios.ecommerce.util.Path.ID;
import static br.com.zup.desafios.ecommerce.util.Path.IMAGENS;
import static br.com.zup.desafios.ecommerce.util.Path.PRODUTOS;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@RestController
@RequestMapping(V1 + PRODUTOS)
public class ImagemController {

    private final ProdutoRepository produtoRepository;
    private final UploaderImage uploaderImage;

    public ImagemController(ProdutoRepository produtoRepository, UploaderImage uploaderImage) {
        this.produtoRepository = produtoRepository;
        this.uploaderImage = uploaderImage;
    }


    @PostMapping(value = ID + IMAGENS)
    public ResponseEntity<ImagemProdutoResponse> adicionaImagens(@PathVariable Long id, @Valid ImagemPersist imagemPersist, @AuthenticationPrincipal Usuario usuarioLogado){
        if(!produtoRepository.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        Produto produto = produtoRepository.getOne(id);

        if(!produto.pertenceAo(usuarioLogado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Set<String> links = uploaderImage.envia(imagemPersist.getImagens());
        produto.addImagens(links);

        produto = produtoRepository.save(produto);

        return ResponseEntity.ok(ImagemProdutoResponse.convert(produto));
    }
}
