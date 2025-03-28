package com.wilmeraraya.ecommerce.product.infrastructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .headers(headers -> headers
                                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                                // .authorizeHttpRequests(auth -> auth
                                // .requestMatchers("/h2-console/**").permitAll()
                                // .anyRequest().authenticated()
                                // )
                                .authorizeHttpRequests(auth -> auth
                                                .anyRequest().permitAll() // Permite todas las solicitudes
                                )
                                .httpBasic(withDefaults());

                return http.build();
        }
}