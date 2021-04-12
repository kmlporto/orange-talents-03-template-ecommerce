package br.com.zup.desafios.ecommerce.externalService.payment;

import br.com.zup.desafios.ecommerce.compra.Compra;
import br.com.zup.desafios.ecommerce.compra.CompraRepository;
import br.com.zup.desafios.ecommerce.compra.transacao.Transacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static br.com.zup.desafios.ecommerce.util.Path.COMPRAS;
import static br.com.zup.desafios.ecommerce.util.Path.ID;
import static br.com.zup.desafios.ecommerce.util.Path.PAGAMENTOS;
import static br.com.zup.desafios.ecommerce.util.Path.PAGSEGURO;
import static br.com.zup.desafios.ecommerce.util.Path.PAYPAL;

@RestController
@RequestMapping(value = PAGAMENTOS + COMPRAS + ID )
public class PaymentController {

    private final CompraRepository compraRepository;
    private final PaymentProcess paymentProcess;

    public PaymentController(CompraRepository compraRepository, PaymentProcess paymentProcess) {
        this.compraRepository = compraRepository;
        this.paymentProcess = paymentProcess;
    }

    @PostMapping(value = PAYPAL)
    public ResponseEntity<PaymentResponse> processaPagamentoPayPal(@PathVariable("id") Long idCompra, @RequestBody @Valid PayPalRequest payPalRequest, UriComponentsBuilder uriLocal){
        return processaPagamento(idCompra, payPalRequest, uriLocal);
    }

    @PostMapping(value = PAGSEGURO)
    public ResponseEntity<PaymentResponse> processaPagamentoPagSeguro(@PathVariable("id") Long idCompra, @RequestBody @Valid PagSeguroRequest pagSeguroRequest, UriComponentsBuilder uriLocal){
        return processaPagamento(idCompra, pagSeguroRequest, uriLocal);
    }

    public ResponseEntity<PaymentResponse> processaPagamento(Long idCompra, PaymentRequest paymentRequest, UriComponentsBuilder uriLocal){
        if(!compraRepository.existsById(idCompra)){
            return ResponseEntity.badRequest().build();
        }
        Compra compra = compraRepository.getOne(idCompra);
        Transacao transacao = paymentRequest.convert(compra);
        boolean transacaoAdicionada = compra.adicionaTransacao(transacao);
        compra = compraRepository.save(compra);

        paymentProcess.process(compra, transacaoAdicionada, uriLocal);

        return ResponseEntity.ok(new PaymentResponse(transacao));
    }

}
