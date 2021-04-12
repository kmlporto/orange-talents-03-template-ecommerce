package br.com.zup.desafios.ecommerce.externalService.payment;

import br.com.zup.desafios.ecommerce.compra.Compra;
import br.com.zup.desafios.ecommerce.compra.transacao.Transacao;

public interface PaymentRequest {
    Transacao convert(Compra compra);
}
