package com.game.indie.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.game.indie.entidad.Game;

public interface GameService {

    Game guardar(Game game);

    List<Game> listarTodos();

    Page<Game> listarPaginado(Pageable pageable);

    Game buscarPorId(Integer id);

    Game actualizar(Integer id, Game game);

    void eliminar(Integer id);
}