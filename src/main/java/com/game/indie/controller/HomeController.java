package com.game.indie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para páginas públicas y de autenticación.
 */
@Controller
public class HomeController {

    /**
     * Página de inicio pública.
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Página de login personalizada.
     * Si el usuario ya está autenticado, Spring Security lo redirige automáticamente.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}