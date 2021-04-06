package br.com.zup.desafios.ecommerce.produto.imagem;

import br.com.zup.desafios.ecommerce.produto.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ImagemProdutoResponse {
    private Long idProduto;
    private List<ImagemResponse> imagens;

    public ImagemProdutoResponse(Long idProduto, List<ImagemResponse> imagens) {
        this.idProduto = idProduto;
        this.imagens = imagens;
    }

    public static ImagemProdutoResponse convert(Produto produto) {
        List<ImagemResponse> imagemResponses = produto.getImagens().stream().map(i -> new ImagemResponse(i.getId(), i.getLink())).collect(Collectors.toList());
        return new ImagemProdutoResponse(produto.getId(), imagemResponses);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public List<ImagemResponse> getImagens() {
        return imagens;
    }
}
