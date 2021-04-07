package br.com.zup.desafios.ecommerce.produto.opiniao;

public class OpiniaoResponse {
    private Long id;
    private Long nota;
    private String titulo;
    private String descricao;
    private Long idProduto;
    private Long idUsuario;

    public OpiniaoResponse(Opiniao opiniao) {
        this.id = opiniao.getId();
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.idProduto = opiniao.getProduto().getId();
        this.idUsuario = opiniao.getUsuario().getId();
    }

    public static OpiniaoResponse convert(Opiniao opiniao) {
        return new OpiniaoResponse(opiniao);
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

    public Long getIdProduto() {
        return idProduto;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
