package br.com.zup.desafios.ecommerce.compra.ranking;

import com.sun.istack.NotNull;

public class RankingPersist {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public RankingPersist(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }
}
