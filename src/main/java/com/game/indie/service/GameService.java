package com.game.indie.service;

import java.util.List;
import com.game.indie.entidad.Game;

public interface GameService {

    Game guardar(Game game);

    List<Game> listarTodos();

    Game buscarPorId(Integer id);

    Game actualizar(Integer id, Game game);

    void eliminar(Integer id);
}
