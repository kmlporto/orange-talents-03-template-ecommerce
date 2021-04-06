package br.com.zup.desafios.ecommerce.produto.imagem;

import br.com.zup.desafios.ecommerce.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produto produto;
    @URL
    @Column(nullable = false)
    private String link;

    @Deprecated
    public Imagem() {
    }

    public Imagem(@NotNull Produto produto, @URL @NotBlank String link) {
        this.produto = produto;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
