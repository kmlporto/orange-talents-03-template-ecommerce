package br.com.zup.desafios.ecommerce.externalService.payment;

import br.com.zup.desafios.ecommerce.compra.transacao.Transacao;
import br.com.zup.desafios.ecommerce.compra.transacao.TransacaoStatus;

public class PaymentResponse {

    private TransacaoStatus status;

    public PaymentResponse(Transacao transacao) {
        this.status = transacao.getStatus();
    }

    public TransacaoStatus getStatus() {
        return status;
    }

}
