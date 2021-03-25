package br.com.zup.desafios.ecommerce.autenticacao;

public class TokenReponse {
    private String token;
    private String tipo;

    public TokenReponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
