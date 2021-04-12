package br.com.zup.desafios.ecommerce.compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {
    PAYPAL("paypal.com/", "/pagamentos/compras/{id}/paypal"),
    PAGSEGURO("pagseguro.com/", "/pagamentos/compras/{id}/pagseguro");

    private String url;
    private String retorno;

    GatewayPagamento(String url, String retorno) {
        this.url = url;
        this.retorno = retorno;
    }

    public String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder){
        String urlRetorno = uriComponentsBuilder
                .path(retorno).buildAndExpand(compra.getId())
                .toString();

        return url + compra.getUuid() + "?redirectUrl=" + urlRetorno;
    }

}
