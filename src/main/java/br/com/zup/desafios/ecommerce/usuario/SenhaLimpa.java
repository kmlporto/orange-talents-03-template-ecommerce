package br.com.zup.desafios.ecommerce.usuario;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SenhaLimpa {
    private final String senha;

    public SenhaLimpa(@NotNull @Size(min = 6) String senha, PasswordEncoder passwordEncoder) {
        this.senha = passwordEncoder.encode(senha);
    }

    public String hash() {
        return senha;
    }
}
