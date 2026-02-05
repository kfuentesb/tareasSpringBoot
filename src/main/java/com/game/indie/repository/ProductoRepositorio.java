package com.game.indie.repository;

import com.game.indie.entidad.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
  

  List<Producto> findByNombreContainingIgnoreCase(String nombre);
  
  Page<Producto> findByActivoTrue(Pageable pageable);
  
  @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN :precioMin AND :precioMax")
  List<Producto> buscarPorRangoPrecio(@Param("precioMin") Double precioMin, 
                                      @Param("precioMax") Double precioMax);

  List<Producto> findByStockGreaterThan(Integer cantidad);
}