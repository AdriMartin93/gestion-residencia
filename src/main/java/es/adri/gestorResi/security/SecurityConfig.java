package es.adri.gestorResi.security;

import es.adri.gestorResi.service.EmpleadoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final EmpleadoService empleadoService;

    public SecurityConfig(JwtFilter jwtFilter, EmpleadoService empleadoService) {

        this.jwtFilter = jwtFilter;
        this.empleadoService = empleadoService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
                    config.setAllowedOrigins(java.util.List.of("*"));
                    config.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(java.util.List.of("Authorization", "Content-Type", "Cache-Control"));
                    return config;
                }))


                // Desactivamos CSRF porque usamos Tokens JWT (No hay estado de sesión)
                .csrf(csrf -> csrf.disable())


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/administracion/**").hasRole("DIRECTOR")
                        .anyRequest().authenticated()
                )


                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .userDetailsService(empleadoService)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
