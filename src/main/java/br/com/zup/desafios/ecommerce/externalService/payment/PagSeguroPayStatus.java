package br.com.zup.desafios.ecommerce.externalService.payment;

import br.com.zup.desafios.ecommerce.compra.transacao.TransacaoStatus;

public enum PagSeguroPayStatus {
    ERRO, SUCESSO;

    public TransacaoStatus convert() {
        if (this.equals(TransacaoStatus.SUCESSO)){
            return TransacaoStatus.SUCESSO;
        }else{
            return TransacaoStatus.ERRO;
        }
    }
}
