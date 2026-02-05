package com.game.indie.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.game.indie.entidad.Producto;
import com.game.indie.entidad.Usuario;
import com.game.indie.entidad.enumerado.Rol;
import com.game.indie.service.ProductoServicio;
import com.game.indie.service.UsuarioServicio;

@Component
public class InitialDataFake implements CommandLineRunner {

  private final int TOTAL_PRODUCTOS = 100;

  private final ProductoServicio productoServicio;
  private final UsuarioServicio usuarioServicio;
  private final Faker faker;

  public InitialDataFake(ProductoServicio productoServicio, UsuarioServicio usuarioServicio) {
    this.productoServicio = productoServicio;
    this.usuarioServicio = usuarioServicio;
    this.faker = new Faker();
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("========================================");
    System.out.println("Iniciando carga de datos de prueba...");
    System.out.println("========================================");

    // Crear usuarios
    crearUsuarios();

    // Crear productos
    crearProductos();

    System.out.println("========================================");
    System.out.println("Carga de datos completada exitosamente!");
    System.out.println("========================================");
  }

  private void crearUsuarios() {
    System.out.println("Creando usuarios...");
    
    try {
      usuarioServicio.crear("admin", "admin123", Rol.ADMIN);
      System.out.println("✓ Usuario ADMIN creado (usuario: admin, contraseña: admin123)");
    } catch (IllegalArgumentException e) {
      System.out.println("✓ Usuario ADMIN ya existe");
    }

    try {
      usuarioServicio.crear("manager", "manager123", Rol.MANAGER);
      System.out.println("✓ Usuario MANAGER creado (usuario: manager, contraseña: manager123)");
    } catch (IllegalArgumentException e) {
      System.out.println("✓ Usuario MANAGER ya existe");
    }

    try {
      usuarioServicio.crear("usuario", "usuario123", Rol.USUARIO);
      System.out.println("✓ Usuario USUARIO creado (usuario: usuario, contraseña: usuario123)");
    } catch (IllegalArgumentException e) {
      System.out.println("✓ Usuario USUARIO ya existe");
    }

    System.out.println();
  }

  private void crearProductos() {
    System.out.println("Creando " + TOTAL_PRODUCTOS + " productos de prueba...");

    // Verificar si ya existen productos
    long productosExistentes = productoServicio.obtenerTodos().size();
    if (productosExistentes >= TOTAL_PRODUCTOS) {
      System.out.println("✓ Ya existen " + productosExistentes + " productos. No se crearán más.");
      return;
    }

    for (int i = 0; i < TOTAL_PRODUCTOS; i++) {
      Producto p = new Producto();
      
      // Generar datos aleatorios con Faker
      p.setNombre(faker.commerce().productName());
      p.setDescripcion(faker.lorem().sentence(10));
      p.setPrecio(faker.number().randomDouble(2, 10, 500));
      p.setStock(faker.number().numberBetween(0, 100));
      p.setActivo(true);
      
      // Opcional: agregar una imagen aleatoria (puedes usar URLs de placeholders)
      p.setImagen("https://via.placeholder.com/300x300?text=" + (i + 1));

      productoServicio.guardarProducto(p);

      // Mostrar progreso cada 20 productos
      if ((i + 1) % 20 == 0) {
        System.out.println("  ✓ Creados " + (i + 1) + " productos...");
      }
    }

    System.out.println("✓ Total de productos creados: " + TOTAL_PRODUCTOS);
    System.out.println();
  }
}