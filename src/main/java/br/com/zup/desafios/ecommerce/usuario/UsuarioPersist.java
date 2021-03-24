package br.com.zup.desafios.ecommerce.usuario;

import br.com.zup.desafios.ecommerce.util.annotation.UniqueValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioPersist {
    @NotNull
    @Email
    @UniqueValue(clazz = Usuario.class, field = "login")
    @ApiModelProperty(position = 1, required = true)
    private String login;
    @NotNull
    @Size(min = 6)
    @ApiModelProperty(position = 2, required = true)
    private String senha;

    public UsuarioPersist(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario convert(PasswordEncoder passwordEncoder) {
        return new Usuario(login, new SenhaLimpa(senha, passwordEncoder));
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
