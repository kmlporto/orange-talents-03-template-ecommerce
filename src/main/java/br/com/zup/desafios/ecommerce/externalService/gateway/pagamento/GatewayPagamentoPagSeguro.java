package br.com.zup.desafios.ecommerce.externalService.gateway.pagamento;

import java.net.URI;
import java.util.UUID;

public class GatewayPagamentoPagSeguro implements GatewayPagamento {
    @Override
    public String pagar(UUID uuid, URI urlRetorno) {
        return "pagseguro.com?buyerId={" + uuid + "}&redirectUrl={"+ urlRetorno +"}";
    }
}
