package com.game.indie.mapper;

import com.game.indie.dto.GameDTO;
import com.game.indie.entidad.Game;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidades Game y DTOs.
 * Implementa el patrón de separación de capas, evitando que las entidades 
 * JPA se expongan directamente a la capa de presentación.
 * 
 * Ventajas:
 * - Desacopla la capa de presentación de la capa de dominio
 * - Permite transformar y adaptar datos según las necesidades de cada capa
 * - Facilita la evolución independiente de entidades y DTOs
 */
@Component
public class GameMapper {

    /**
     * Convierte una entidad Game a GameDTO
     */
    public GameDTO toDTO(Game game) {
        if (game == null) {
            return null;
        }
        return new GameDTO(
            game.getId(),
            game.getTitulo(),
            game.getPrecio()
        );
    }

    /**
     * Convierte un GameDTO a entidad Game
     */
    public Game toEntity(GameDTO dto) {
        if (dto == null) {
            return null;
        }
        Game game = new Game();
        game.setId(dto.getId());
        game.setTitulo(dto.getTitulo());
        game.setPrecio(dto.getPrecio());
        return game;
    }

    /**
     * Actualiza una entidad existente con los datos del DTO
     * Útil para operaciones de actualización parcial
     */
    public void updateEntityFromDTO(GameDTO dto, Game game) {
        if (dto == null || game == null) {
            return;
        }
        game.setTitulo(dto.getTitulo());
        game.setPrecio(dto.getPrecio());
    }
}
