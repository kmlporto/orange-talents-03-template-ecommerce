package br.com.zup.desafios.ecommerce.produto.pergunta.envio;

import br.com.zup.desafios.ecommerce.produto.pergunta.Pergunta;
import org.springframework.stereotype.Component;

@Component
public class SenderEmailPerguntaFake implements SenderEmailPergunta{
    @Override
    public void enviarEmail(Pergunta pergunta) {
        String email = pergunta.getProduto().getDono().getLogin();
        String texto = pergunta.getTitulo();
        System.out.println("Enviando email para " + email + "...\nPergunta: " + texto + "\nSobre o produto: " + pergunta.getProduto().getNome());
    }
}
