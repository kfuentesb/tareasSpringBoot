package com.game.indie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.indie.entidad.Game;
import com.game.indie.exception.GameNotFoundException;
import com.game.indie.repository.GameRepository;

/**
 * Implementación del servicio de gestión de juegos.
 * 
 * Mejoras implementadas:
 * - Uso de Optional en lugar de null para mayor seguridad
 * - Excepciones personalizadas para mejor manejo de errores
 * - Anotaciones de seguridad a nivel de método (@PreAuthorize)
 * - Transacciones explícitas donde sea necesario
 * - Validaciones de negocio antes de operaciones críticas
 * 
 * @Service marca esta clase como un bean de servicio de Spring
 * @Transactional maneja transacciones automáticamente para consistencia de datos
 */
@Service
@Transactional(readOnly = true) // Por defecto, operaciones de solo lectura
public class GameServiceImpl implements GameService {

    private final GameRepository repository;

    /**
     * Inyección por constructor (mejor práctica).
     * - Es inmutable (final)
     * - Facilita testing
     * - Spring lo detecta automáticamente sin @Autowired
     */
    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional // Permite escritura en BD
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')") // Solo ADMIN o MANAGER pueden crear juegos
    public Game guardar(Game game) {
        // Validación de negocio adicional si se requiere
        if (game.getPrecio() != null && game.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        return repository.save(game);
    }

    @Override
    public List<Game> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Page<Game> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Game> buscarPorId(Integer id) {
        // Retorna Optional en lugar de null - patrón más seguro
        return repository.findById(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')") // Solo ADMIN o MANAGER pueden actualizar
    public Game actualizar(Integer id, Game game) {
        // Buscar el juego existente
        Game existente = repository.findById(id)
            .orElseThrow(() -> new GameNotFoundException(id));

        // Actualizar solo los campos modificables
        existente.setTitulo(game.getTitulo());
        existente.setPrecio(game.getPrecio());

        return repository.save(existente);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')") // Solo ADMIN puede eliminar
    public void eliminar(Integer id) {
        // Verificar que el juego existe antes de eliminar
        if (!repository.existsById(id)) {
            throw new GameNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public Page<Game> buscarPorTituloContaining(String titulo, Pageable pageable) {
        // Consulta personalizada del repositorio
        return repository.findByTituloContaining(titulo, pageable);
    }
}