package br.com.zup.desafios.ecommerce.autenticacao;

import br.com.zup.desafios.ecommerce.config.security.TokenManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.zup.desafios.ecommerce.util.Path.AUTH;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@RestController
@RequestMapping(V1 + AUTH)
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenManager tokenManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<TokenReponse> criaToken(@RequestBody @Valid TokenRequest tokenRequest) {
        UsernamePasswordAuthenticationToken dadosLogin = tokenRequest.converter(passwordEncoder);

        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenManager.generateToken(authentication);
            return ResponseEntity.ok(new TokenReponse(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
