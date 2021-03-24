package br.com.zup.desafios.ecommerce.usuario;

import java.time.LocalDateTime;

public class UsuarioResponse {
    private Long id;
    private String login;
    private LocalDateTime dataCriacao;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.dataCriacao = usuario.getDataCadastro();
    }

    public static UsuarioResponse convert(Usuario usuario) {
        return new UsuarioResponse(usuario);
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }


}
