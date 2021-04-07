package br.com.zup.desafios.ecommerce.produto.pergunta;

import br.com.zup.desafios.ecommerce.produto.Produto;
import br.com.zup.desafios.ecommerce.produto.ProdutoRepository;
import br.com.zup.desafios.ecommerce.produto.pergunta.envio.SenderEmailPergunta;
import br.com.zup.desafios.ecommerce.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.zup.desafios.ecommerce.util.Path.ID;
import static br.com.zup.desafios.ecommerce.util.Path.PERGUNTAS;
import static br.com.zup.desafios.ecommerce.util.Path.PRODUTOS;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@RestController
@RequestMapping(V1 + PRODUTOS)
public class PerguntaController {

    private final ProdutoRepository produtoRepository;
    private final PerguntaRepository perguntaRepository;
    private final SenderEmailPergunta senderEmailPergunta;

    public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, SenderEmailPergunta senderEmailPergunta) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.senderEmailPergunta = senderEmailPergunta;
    }


    @PostMapping(ID + PERGUNTAS)
    public ResponseEntity<PerguntaResponse> adicionaPergunta(@PathVariable Long id, @Valid @RequestBody PerguntaPersist perguntaPersist, @AuthenticationPrincipal Usuario usuarioLogado){
        if(!produtoRepository.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        Produto produto = produtoRepository.getOne(id);
        Pergunta pergunta = perguntaRepository.save(perguntaPersist.convert(usuarioLogado, produto));
        senderEmailPergunta.enviarEmail(pergunta);

        return ResponseEntity.ok(PerguntaResponse.convert(pergunta));
    }
}
