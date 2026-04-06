package com.pritish.finance.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    // ✅ Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Authentication Provider (IMPORTANT)
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    // ✅ Security Configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/finance/**").hasAnyRole("ADMIN", "ANALYST")
                .requestMatchers("/dashboard/**").hasAnyRole("ADMIN", "ANALYST", "VIEWER")
                .anyRequest().authenticated()
            )

            // ✅ CRITICAL LINE (YOU MISSED THIS)
            .authenticationProvider(authenticationProvider())

            // ✅ Form Login
            .formLogin(form -> form
            	    .loginProcessingUrl("/login")
            	    .defaultSuccessUrl("/dashboard/summary", true)
            	    .permitAll()
            	)

            // ✅ Logout
            .logout(logout -> logout
            	    .logoutUrl("/logout")
            	    .logoutSuccessHandler((request, response, authentication) -> {
            	        response.setContentType("application/json");
            	        response.getWriter().write("{\"message\": \"Logged out successfully\"}");
            	    })
            	);

        return http.build();
    }

    // ✅ Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}