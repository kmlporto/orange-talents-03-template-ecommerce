package br.com.zup.desafios.ecommerce.externalService.gateway.pagamento;

import java.net.URI;
import java.util.UUID;

public class GatewayPagamentoPayPal implements GatewayPagamento {
    @Override
    public String pagar(UUID uuid, URI urlRetorno) {
        return "paypal.com?buyerId={" + uuid + "}&redirectUrl={"+urlRetorno+"}";
    }
}
