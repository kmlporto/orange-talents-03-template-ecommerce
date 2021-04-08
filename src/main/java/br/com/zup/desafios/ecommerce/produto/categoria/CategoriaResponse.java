package br.com.zup.desafios.ecommerce.produto.categoria;

import java.util.Objects;

public class CategoriaResponse {
    private Long id;
    private String nome;
    private Long categoriaMae;

    public CategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.categoriaMae = Objects.isNull(categoria.getCategoriaMae()) ? null : categoria.getCategoriaMae().getId();
    }

    public static CategoriaResponse convert(Categoria categoria) {
        return new CategoriaResponse(categoria);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMae() {
        return categoriaMae;
    }
}
