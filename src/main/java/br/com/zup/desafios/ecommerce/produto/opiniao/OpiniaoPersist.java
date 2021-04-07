package br.com.zup.desafios.ecommerce.produto.opiniao;

import br.com.zup.desafios.ecommerce.produto.Produto;
import br.com.zup.desafios.ecommerce.usuario.Usuario;

import javax.validation.constraints.*;

public class OpiniaoPersist {

    @Max(5)
    @Min(0)
    @NotNull
    private Long nota;
    @NotEmpty
    private String titulo;
    @NotEmpty
    @Size(max = 500)
    private String descricao;

    public OpiniaoPersist(@Max(5) @Min(1) @NotNull Long nota, @NotEmpty String titulo, @NotEmpty @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }


    public Opiniao convert(@NotNull Usuario usuarioComprador, @NotNull Produto produto) {
        return new Opiniao(nota, titulo, descricao, usuarioComprador, produto);
    }

}
