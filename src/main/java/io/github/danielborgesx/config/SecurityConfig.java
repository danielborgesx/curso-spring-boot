package io.github.danielborgesx.config;

import io.github.danielborgesx.security.jwt.JwtAuthFilter;
import io.github.danielborgesx.security.jwt.JwtService;
import io.github.danielborgesx.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioService);
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz
                                        .requestMatchers("/api/clientes/**")
                                            .hasAnyRole("USER", "ADMIN")
                                        .requestMatchers("/api/produtos/**")
                                            .hasAnyRole("USER", "ADMIN")
                                        .requestMatchers("/api/pedidos/**")
                                            .hasAnyRole("USER", "ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/api/usuarios/**")
                                            .permitAll()
                                        .anyRequest().authenticated()
                                        .and()
                                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                        .and()
                                        .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );


        return http.build();
    }

    @Bean
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }
}
