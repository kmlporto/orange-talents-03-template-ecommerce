package br.com.zup.desafios.ecommerce.produto.detalhe;

import br.com.zup.desafios.ecommerce.produto.caracteristica.Caracteristica;

public class ProdutoCaracteristicaReponse {
    private String nome;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public ProdutoCaracteristicaReponse(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }
}
