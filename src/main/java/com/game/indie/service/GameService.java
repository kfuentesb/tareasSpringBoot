package com.game.indie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.game.indie.entidad.Game;

/**
 * Servicio para gestión de juegos.
 * Define las operaciones de negocio relacionadas con entidades Game.
 * 
 * Beneficios de usar interfaces para servicios:
 * - Facilita testing con mocks
 * - Permite múltiples implementaciones
 * - Mejora el desacoplamiento entre capas
 */
public interface GameService {

    /**
     * Guarda un nuevo juego o actualiza uno existente.
     * @param game El juego a guardar
     * @return El juego guardado con su ID generado
     */
    Game guardar(Game game);

    /**
     * Lista todos los juegos sin paginación.
     * @return Lista completa de juegos
     */
    List<Game> listarTodos();

    /**
     * Lista juegos con paginación y ordenamiento.
     * @param pageable Configuración de paginación
     * @return Página de juegos
     */
    Page<Game> listarPaginado(Pageable pageable);

    /**
     * Busca un juego por su ID.
     * @param id ID del juego
     * @return Optional con el juego si existe, Optional.empty() si no
     */
    Optional<Game> buscarPorId(Integer id);

    /**
     * Actualiza un juego existente.
     * @param id ID del juego a actualizar
     * @param game Datos actualizados
     * @return El juego actualizado
     * @throws com.game.indie.exception.GameNotFoundException si el juego no existe
     */
    Game actualizar(Integer id, Game game);

    /**
     * Elimina un juego por su ID.
     * @param id ID del juego a eliminar
     * @throws com.game.indie.exception.GameNotFoundException si el juego no existe
     */
    void eliminar(Integer id);

    /**
     * Busca juegos por título (búsqueda parcial).
     * Útil para funcionalidades de búsqueda y autocompletado.
     * @param titulo Parte del título a buscar
     * @param pageable Configuración de paginación
     * @return Página de juegos que contienen el título
     */
    Page<Game> buscarPorTituloContaining(String titulo, Pageable pageable);
}