package br.com.zup.desafios.ecommerce.produto;

import br.com.zup.desafios.ecommerce.categoria.Categoria;
import br.com.zup.desafios.ecommerce.produto.caracteristica.Caracteristica;
import br.com.zup.desafios.ecommerce.produto.imagem.Imagem;
import br.com.zup.desafios.ecommerce.produto.opiniao.Opiniao;
import br.com.zup.desafios.ecommerce.usuario.Usuario;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private int quantidadeDisponivel;
    @ManyToMany
    private Set<Caracteristica> caracteristicas;
    @Column(nullable = false, length = 1000)
    private String descricao;
    @ManyToOne
    private Categoria categoria;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Imagem> imagens;
    @ManyToOne
    private Usuario dono;
    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, int quantidadeDisponivel, Set<Caracteristica> caracteristicas, String descricao, Categoria categoria, Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
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

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public Usuario getDono() {
        return dono;
    }

    public void addImagens(Set<String> links) {
        Set<Imagem> collect = links.stream().map(link -> new Imagem(this, link)).collect(Collectors.toSet());
        imagens.addAll(collect);
    }

    public boolean pertenceAo(Usuario usuarioLogado) {
        return dono.equals(usuarioLogado);
    }
}
