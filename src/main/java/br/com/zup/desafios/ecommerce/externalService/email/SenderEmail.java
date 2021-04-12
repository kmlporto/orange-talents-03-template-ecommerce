package br.com.zup.desafios.ecommerce.externalService.email;

import br.com.zup.desafios.ecommerce.compra.Compra;
import br.com.zup.desafios.ecommerce.compra.EventoCompraSucesso;
import br.com.zup.desafios.ecommerce.produto.pergunta.Pergunta;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SenderEmail implements EventoCompraSucesso {

    private final EmailFake emailFake;

    public SenderEmail(EmailFake emailFake) {
        this.emailFake = emailFake;
    }

    public void emailPergunta(Pergunta pergunta){
        emailFake.send(pergunta.getUsuario().getLogin(),
                pergunta.getProduto().getDono().getLogin(),
                "Nova pergunta do produto " + pergunta.getProduto().getNome(),
                pergunta.getTitulo());
    }

    public void emailPagamentoFalhou(Compra compra, String uriRedirecionamento){
        emailFake.send(
                "mercadolivre@gmail.com",
                compra.getComprador().getLogin(),
                "Pagamento da compra negado",
                "Pagamento sem sucesso, tentar novamente no link: " + uriRedirecionamento);
    }

    @Override
    public void process(Compra compra) {
        List<String> produtos = compra.getItens().stream().map(item -> item.getProduto().getNome()).collect(Collectors.toList());
        emailFake.send(
                "mercadolivre@gmail.com",
                compra.getComprador().getLogin(),
                "Pagamento da compra aprovado de " + produtos,
                "Pagamento bem sucedido, em breve enviaremos os produtos");
    }
}
