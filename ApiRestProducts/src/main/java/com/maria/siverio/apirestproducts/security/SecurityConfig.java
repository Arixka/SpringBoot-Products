package com.maria.siverio.apirestproducts.security;


import com.maria.siverio.apirestproducts.security.config.JwtAuthenticationEntryPoint;
import com.maria.siverio.apirestproducts.security.config.JwtRequestFilter;
import com.maria.siverio.apirestproducts.security.service.AuthDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    //WebSecurityConfigurerAdapter  esta obsoleto, ahora usa dls lambda y HttpSecurity#authorizeHttpRequests

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);


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
        logger.debug("SecurityConfig initialized.");

        http.csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests().antMatchers("/auth/login", "/auth/user/create").permitAll()
                .antMatchers("/api/product/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated().and()
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
        logger.debug("AuthenticationManager ");
        return authenticationConfiguration.getAuthenticationManager();
    }


}
