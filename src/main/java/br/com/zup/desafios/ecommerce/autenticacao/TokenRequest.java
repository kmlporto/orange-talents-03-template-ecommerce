package br.com.zup.desafios.ecommerce.autenticacao;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

public class TokenRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String senha;

    public TokenRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter(PasswordEncoder passwordEncoder) {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}
