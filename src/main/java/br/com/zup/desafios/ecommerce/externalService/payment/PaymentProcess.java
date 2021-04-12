package br.com.zup.desafios.ecommerce.externalService.payment;

import br.com.zup.desafios.ecommerce.compra.Compra;
import br.com.zup.desafios.ecommerce.compra.CompraRepository;
import br.com.zup.desafios.ecommerce.compra.EventoCompraSucesso;
import br.com.zup.desafios.ecommerce.compra.GatewayPagamento;
import br.com.zup.desafios.ecommerce.externalService.email.SenderEmail;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@Component
public class PaymentProcess {

    private final CompraRepository compraRepository;
    private final Set<EventoCompraSucesso> eventoCompraSucesso;
    private final SenderEmail senderEmail;

    public PaymentProcess(CompraRepository compraRepository, Set<EventoCompraSucesso> eventoCompraSucesso, SenderEmail senderEmail) {
        this.compraRepository = compraRepository;
        this.eventoCompraSucesso = eventoCompraSucesso;
        this.senderEmail = senderEmail;
    }

    public void process(Compra compra, boolean transacaoAdicionada, UriComponentsBuilder uriLocal){
        compra = compraRepository.save(compra);

        if(transacaoAdicionada && compra.processadaComSucesso()){
            Compra finalCompra = compra;
            eventoCompraSucesso.forEach(evento -> evento.process(finalCompra));
        }else if(!compra.processadaComSucesso()){
            GatewayPagamento gatewayPagamento = compra.getGatewayPagamento();
            senderEmail.emailPagamentoFalhou(compra, gatewayPagamento.criaUrlRetorno(compra, uriLocal));
        }
    }

}
