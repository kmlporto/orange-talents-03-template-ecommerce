package br.com.zup.desafios.ecommerce.produto.pergunta;

public class PerguntaResponse {
    private Long id;
    private String titulo;
    private Long idProduto;
    private Long idUsuario;

    public PerguntaResponse(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.titulo = pergunta.getTitulo();
        this.idProduto = pergunta.getProduto().getId();
        this.idUsuario = pergunta.getUsuario().getId();
    }

    public static PerguntaResponse convert(Pergunta pergunta) {
        return new PerguntaResponse(pergunta);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
