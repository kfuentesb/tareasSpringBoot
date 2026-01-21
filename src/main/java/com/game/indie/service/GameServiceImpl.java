package com.game.indie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.game.indie.entidad.Game;
import com.game.indie.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository repository;

    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Game guardar(Game game) {
        return repository.save(game);
    }

    @Override
    public List<Game> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Game buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Game actualizar(Integer id, Game game) {
        Optional<Game> existente = repository.findById(id);

        if (existente.isPresent()) {
            Game g = existente.get();
            g.setTitulo(game.getTitulo());
            g.setPrecio(game.getPrecio());
            return repository.save(g);
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
