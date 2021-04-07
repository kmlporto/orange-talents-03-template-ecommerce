package br.com.zup.desafios.ecommerce.produto.opiniao;

import br.com.zup.desafios.ecommerce.produto.Produto;
import br.com.zup.desafios.ecommerce.usuario.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long nota;
    @Column(nullable = false)
    private String titulo;
    @Column(length = 500, nullable = false)
    private String descricao;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@Max(5) @Min(1) @NotNull Long nota, @NotEmpty String titulo, @NotEmpty String descricao, @NotNull Usuario usuario, @NotNull Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public Long getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
