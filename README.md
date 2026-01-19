# CRUD MVC con Thymeleaf — RA3

## 1) Datos del alumno/a
- Entidad elegida (ej. Producto, Libro...): Game

## 2) Repositorio (fork) y gestión de versiones
- Repositorio base: https://github.com/profeInformatica101/tareasSpringBoot
- Enlace a MI fork: [[PON AQUÍ EL ENLACE CUANDO LO CREES](https://github.com/kfuentesb/tareasSpringBoot/)]
- Nº de commits realizados: (mínimo 5)

## 3) Arquitectura
Explica brevemente cómo has organizado:
- Controller: GameController
- Service: GameService
- Repository: GameRepository
- Entity: Game

## 4) Base de datos elegida (marca una)
- [x] H2
- [ ] MySQL
- [ ] PostgreSQL

## 5) Configuración de la base de datos
### 5.1 Dependencias añadidas
#### H2
```
(Indica la dependencia del driver que has usado)
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```
```
spring.datasource.url=jdbc:h2:mem:cruddb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
### 5.2 application.properties / application.yml
(Pega aquí tu configuración SIN contraseñas reales si es necesario)

### 5.3 Pasos para crear la BD (si aplica)
- MySQL: CREATE DATABASE ...
- PostgreSQL: createdb ...

## 6) Cómo ejecutar el proyecto
1. Requisitos (Java versión, Maven/Gradle, DB instalada si aplica)
2. Comando de arranque:
   - ./mvnw spring-boot:run   (o equivalente)
3. URL de acceso:
   - http://localhost:8080/...

## 7) Pantallas / Rutas MVC
- GET /game (listar)
- GET /game/nuevo (formulario alta)
- POST /game (crear)
- GET /game/{id}/editar (editar)
- POST /game/{id} (actualizar)
- POST /game/{id}/borrar (eliminar)


## 8) Mejoras extra (opcional)
- Validaciones
- Estilos Bootstrap
- Búsqueda
- Pruebas
- Paginación
