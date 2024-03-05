package com.security.jwtauthentication.config;

import com.security.jwtauthentication.jwt.JwtAuthenticationEntryPoint;
import com.security.jwtauthentication.jwt.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityFilterConfig {

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //disable CORS and CSRF
        return httpSecurity.csrf(csrf->csrf.disable())
        .cors(cors->cors.disable())

        // request matchers
        .authorizeHttpRequests(
                authorize->authorize.requestMatchers("/authenticate")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        )

        //give exception handler entry points and filter entry points
        .exceptionHandling(exception->exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))

        // set session policies to stateless
        .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }
}
