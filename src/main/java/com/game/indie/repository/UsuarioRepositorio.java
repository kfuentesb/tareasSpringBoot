package com.game.indie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.game.indie.entidad.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
  Usuario findByNombre(String username);
}
