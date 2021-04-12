package br.com.zup.desafios.ecommerce.compra.transacao;

public enum TransacaoStatus {
    ERRO, SUCESSO;

    public static TransacaoStatus get(int status){
        return TransacaoStatus.values()[status];
    }
}
