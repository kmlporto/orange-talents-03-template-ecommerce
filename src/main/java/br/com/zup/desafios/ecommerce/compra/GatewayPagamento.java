package br.com.zup.desafios.ecommerce.compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {
    PAYPAL("paypal.com/", "/retorno-paypal/{id}"),
    PAGSEGURO("pagseguro.com/", "/retorno-pagseguro/{id}");

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
