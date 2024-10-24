# ⚽ Team Service - MS Football

Este microservicio es parte del sistema de **MS Football**, que está diseñado para gestionar datos relacionados con el fútbol. El **Team Service** se encarga de la gestión de equipos, incluyendo operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y otras funcionalidades relacionadas con la información de equipos.

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.3.x**
- **Spring Data JPA** (para acceso a datos)
- **Hibernate** (ORM)
- **H2** (base de datos en memorio)
- **JUnit 5 & Mockito** (para pruebas unitarias)
- JaCoCo (cobertura de código)
- **Lombok** (para reducir código boilerplate)
- **Swagger** (para documentación de API)

## 📦 Instalación y Ejecución

### 1. Clonar el Repositorio

```bash
git clone https://github.com/pabalvrz/ms-football.git
cd ms-football/team
```

### 2. Configuración de la Base de Datos
La aplicación utiliza una base de datos **H2** en memoria por defecto. Puedes acceder a la consola de la base de datos H2 en: http://localhost:8090/h2-console

### Credenciales de la Base de Datos:

- **JDBC URL**: `jdbc:h2:file:D:/Proyectos/Learn/BD/ms-football;SCHEMA=TEAMS;AUTO_SERVER=TRUE`
- **Usuario**: `sa`
- **Contraseña**: `password`

### 3. Ejecución de la Aplicación

#### Usando Maven

Si prefieres ejecutar la aplicación desde Maven:

```bash
mvn spring-boot:run
```

### 4. Acceso a la API

Una vez que la aplicación esté corriendo, puedes acceder a la API en http://localhost:8091/swagger-ui.html.

## 📚 Endpoints Principales

