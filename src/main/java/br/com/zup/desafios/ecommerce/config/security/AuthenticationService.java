package br.com.zup.desafios.ecommerce.config.security;

import br.com.zup.desafios.ecommerce.usuario.Usuario;
import br.com.zup.desafios.ecommerce.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AuthenticationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByLogin(username);
        if(optionalUsuario.isPresent()){
            return optionalUsuario.get();
        }
        throw new UsernameNotFoundException("Dados inválidos");
    }
}
