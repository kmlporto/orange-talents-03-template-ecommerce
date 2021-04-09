package br.com.zup.desafios.ecommerce.compra;

import br.com.zup.desafios.ecommerce.compra.item.Item;
import br.com.zup.desafios.ecommerce.compra.item.ItemPersist;
import br.com.zup.desafios.ecommerce.produto.ProdutoRepository;
import br.com.zup.desafios.ecommerce.usuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class CompraPersist {
    @Valid
    @NotEmpty
    private List<ItemPersist> itens;

    @NotNull
    private GatewayPagamento pagamento;

    public List<ItemPersist> getItens() {
        return itens;
    }

    public CompraPersist(@Valid @NotEmpty List<ItemPersist> itens, @NotNull GatewayPagamento pagamento) {
        this.itens = itens;
        this.pagamento = pagamento;
    }

    public Compra convert(ProdutoRepository produtoRepository, Usuario comprador) {
        List<Item> items = itens.stream().map(item -> item.convert(produtoRepository)).collect(Collectors.toList());
        return new Compra(items, pagamento, comprador);
    }
}
