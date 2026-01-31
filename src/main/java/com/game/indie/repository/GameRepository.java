package com.game.indie.repository;

import com.game.indie.entidad.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

	List<Game> findByNombre(String nombre);
	
	Page<Game> findAll(Pageable pageable);
	Page<Game> findByNombre(String nombre, Pageable pageable);
	Page<Game> findByNombreContaining(String nombre, Pageable pageable);
}