package br.com.zup.desafios.ecommerce.compra.notaFiscal;

import javax.validation.constraints.NotNull;

public class NotaFiscalPersist {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idUsuario;

    public NotaFiscalPersist(@NotNull Long idCompra, @NotNull Long idUsuario) {
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
