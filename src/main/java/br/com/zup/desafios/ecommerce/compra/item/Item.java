package br.com.zup.desafios.ecommerce.compra.item;

import br.com.zup.desafios.ecommerce.produto.Produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produto produto;
    @Column(nullable = false)
    private int quantidade;
    @Column(nullable = false)
    private BigDecimal valorProduto;

    @Deprecated
    public Item() {
    }

    public Item(@NotNull Produto produto, @NotNull int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorProduto = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

}
