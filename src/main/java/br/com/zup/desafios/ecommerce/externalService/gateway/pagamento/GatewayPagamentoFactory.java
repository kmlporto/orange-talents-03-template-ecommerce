package br.com.zup.desafios.ecommerce.externalService.gateway.pagamento;

import org.springframework.stereotype.Component;

@Component
public class GatewayPagamentoFactory {

    public GatewayPagamento getGatewayPagamento(TipoGatewayPagamento tipoGatewayPagamento){
        if(tipoGatewayPagamento.equals(TipoGatewayPagamento.PAYPAL)){
            return new GatewayPagamentoPayPal();
        }else if(tipoGatewayPagamento.equals(TipoGatewayPagamento.PAGSEGURO)){
            return new GatewayPagamentoPagSeguro();
        }
        return null;
    }
}
