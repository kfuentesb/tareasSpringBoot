package com.game.indie.service;

import com.game.indie.entidad.Game;
import com.game.indie.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> obtenerTodosLosJuegos() {
        return gameRepository.findAll();
    }

    public Game guardarJuego(Game game) {
        return gameRepository.save(game);
    }
}