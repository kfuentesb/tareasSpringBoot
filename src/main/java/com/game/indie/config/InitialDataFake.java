package com.game.indie.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.game.indie.entidad.Game;
import com.game.indie.entidad.Usuario;
import com.game.indie.entidad.enumerado.Rol;
import com.game.indie.service.GameService;
import com.game.indie.service.UsuarioServicio;

@Component
public class InitialDataFake implements CommandLineRunner {

  private final int TOTAL_GAMES = 100;

  private final UsuarioServicio usuarioServicio;
  private final GameService gameService;
  private final Faker faker;

  public InitialDataFake(UsuarioServicio usuarioServicio, GameService gameService) {
    this.usuarioServicio = usuarioServicio;
    this.gameService = gameService;
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
    crearGames();

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

  private void crearGames() {
	    System.out.println("Creando " + TOTAL_GAMES + " juegos de prueba...");

	    // Verificar si ya existen juegos
	    long juegosExistentes = gameService.listarTodos().size();
	    if (juegosExistentes >= TOTAL_GAMES) {
	        System.out.println("✓ Ya existen " + juegosExistentes + " juegos. No se crearán más.");
	        return;
	    }

	    for (int i = 0; i < TOTAL_GAMES; i++) {
	        Game game = new Game();

	        // Fake data compatible con la entidad Game
	        game.setTitulo(faker.name().title()); // o faker.commerce().productName()
	        game.setPrecio(faker.number().randomDouble(2, 5, 70)); // precio positivo

	        gameService.guardar(game);

	        // Mostrar progreso cada 10 juegos
	        if ((i + 1) % 10 == 0) {
	            System.out.println("  ✓ Creados " + (i + 1) + " juegos...");
	        }
	    }

	    System.out.println("✓ Total de juegos creados: " + TOTAL_GAMES);
	    System.out.println();
	}

}