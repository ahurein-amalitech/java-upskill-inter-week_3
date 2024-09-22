package org.example.partoneoauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> {
                            authorize.requestMatchers("/", "/login").permitAll();
                            authorize.anyRequest().authenticated();
                        }
                )
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}