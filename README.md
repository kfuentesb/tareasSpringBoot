# CRUD MVC con Thymeleaf — RA3

## 1) Datos del alumno/a
- Kevin Fuentes
- Entidad elegida (ej. Producto, Libro...): **Game**

## 2) Repositorio (fork) y gestión de versiones
- Repositorio base: https://github.com/profeInformatica101/tareasSpringBoot
- Enlace a MI fork: https://github.com/kfuentesb/tareasSpringBoot/
- Nº de commits realizados: *(mínimo 5)*

## 3) Arquitectura
Explica brevemente cómo has organizado:
- **Controller:** AdminController, GameController, HomeController
- **Service:** GameService, GameServiceImpl, UserDetailsServiceImpl, UsuarioServicio, UsuarioServicioImpl
- **Repository:** GameRepository, UsuarioRepositorio
- **Entity:** Game, Usuario
- **Entity (Enumerado):** Rol
- **Config:** InitialDataFake, SecurityConfig

## 4) Base de datos elegida (marca una)
- [x] H2  
- [ ] MySQL

## 5) Configuración de la base de datos

### 5.1 Dependencias añadidas
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-h2console</artifactId>
</dependency>
<dependency>
  <groupId>com.github.javafaker</groupId>
  <artifactId>javafaker</artifactId>
  <version>1.0.2</version>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <scope>runtime</scope>
  <optional>true</optional>
</dependency>
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa-test</artifactId>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation-test</artifactId>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-webmvc-test</artifactId>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

### 5.2 application.properties / application.yml
```properties
server.port=9000

# H2
spring.datasource.url=jdbc:h2:mem:indiedb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# (opcional) Evita warning open-in-view si no lo quieres
# spring.jpa.open-in-view=false

# JPA
spring.jpa.hibernate.ddl-auto=create
# spring.jpa.hibernate.ddl-auto=none "Crear tabla manualmente"
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=false
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Thymeleaf
spring.thymeleaf.cache=false
```

### 5.3 Pasos para crear la BD (si aplica)
- Se genera automáticamente con H2

## 6) Cómo ejecutar el proyecto
1. Requisitos (Java versión, Maven/Gradle, DB instalada si aplica)
   - **Java 17**
   - **Maven 4.0.2**
   - **DB:** H2
2. Comando de arranque:
   - `./mvnw spring-boot:run` (o equivalente)
3. URL de acceso:
   - http://localhost:9000/
   - http://localhost:9000/index.html → redirige siempre a login

## 7) Pantallas / Rutas MVC
- GET `/game` (listar)
- GET `/game/nuevo` (formulario alta)
- POST `/game` (crear)
- GET `/game/{id}/editar` (editar)
- POST `/game/{id}` (actualizar)
- POST `/game/{id}/borrar` (eliminar)
- GET `/admin/dashboard` (panel admin)
- GET `/` (inicio)

## 8) Mejoras extra (opcional)
- Validaciones
- Estilos Bootstrap
- Búsqueda
- Pruebas
- Paginación
