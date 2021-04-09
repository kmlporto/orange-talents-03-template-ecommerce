package br.com.zup.desafios.ecommerce.compra.item;

import br.com.zup.desafios.ecommerce.produto.Produto;
import br.com.zup.desafios.ecommerce.produto.ProdutoRepository;
import br.com.zup.desafios.ecommerce.util.annotation.Exist;
import br.com.zup.desafios.ecommerce.util.annotation.HasStock;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@HasStock
public class ItemPersist {
    @NotNull
    @Exist(clazz = Produto.class, field = "id")
    private Long idProduto;

    @Positive
    @NotNull
    private int quantidade;

    public ItemPersist(@NotNull Long idProduto, @Positive @NotNull int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Item convert(ProdutoRepository produtoRepository){
        Produto produto = produtoRepository.getOne(idProduto);
        return new Item(produto, quantidade);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
