package com.game.indie.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.game.indie.entidad.Game;
import com.game.indie.service.GameService;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    // Listar paginación
    @GetMapping
    public String listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Page<Game> gamesPage = service.listarPaginado(
                PageRequest.of(page, size, Sort.by("titulo").ascending())
        );

        model.addAttribute("gamesPage", gamesPage);
        model.addAttribute("games", gamesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);

        return "games/list";
    }

    // FORM NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("game", new Game());
        return "games/form";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Game game) {
        service.guardar(game);
        return "redirect:/games";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("game", service.buscarPorId(id));
        return "games/form";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "redirect:/games";
    }
}