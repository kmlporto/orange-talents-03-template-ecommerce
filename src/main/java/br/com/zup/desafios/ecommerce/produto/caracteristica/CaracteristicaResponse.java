package br.com.zup.desafios.ecommerce.produto.caracteristica;

public class CaracteristicaResponse {
    private Long id;
    private String nome;
    private String descricao;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaResponse(Caracteristica caracteristica) {
        this.id = caracteristica.getId();
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public static CaracteristicaResponse convert(Caracteristica caracteristica){
        return new CaracteristicaResponse(caracteristica);
    }

}
