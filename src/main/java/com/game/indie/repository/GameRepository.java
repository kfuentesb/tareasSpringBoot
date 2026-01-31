package com.game.indie.repository;

import com.game.indie.entidad.Game;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

	List<Game> findByTitulo(String titulo);
	Page<Game> findAll(Pageable pageable);
	Page<Game> findByTitulo(String titulo, Pageable pageable);
	Page<Game> findByTituloContaining(String titulo, Pageable pageable);
}