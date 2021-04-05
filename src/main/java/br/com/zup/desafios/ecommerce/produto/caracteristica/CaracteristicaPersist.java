package br.com.zup.desafios.ecommerce.produto.caracteristica;

public class CaracteristicaPersist {
    private String nome;
    private String descricao;

    public CaracteristicaPersist(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica convert() {
        return new Caracteristica(nome, descricao);
    }
}
