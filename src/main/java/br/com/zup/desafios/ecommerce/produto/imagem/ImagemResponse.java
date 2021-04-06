package br.com.zup.desafios.ecommerce.produto.imagem;

public class ImagemResponse {
    private Long id;
    private String link;

    public ImagemResponse(Long id, String link) {
        this.id = id;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
