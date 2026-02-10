package com.game.indie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * Configuración para habilitar métodos HTTP no estándar en formularios HTML.
 * 
 * HTML solo soporta GET y POST nativamente. Este filtro permite usar PUT y DELETE
 * agregando un campo oculto "_method" en los formularios:
 * 
 * <input type="hidden" name="_method" value="PUT" />
 * 
 * Esto hace que las rutas sean más RESTful y semánticamente correctas.
 */
@Configuration
public class WebConfig {

    /**
     * Bean que habilita el uso de PUT, DELETE, PATCH en formularios HTML.
     * Spring Boot 2.2+ requiere configuración explícita de este filtro.
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
