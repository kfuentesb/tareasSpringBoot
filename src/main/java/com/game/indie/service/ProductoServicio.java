package com.game.indie.service;

import com.game.indie.entidad.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductoServicio {
  
  Page<Producto> obtenerProductoPorPagina(Pageable pageable);
  
  List<Producto> obtenerTodos();
  
  Page<Producto> obtenerProductosActivos(Pageable pageable);
  
  Optional<Producto> buscarPorId(Long id);
  
  Producto guardarProducto(Producto producto);
  Producto actualizarProducto(Producto producto);
  
  void eliminarProducto(Long id);
  void desactivarProducto(Long id);
 
  List<Producto> buscarPorNombre(String nombre);
  List<Producto> buscarPorRangoPrecio(Double precioMin, Double precioMax);
  List<Producto> obtenerProductosConStock();
}