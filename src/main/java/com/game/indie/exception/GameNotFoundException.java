package com.game.indie.exception;

/**
 * Excepción personalizada para cuando no se encuentra un juego.
 * Esta excepción se lanza cuando se intenta acceder a un juego que no existe en la base de datos.
 * 
 * @author Spring Boot Indie Games
 */
public class GameNotFoundException extends RuntimeException {

    private final Integer gameId;

    public GameNotFoundException(Integer gameId) {
        super(String.format("No se encontró el juego con ID: %d", gameId));
        this.gameId = gameId;
    }

    public GameNotFoundException(String message) {
        super(message);
        this.gameId = null;
    }

    public Integer getGameId() {
        return gameId;
    }
}
