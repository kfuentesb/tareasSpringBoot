package com.game.indie.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.indie.entidad.Game;
import com.game.indie.exception.GameNotFoundException;
import com.game.indie.service.GameService;

/**
 * Controlador REST para gestión de juegos.
 * 
 * Mejoras implementadas:
 * - Uso de flash attributes para mensajes de éxito/error
 * - Métodos HTTP apropiados (GET, POST, PUT, DELETE)
 * - Manejo de errores con excepciones personalizadas
 * - Validación mejorada con feedback claro
 * - Rutas RESTful más consistentes
 */
@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService service;

    /**
     * Inyección por constructor (mejor práctica).
     * Spring lo detecta automáticamente sin @Autowired.
     */
    public GameController(GameService service) {
        this.service = service;
    }

    // ===============================
    // LISTAR CON PAGINACIÓN
    // ===============================
    /**
     * Lista todos los juegos con paginación y ordenamiento.
     * GET /games?page=0&size=5
     */
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
        
        // Agregar info para manejar estado vacío en la vista
        model.addAttribute("isEmpty", gamesPage.isEmpty());

        return "games/list";
    }

    // ===============================
    // FORM NUEVO
    // ===============================
    /**
     * Muestra el formulario para crear un nuevo juego.
     * GET /games/nuevo
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("isEdit", false);
        return "games/form";
    }

    // ===============================
    // GUARDAR (crear)
    // ===============================
    /**
     * Guarda un nuevo juego.
     * POST /games
     */
    @PostMapping
    public String guardar(
            @Valid @ModelAttribute("game") Game game,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Si hay errores de validación → volver al form
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "games/form";
        }

        service.guardar(game);
        
        // Flash message de éxito
        redirectAttributes.addFlashAttribute("success", "Juego creado exitosamente");
        redirectAttributes.addFlashAttribute("successType", "success");

        return "redirect:/games";
    }

    // ===============================
    // EDITAR
    // ===============================
    /**
     * Muestra el formulario para editar un juego existente.
     * GET /games/{id}/editar
     */
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        
        // Usar Optional en lugar de verificar null
        Game game = service.buscarPorId(id)
            .orElseThrow(() -> new GameNotFoundException(id));

        model.addAttribute("game", game);
        model.addAttribute("isEdit", true);
        return "games/form";
    }

    // ===============================
    // ACTUALIZAR
    // ===============================
    /**
     * Actualiza un juego existente.
     * PUT /games/{id} (o POST con _method=PUT para compatibilidad con HTML forms)
     */
    @PutMapping("/{id}")
    public String actualizar(
            @PathVariable Integer id,
            @Valid @ModelAttribute("game") Game game,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Si hay errores de validación → volver al form
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "games/form";
        }

        service.actualizar(id, game);
        
        // Flash message de éxito
        redirectAttributes.addFlashAttribute("success", "Juego actualizado exitosamente");
        redirectAttributes.addFlashAttribute("successType", "info");

        return "redirect:/games";
    }

    // ===============================
    // ELIMINAR
    // ===============================
    /**
     * Elimina un juego.
     * DELETE /games/{id} (o POST con _method=DELETE para compatibilidad con HTML forms)
     */
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        
        service.eliminar(id);
        
        // Flash message de éxito
        redirectAttributes.addFlashAttribute("success", "Juego eliminado exitosamente");
        redirectAttributes.addFlashAttribute("successType", "warning");

        return "redirect:/games";
    }
}
