package br.com.zup.desafios.ecommerce.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoResponse {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private int quantidadeDisponivel;
    private List<Long> caracteristicas_id = new ArrayList<>();
    private String descricao;
    private Long categoria_id;
    private LocalDateTime dataCriacao;
    private Long dono_id;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
        this.caracteristicas_id = produto.getCaracteristicas().stream().map(caracteristica -> caracteristica.getId()).collect(Collectors.toList());
        this.descricao = produto.getDescricao();
        this.categoria_id = produto.getCategoria().getId();
        this.dataCriacao = produto.getDataCriacao();
        this.dono_id = produto.getDono().getId();
    }

    public static ProdutoResponse convert(Produto produto) {
        return new ProdutoResponse(produto);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public List<Long> getCaracteristicas_id() {
        return caracteristicas_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoria_id() {
        return categoria_id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Long getDono_id() {
        return dono_id;
    }
}
