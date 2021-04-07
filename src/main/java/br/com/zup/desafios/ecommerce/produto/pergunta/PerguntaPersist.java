package br.com.zup.desafios.ecommerce.produto.pergunta;

import br.com.zup.desafios.ecommerce.produto.Produto;
import br.com.zup.desafios.ecommerce.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class PerguntaPersist {
    @NotBlank
    private String titulo;

    @JsonCreator
    public PerguntaPersist(@JsonProperty("titulo") @NotBlank String titulo) {
        this.titulo = titulo;
    }

    public Pergunta convert(Usuario usuario, Produto produto){
        return new Pergunta(titulo, usuario, produto);
    }
}
