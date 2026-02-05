package com.game.indie.service;

import com.game.indie.entidad.Producto;
import com.game.indie.repository.ProductoRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoServicioImpl implements ProductoServicio {

  private final ProductoRepositorio productoRepositorio;

  public ProductoServicioImpl(ProductoRepositorio productoRepositorio) {
    this.productoRepositorio = productoRepositorio;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Producto> obtenerProductoPorPagina(Pageable pageable) {
    return productoRepositorio.findAll(pageable);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Producto> obtenerTodos() {
    return productoRepositorio.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Producto> obtenerProductosActivos(Pageable pageable) {
    return productoRepositorio.findByActivoTrue(pageable);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Producto> buscarPorId(Long id) {
    return productoRepositorio.findById(id);
  }

  @Override
  public Producto guardarProducto(Producto producto) {
    return productoRepositorio.save(producto);
  }

  @Override
  public Producto actualizarProducto(Producto producto) {
    if (producto.getId() == null) {
      throw new IllegalArgumentException("El producto debe tener un ID para actualizarse");
    }
    return productoRepositorio.save(producto);
  }

  @Override
  public void eliminarProducto(Long id) {
    productoRepositorio.deleteById(id);
  }

  @Override
  public void desactivarProducto(Long id) {
    Optional<Producto> productoOpt = productoRepositorio.findById(id);
    if (productoOpt.isPresent()) {
      Producto producto = productoOpt.get();
      producto.setActivo(false);
      productoRepositorio.save(producto);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<Producto> buscarPorNombre(String nombre) {
    return productoRepositorio.findByNombreContainingIgnoreCase(nombre);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Producto> buscarPorRangoPrecio(Double precioMin, Double precioMax) {
    return productoRepositorio.buscarPorRangoPrecio(precioMin, precioMax);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Producto> obtenerProductosConStock() {
    return productoRepositorio.findByStockGreaterThan(0);
  }
}