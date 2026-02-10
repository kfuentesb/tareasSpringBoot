package com.game.indie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * DTO para crear o actualizar un juego.
 * Separa la capa de presentación de la entidad de dominio, 
 * permitiendo validaciones específicas para formularios web.
 */
public class GameDTO {

    private Integer id;

    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    @Positive(message = "El precio debe ser positivo")
    private Double precio;

    // Constructors
    public GameDTO() {}

    public GameDTO(Integer id, String titulo, Double precio) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
    }

    // Getters and Setters
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
        return "GameDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
