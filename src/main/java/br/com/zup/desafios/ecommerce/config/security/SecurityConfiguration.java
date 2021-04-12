package br.com.zup.desafios.ecommerce.config.security;

import br.com.zup.desafios.ecommerce.usuario.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static br.com.zup.desafios.ecommerce.util.Path.USUARIOS;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenManager tokenManager;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationService authenticationService;
    private final EncoderSecurity encoderSecurity;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    public SecurityConfiguration(TokenManager tokenManager, UsuarioRepository usuarioRepository, AuthenticationService authenticationService, EncoderSecurity encoderSecurity) {
        this.tokenManager = tokenManager;
        this.usuarioRepository = usuarioRepository;
        this.authenticationService = authenticationService;
        this.encoderSecurity = encoderSecurity;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(encoderSecurity.encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, V1 + USUARIOS).permitAll()
            .antMatchers(V1 + "/auth/**").permitAll()
            .antMatchers("/h2/**").permitAll()
            .antMatchers("/ranking/**").permitAll()
            .antMatchers("/nota-fiscal/**").permitAll()
            .anyRequest().authenticated()
            .and().cors()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().headers().frameOptions().sameOrigin()
            .and().addFilterBefore(new JwtAuthenticationFilter(tokenManager, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/***.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }
}
