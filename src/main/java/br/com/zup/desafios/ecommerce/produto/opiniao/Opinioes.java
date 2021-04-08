package br.com.zup.desafios.ecommerce.produto.opiniao;

import br.com.zup.desafios.ecommerce.produto.detalhe.ProdutoOpiniaoResponse;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Opinioes {

    private List<Opiniao> opinioes;

    public Opinioes(List<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public int totalNotas(){
        return opinioes.size();
    }

    public double media(){
        OptionalDouble possivelMedia = opinioes.stream().mapToLong(opiniao -> opiniao.getNota()).average();
        return possivelMedia.orElse(0.0);
    }

    public List<ProdutoOpiniaoResponse> getOpinioes() {
        return opinioes.stream().map(ProdutoOpiniaoResponse::new).collect(Collectors.toList());
    }
}
