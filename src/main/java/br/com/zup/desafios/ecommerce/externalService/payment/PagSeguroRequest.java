package br.com.zup.desafios.ecommerce.externalService.payment;

import br.com.zup.desafios.ecommerce.compra.Compra;
import br.com.zup.desafios.ecommerce.compra.transacao.Transacao;
import br.com.zup.desafios.ecommerce.compra.transacao.TransacaoStatus;

import javax.validation.constraints.NotNull;

public class PagSeguroRequest implements PaymentRequest{
    @NotNull
    private PagSeguroPayStatus status;
    @NotNull
    private String idTransacao;

    public PagSeguroRequest(@NotNull PagSeguroPayStatus status, @NotNull String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    @Override
    public Transacao convert(Compra compra) {
        TransacaoStatus transacaoStatus = status.convert();
        return new Transacao(transacaoStatus, idTransacao, compra);
    }
}
