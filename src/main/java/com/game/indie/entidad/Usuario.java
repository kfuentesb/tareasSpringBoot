package com.game.indie.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.game.indie.entidad.enumerado.Rol;

/**
 * Entidad Usuario que representa un usuario del sistema.
 * Implementa UserDetails de Spring Security para integración con autenticación.
 * 
 * Mejoras implementadas:
 * - Constraints explícitos en columnas
 * - Validaciones mejoradas
 * - Tabla con nombre explícito
 * - Comentarios explicativos para cada campo
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /**
     * Nombre de usuario único en el sistema.
     * Se utiliza para el login y debe ser único.
     */
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    private String nombre;

    /**
     * Contraseña almacenada como hash BCrypt.
     * NUNCA se almacena en texto plano por seguridad.
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    /**
     * Rol del usuario (ADMIN, MANAGER, USUARIO).
     * Define los permisos y accesos del usuario en el sistema.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 20)
    private Rol rol;

    // Constructores
    public Usuario() {}

    public Usuario(String nombre, String contrasena, Rol rol) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getContrasena() { 
        return contrasena; 
    }
    
    public void setContrasena(String contrasena) { 
        this.contrasena = contrasena; 
    }

    public Rol getRol() { 
        return rol; 
    }
    
    public void setRol(Rol rol) { 
        this.rol = rol; 
    }

    // ========================================
    // Métodos de UserDetails (Spring Security)
    // ========================================

    /**
     * Retorna las autoridades (roles) del usuario.
     * Se prefija con "ROLE_" según convención de Spring Security.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    /**
     * Por defecto, las cuentas no expiran.
     * Se puede personalizar agregando un campo 'fechaExpiracion' si se requiere.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Por defecto, las cuentas no se bloquean.
     * Se puede personalizar agregando un campo 'bloqueado' si se requiere.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Por defecto, las credenciales no expiran.
     * Se puede personalizar agregando un campo 'fechaExpiracionCredenciales' si se requiere.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Por defecto, los usuarios están habilitados.
     * Se puede personalizar agregando un campo 'activo' si se requiere.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rol=" + rol +
                '}';
    }
}