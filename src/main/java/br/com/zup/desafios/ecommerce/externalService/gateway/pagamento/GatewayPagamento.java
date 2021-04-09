package br.com.zup.desafios.ecommerce.externalService.gateway.pagamento;

import java.net.URI;
import java.util.UUID;

public interface GatewayPagamento {
    String pagar(UUID uuid, URI urlRetorno);
}
