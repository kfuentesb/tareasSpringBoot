package com.game.indie.repository;

import com.game.indie.entidad.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    // Aquí no hace falta escribir nada más por ahora.
}