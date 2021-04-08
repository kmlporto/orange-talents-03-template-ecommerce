package br.com.zup.desafios.ecommerce.produto.detalhe;

import br.com.zup.desafios.ecommerce.produto.pergunta.Pergunta;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoPerguntaResponse {
    private String titulo;

    @JsonCreator
    public ProdutoPerguntaResponse(@JsonProperty("titulo") Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }
}
