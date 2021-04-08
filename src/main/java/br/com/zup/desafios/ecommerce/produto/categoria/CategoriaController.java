package br.com.zup.desafios.ecommerce.produto.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

import static br.com.zup.desafios.ecommerce.util.Path.CATEGORIAS;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@RestController
@RequestMapping(V1 + CATEGORIAS)
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> cadastrar(@RequestBody @Valid CategoriaPersist categoriaPersist){
        Categoria categoriaMae = null;
        if(Objects.nonNull(categoriaPersist.getCategoria_id())){
            categoriaMae = categoriaRepository.getOne(categoriaPersist.getCategoria_id());
        }
        Categoria categoria = categoriaRepository.save(categoriaPersist.convert(categoriaMae));

        return ResponseEntity.ok(CategoriaResponse.convert(categoria));

    }
}
