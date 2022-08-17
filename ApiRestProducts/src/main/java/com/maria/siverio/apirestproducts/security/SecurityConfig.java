package com.maria.siverio.apirestproducts.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    //WebSecurityConfigurerAdapter  esta obsoleto, ahora usa dls lambda y HttpSecurity#authorizeHttpRequests
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .antMatchers("/login").permitAll()
                        .antMatchers("/api/product/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

                )
                .httpBasic(withDefaults());
        return http.build();
    }

//TODO tenemos que crear un controlador para majera la ruta al login que debe ser publico
// y mas adelante las rutas de admin para crear users

}
