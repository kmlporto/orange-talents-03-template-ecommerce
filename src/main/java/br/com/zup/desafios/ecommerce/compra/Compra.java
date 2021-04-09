package br.com.zup.desafios.ecommerce.compra;

import br.com.zup.desafios.ecommerce.compra.item.Item;
import br.com.zup.desafios.ecommerce.usuario.Usuario;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid = UUID.randomUUID();
    @Enumerated(value = EnumType.STRING)
    private CompraStatus compraStatus = CompraStatus.INICIADA;
    @Enumerated(value = EnumType.STRING)
    private GatewayPagamento gatewayPagamento;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Item> itens;
    @ManyToOne
    private Usuario comprador;

    @Deprecated
    public Compra() {
    }

    public Compra(List<Item> itens, GatewayPagamento gatewayPagamento, Usuario comprador) {
        this.itens = itens;
        this.gatewayPagamento = gatewayPagamento;
        this.comprador = comprador;
    }

    public Long getId() {
        return id;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Compra abaterEstoque(){
        this.itens.forEach(item -> item.getProduto().abaterEstoque(item.getQuantidade()));
        return this;
    }

}
