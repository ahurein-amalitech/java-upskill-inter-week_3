package org.example.partoneactuator.configs;


import lombok.RequiredArgsConstructor;
import org.example.partoneactuator.entity.User;
import org.example.partoneactuator.repository.JpaUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JpaUserRepository userRepository;

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}pass")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
