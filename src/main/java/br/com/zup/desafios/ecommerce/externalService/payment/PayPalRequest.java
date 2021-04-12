package br.com.zup.desafios.ecommerce.externalService.payment;

import br.com.zup.desafios.ecommerce.compra.Compra;
import br.com.zup.desafios.ecommerce.compra.transacao.Transacao;
import br.com.zup.desafios.ecommerce.compra.transacao.TransacaoStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PayPalRequest implements PaymentRequest{
    @Min(0)
    @Max(1)
    @NotNull
    private int status;
    @NotEmpty
    private String idTransacao;

    public PayPalRequest(@Min(0) @Max(1) @NotNull int status, @NotEmpty String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao convert(Compra compra){
        TransacaoStatus transacaoStatus = TransacaoStatus.get(status);
        return new Transacao(transacaoStatus, idTransacao, compra);
    }

}
