package br.com.zup.desafios.ecommerce.produto.imagem.detalhe;

import br.com.zup.desafios.ecommerce.produto.opiniao.Opiniao;

public class ProdutoOpiniaoResponse {

    private Long nota;
    private String titulo;
    private String descricao;

    public ProdutoOpiniaoResponse(Opiniao opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public Long getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
