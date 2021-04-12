package br.com.zup.desafios.ecommerce.compra.transacao;

import br.com.zup.desafios.ecommerce.compra.Compra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TransacaoStatus status;

    @PastOrPresent
    private LocalDateTime dataTransacao = LocalDateTime.now();

    @ManyToOne
    private Compra compra;

    @Column(nullable = false)
    private String idTransacaoGateway;

    @Deprecated
    public Transacao() {
    }

    public Transacao(@NotNull TransacaoStatus status, @NotNull String idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
    }

    public TransacaoStatus getStatus() {
        return status;
    }

    public boolean comSucesso() {
        return this.status.equals(TransacaoStatus.SUCESSO);
    }

    public Compra getCompra() {
        return compra;
    }
}
