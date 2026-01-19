package com.game.indie.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
/**
 * https://spring.io/guides/gs/validating-form-input
 */
@Entity
public class Game {
		//añadios ID
		@Id
		@GeneratedValue(strategy =GenerationType.AUTO)
		private Integer id;
		@NotBlank(message = "El titulo no puede estar vacio")
		private String titulo;
		@Positive(message = "El precio tiene que ser positivo")
		private String descripcion;
		
		@NotBlank(message = "Categoría tiene que tener un valor")
		private String categoria;
		
		public Game() {}
		
		public Game(int id, String titulo, String descripcion) {
			super();
			this.id = id;
			this.titulo = titulo;
			this.descripcion = descripcion;
			
		}


		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "Game [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + "]";
		}
		
			

}
