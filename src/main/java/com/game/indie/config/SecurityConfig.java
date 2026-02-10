package com.game.indie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.game.indie.entidad.enumerado.Rol;

/**
 * Configuración de seguridad de la aplicación.
 * 
 * Mejoras implementadas:
 * - Login personalizado con página propia
 * - Página de acceso denegado personalizada
 * - Protección de H2 console solo para ADMIN
 * - Seguridad a nivel de método habilitada (@PreAuthorize)
 * - CSRF manejado correctamente (deshabilitado solo para H2)
 * - Manejo de errores de autenticación mejorado
 * 
 * @EnableMethodSecurity habilita anotaciones de seguridad en métodos (@PreAuthorize, @PostAuthorize, @Secured)
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Habilita seguridad a nivel de método
public class SecurityConfig {

    /**
     * Bean del AuthenticationManager para gestión de autenticación.
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Encoder de contraseñas BCrypt.
     * BCrypt es un algoritmo robusto que incluye salt automático
     * y es resistente a ataques de fuerza bruta.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuración de la cadena de filtros de seguridad.
     * Define qué rutas están protegidas y cómo.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // ============================================
        // H2 Console Configuration
        // ============================================
        // Permite iframes para H2 console (solo en desarrollo)
        http.headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        // ============================================
        // Authorization Rules
        // ============================================
        http.authorizeHttpRequests(auth -> auth
            // Páginas públicas accesibles sin autenticación
            .requestMatchers("/", "/login", "/error", "/error/**", "/css/**", "/js/**", "/images/**").permitAll()

            // Dashboard de administrador - solo ADMIN
            .requestMatchers("/admin/**").hasRole(Rol.ADMIN.toString())

            // H2 Console - solo ADMIN (para depuración en desarrollo)
            .requestMatchers("/h2-console/**", "/h2/**").hasRole(Rol.ADMIN.toString())

            // Todas las demás rutas requieren autenticación
            .anyRequest().authenticated()
        );

        // ============================================
        // CSRF Configuration
        // ============================================
        http.csrf(csrf -> csrf
            // Deshabilitar CSRF solo para H2 console (solo desarrollo)
            .ignoringRequestMatchers("/h2-console/**", "/h2/**")
            // CSRF está habilitado para el resto de la aplicación
        );

        // ============================================
        // Form Login Configuration
        // ============================================
        http.formLogin(form -> form
            .loginPage("/login")                    // Página de login personalizada
            .loginProcessingUrl("/login")           // URL donde se procesa el login
            .defaultSuccessUrl("/games", true)      // Redirección tras login exitoso
            .failureUrl("/login?error=true")        // Redirección si falla el login
            .permitAll()
        );

        // ============================================
        // Logout Configuration
        // ============================================
        http.logout(logout -> logout
            .logoutUrl("/logout")                   // URL para cerrar sesión
            .logoutSuccessUrl("/login?logout=true") // Redirección tras logout
            .invalidateHttpSession(true)            // Invalida la sesión
            .deleteCookies("JSESSIONID")            // Elimina cookies
            .permitAll()
        );

        // ============================================
        // Exception Handling - Access Denied
        // ============================================
        http.exceptionHandling(ex -> ex
            .accessDeniedPage("/error/403")         // Página personalizada para acceso denegado
        );

        return http.build();
    }
}