package br.com.zup.desafios.ecommerce.externalService.gateway.pagamento;

import br.com.zup.desafios.ecommerce.compra.Compra;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class GatewayFake {

    private final GatewayPagamentoFactory pagamentoFactory;

    public GatewayFake(GatewayPagamentoFactory pagamentoFactory) {
        this.pagamentoFactory = pagamentoFactory;
    }

    public String pagar(Compra compra, URI urlRetorno){
        GatewayPagamento gatewayPagamento = pagamentoFactory.getGatewayPagamento(compra.getTipoGateway());
        return gatewayPagamento.pagar(compra.getUuid(), urlRetorno);
    }

}
