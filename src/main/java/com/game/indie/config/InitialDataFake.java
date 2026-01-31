package com.game.indie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.game.indie.entidad.Game;
import com.game.indie.service.GameService;

@Component
public class InitialDataFake implements CommandLineRunner {
	@Autowired
	private GameService servicio;
	
	@Override
	public void run(String...args) throws Exception {
		Game g1 = new Game();
		g1.setPrecio(5.0);
		g1.setTitulo("Celeste");
		Game g2 = new Game();
		g2.setPrecio(9.95);
		g2.setTitulo("Stardew Valley");
		
		servicio.guardar(g1);
		servicio.guardar(g2);
	}
}
