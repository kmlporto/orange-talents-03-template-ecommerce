package br.com.zup.desafios.ecommerce.produto;

import br.com.zup.desafios.ecommerce.produto.categoria.Categoria;
import br.com.zup.desafios.ecommerce.produto.caracteristica.Caracteristica;
import br.com.zup.desafios.ecommerce.usuario.Usuario;
import br.com.zup.desafios.ecommerce.util.annotation.Exist;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

public class ProdutoPersist {
    @NotBlank
    private String nome;
    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal valor;
    @NotNull
    @Min(value = 0)
    private int quantidadeDisponivel;
    @NotNull
    @Size(min = 3)
    @Exist(clazz = Caracteristica.class, field = "id")
    private Set<Long> caracteristicas_id;
    @NotBlank
    private String descricao;
    @NotNull
    @Exist(clazz = Categoria.class, field = "id")
    private Long categoria_id;

    public Produto convert(Set<Caracteristica> caracteristicas, Categoria categoria, Usuario dono) {
        return new Produto(nome, valor, quantidadeDisponivel, caracteristicas, descricao, categoria, dono);
    }

    public ProdutoPersist(@NotBlank String nome, @NotNull @DecimalMin(value = "0.00", inclusive = false) BigDecimal valor, @NotNull @Min(value = 0) int quantidadeDisponivel, @NotNull @Size(min = 3) Set<Long> caracteristicas_id, @NotBlank String descricao, @NotNull Long categoria_id) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas_id = caracteristicas_id;
        this.descricao = descricao;
        this.categoria_id = categoria_id;
    }

    public Set<Long> getCaracteristicas_id() {
        return caracteristicas_id;
    }

    public Long getCategoria_id() {
        return categoria_id;
    }
}
