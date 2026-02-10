package com.game.indie.web;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.indie.exception.GameNotFoundException;
import com.game.indie.exception.ResourceNotFoundException;

/**
 * Manejador global de excepciones para toda la aplicación.
 * 
 * @ControllerAdvice permite interceptar excepciones en todos los controladores
 * y proporcionar un manejo centralizado y consistente de errores.
 * 
 * Ventajas:
 * - Código más limpio en controladores (no necesitan try-catch)
 * - Manejo consistente de errores en toda la aplicación
 * - Mejor experiencia de usuario con mensajes claros
 * - Facilita el logging y monitoreo de errores
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja GameNotFoundException cuando no se encuentra un juego.
     * Redirige a la lista de juegos con un mensaje de error.
     */
    @ExceptionHandler(GameNotFoundException.class)
    public String handleGameNotFound(GameNotFoundException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        redirectAttributes.addFlashAttribute("errorType", "warning");
        return "redirect:/games";
    }

    /**
     * Maneja ResourceNotFoundException genérica.
     * Muestra una página de error personalizada.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest req, Model model) {
        model.addAttribute("titulo", "Recurso no encontrado");
        model.addAttribute("mensaje", ex.getMessage());
        model.addAttribute("path", req.getRequestURI());
        model.addAttribute("status", 404);
        return "error";
    }

    /**
     * Maneja UsernameNotFoundException cuando no se encuentra un usuario.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(UsernameNotFoundException ex, HttpServletRequest req, Model model) {
        model.addAttribute("titulo", "Usuario no encontrado");
        model.addAttribute("mensaje", ex.getMessage());
        model.addAttribute("path", req.getRequestURI());
        model.addAttribute("status", 404);
        return "error";
    }

    /**
     * Maneja IllegalArgumentException para validaciones de negocio.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(IllegalArgumentException ex, HttpServletRequest req, Model model) {
        model.addAttribute("titulo", "Petición no válida");
        model.addAttribute("mensaje", ex.getMessage());
        model.addAttribute("path", req.getRequestURI());
        model.addAttribute("status", 400);
        return "error";
    }

    /**
     * Maneja AccessDeniedException cuando un usuario intenta acceder a un recurso sin permisos.
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDenied(AccessDeniedException ex, HttpServletRequest req, Model model) {
        model.addAttribute("titulo", "Acceso Denegado");
        model.addAttribute("mensaje", "No tienes permisos para acceder a este recurso.");
        model.addAttribute("path", req.getRequestURI());
        model.addAttribute("status", 403);
        return "error/403"; // Página personalizada de acceso denegado
    }

    /**
     * Maneja cualquier otra excepción no capturada específicamente.
     * Actúa como red de seguridad para errores inesperados.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneric(Exception ex, HttpServletRequest req, Model model) {
        // Log del error para debugging (en producción usar un logger apropiado)
        System.err.println("Error no manejado: " + ex.getClass().getName() + " - " + ex.getMessage());
        ex.printStackTrace();

        model.addAttribute("titulo", "Error inesperado");
        model.addAttribute("mensaje", "Ha ocurrido un error. Inténtalo de nuevo.");
        model.addAttribute("path", req.getRequestURI());
        model.addAttribute("status", 500);
        return "error";
    }
}