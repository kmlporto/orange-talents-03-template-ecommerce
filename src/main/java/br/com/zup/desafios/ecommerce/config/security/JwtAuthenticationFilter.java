package br.com.zup.desafios.ecommerce.config.security;

import br.com.zup.desafios.ecommerce.usuario.Usuario;
import br.com.zup.desafios.ecommerce.usuario.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;
    private final UsuarioRepository usuarioRepository;

    public JwtAuthenticationFilter(TokenManager tokenManager, UsuarioRepository usuarioRepository) {
        this.tokenManager = tokenManager;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if(tokenManager.isTokenValid(token)){
            clientAuthenticate(token);
        }
        filterChain.doFilter(request, response);
    }

    private void clientAuthenticate(String token) {
        Long idUsuario = tokenManager.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7);
    }
}
