package br.com.zup.desafios.ecommerce.categoria;

import br.com.zup.desafios.ecommerce.util.annotation.Exist;
import br.com.zup.desafios.ecommerce.util.annotation.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoriaPersist {
    @NotBlank
    @UniqueValue(clazz = Categoria.class, field = "nome")
    private String nome;
    @Exist(clazz = Categoria.class, field = "id")
    private Long categoria_id;

    public CategoriaPersist(@NotBlank String nome, @NotNull Long categoria_id) {
        this.nome = nome;
        this.categoria_id = categoria_id;
    }

    public Categoria convert(Categoria categoriaMae) {
        return new Categoria(nome, categoriaMae);
    }

    public Long getCategoria_id() {
        return categoria_id;
    }
}
