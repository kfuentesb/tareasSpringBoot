package com.game.indie.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa un juego indie en el sistema.
 * Utiliza JPA para mapeo objeto-relacional con H2.
 * 
 * Mejoras implementadas:
 * - Constraints explícitos en columnas (nullable, length)
 * - Validaciones mejoradas con mensajes personalizados
 * - Estrategia de generación de ID optimizada (IDENTITY)
 * - Nombre de tabla explícito para mayor claridad
 * 
 * @see <a href="https://spring.io/guides/gs/validating-form-input">Spring Validation Guide</a>
 */
@Entity
@Table(name = "games")
public class Game {
    
    /**
     * ID único del juego. Se genera automáticamente usando IDENTITY.
     * IDENTITY es más eficiente para H2 que AUTO.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    
    /**
     * Título del juego. No puede ser nulo ni estar vacío.
     * Longitud máxima: 200 caracteres.
     */
    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 1, max = 200, message = "El título debe tener entre 1 y 200 caracteres")
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;
    
    /**
     * Precio del juego en euros. Debe ser un valor positivo.
     * Se almacena con precisión decimal (precision=10, scale=2).
     */
    @Positive(message = "El precio debe ser positivo")
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private Double precio;
    
    // Constructores
    public Game() {}
    
    public Game(Integer id, String titulo, Double precio) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
