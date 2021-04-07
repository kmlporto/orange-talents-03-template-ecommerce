package br.com.zup.desafios.ecommerce.produto.imagem.detalhe;

import br.com.zup.desafios.ecommerce.produto.Produto;
import br.com.zup.desafios.ecommerce.produto.caracteristica.Caracteristica;
import br.com.zup.desafios.ecommerce.produto.imagem.Imagem;
import br.com.zup.desafios.ecommerce.produto.opiniao.Opinioes;
import br.com.zup.desafios.ecommerce.produto.pergunta.Pergunta;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDetailResponse {

    private String nome;
    private String descricao;
    private BigDecimal valor;
    private List<String> linksImagens;
    private List<ProdutoCaracteristicaReponse> caracteristicas;
    private double mediaNota;
    private int totalNotas;
    private List<ProdutoPerguntaResponse> perguntas;
    private List<ProdutoOpiniaoResponse> opinioes;

    public ProdutoDetailResponse(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.linksImagens = linksResponse(produto.getImagens());
        this.caracteristicas = caracteristicasResponse(produto.getCaracteristicas());
        this.perguntas = perguntasResponse(produto.getPerguntas());
        Opinioes opinioes = produto.getOpinioes();
        this.totalNotas = opinioes.totalNotas();
        this.mediaNota = opinioes.media();
        this.opinioes = opinioes.getOpinioes();
    }

    public static ProdutoDetailResponse convert(Produto produto) {
        return new ProdutoDetailResponse(produto);
    }

    private List<String> linksResponse(List<Imagem>imagems){
        return imagems.stream().map(Imagem::getLink).collect(Collectors.toList());
    }

    private List<ProdutoCaracteristicaReponse> caracteristicasResponse(Set<Caracteristica> caracteristicas){
        return caracteristicas.stream().map(ProdutoCaracteristicaReponse::new).collect(Collectors.toList());
    }

    private List<ProdutoPerguntaResponse> perguntasResponse(List<Pergunta> perguntas){
        return perguntas.stream().map(ProdutoPerguntaResponse::new).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public List<String> getLinksImagens() {
        return linksImagens;
    }

    public List<ProdutoCaracteristicaReponse> getCaracteristicas() {
        return caracteristicas;
    }

    public double getMediaNota() {
        return mediaNota;
    }

    public int getTotalNotas() {
        return totalNotas;
    }

    public List<ProdutoPerguntaResponse> getPerguntas() {
        return perguntas;
    }

    public List<ProdutoOpiniaoResponse> getOpinioes() {
        return opinioes;
    }
}
