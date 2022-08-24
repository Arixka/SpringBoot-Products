package com.maria.siverio.apirestproducts.security;


import com.maria.siverio.apirestproducts.security.config.JwtAuthenticationEntryPoint;
import com.maria.siverio.apirestproducts.security.config.JwtRequestFilter;
import com.maria.siverio.apirestproducts.security.service.AuthDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    //WebSecurityConfigurerAdapter  esta obsoleto, ahora usa dls lambda y HttpSecurity#authorizeHttpRequests

    @Value("${spring.h2.console.path}")
    private String h2ConsolePath;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private AuthDetailService authDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("SecurityConfig initialized.");

        http.csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests().antMatchers("/api/auth/login", "/api/auth/user/create").permitAll()
                .antMatchers(h2ConsolePath + "/**").permitAll()
//                .antMatchers("/api/product/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/product/**").authenticated().and()
                .userDetailsService(authDetailService)
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


//TODO tenemos que crear un controlador para majera la ruta al login que debe ser publico
// y mas adelante las rutas de admin para crear users

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        log.debug("AuthenticationManager ");
        return authenticationConfiguration.getAuthenticationManager();
    }


}
