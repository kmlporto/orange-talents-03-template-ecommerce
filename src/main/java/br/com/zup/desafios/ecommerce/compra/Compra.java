package br.com.zup.desafios.ecommerce.compra;

import br.com.zup.desafios.ecommerce.compra.item.Item;
import br.com.zup.desafios.ecommerce.compra.transacao.Transacao;
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
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

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


    public Usuario getComprador() {
        return comprador;
    }

    public List<Item> getItens() {
        return itens;
    }

    public Set<Long> getIdsDonos(){
        return itens.stream().map(item -> item.getProduto().getDono().getId()).collect(Collectors.toSet());
    }

    public Compra abaterEstoque(){
        this.itens.forEach(item -> item.getProduto().abaterEstoque(item.getQuantidade()));
        return this;
    }

    public boolean adicionaTransacao(Transacao transacao) {
        if(!processadaComSucesso()){
            this.transacoes.add(transacao);
            atualizaStatusCompra(transacao);
            return true;
        }
        return false;
    }

    public void atualizaStatusCompra(Transacao transacao){
        if(transacao.comSucesso()){
            compraStatus = CompraStatus.SUCESSO;
        }else
            compraStatus = CompraStatus.FALHA;
    }

    public boolean processadaComSucesso(){
        return compraStatus.equals(CompraStatus.SUCESSO);
    }
}
