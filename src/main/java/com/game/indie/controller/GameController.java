package com.game.indie.controller;

import com.game.indie.entidad.Game;
import com.game.indie.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indie/games") // La URL base será http://localhost:8080/indie/games
@CrossOrigin(origins = "*")   
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> listarJuegos() {
        return gameService.obtenerTodosLosJuegos();
    }

    @PostMapping
    public Game crearJuego(@RequestBody Game game) {
        return gameService.guardarJuego(game);
    }
}