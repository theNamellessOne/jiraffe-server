package com.example.jiraffeserver.configuration;

import com.example.jiraffeserver.configuration.bean.AuthorizationCreatorManager;
import com.example.jiraffeserver.configuration.bean.AuthorizationMemberManager;
import com.example.jiraffeserver.jwt.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AuthorizationMemberManager organizationMember;
    private final AuthorizationCreatorManager organizationCreator;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(request -> {
            org.springframework.web.cors.CorsConfiguration config =
                    new org.springframework.web.cors.CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("*"));
            config.setAllowCredentials(true);
            return config;
        }));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/organizations/my").authenticated()
                .requestMatchers("/api/organizations/my/created").authenticated()
                .requestMatchers("/api/organizations/{organizationId}/**").access(organizationMember)
                .requestMatchers(HttpMethod.PATCH, "/api/organizations/{organizationId}/members/**").access(organizationCreator)
                .requestMatchers(HttpMethod.DELETE, "/api/organizations/{organizationId}").access(organizationCreator)
                .anyRequest().authenticated()
        );

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
