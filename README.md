# CRUD MVC con Thymeleaf — RA3

## 1) Datos del alumno/a
- Kevin Fuentes
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
<<<<<<< HEAD
- [x] H2
- [ ] MySQL
=======
- [] H2
- [x] MySQL
>>>>>>> ab5868d7c199016ac7ba4912e9185511184f9e67
- [ ] PostgreSQL

## 5) Configuración de la base de datos
### 5.1 Dependencias añadidas
#### H2
```
<<<<<<< HEAD
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
=======
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
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
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
>>>>>>> ab5868d7c199016ac7ba4912e9185511184f9e67
```
### 5.2 application.properties / application.yml
```
spring.application.name=NextIndie

spring.datasource.url=jdbc:mysql://localhost:3306/nextindie_db?createDatabaseIfNotExist=true&serverTimezone=UTC

spring.datasource.username=root
spring.datasource.password=

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

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
