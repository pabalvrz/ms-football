# ⚽ Player Service - MS Football

Este microservicio es parte del sistema de **MS Football**, que está diseñado para gestionar datos relacionados con el fútbol. El **Player Service** se encarga de la gestión de jugadores, incluyendo operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y otras funcionalidades relacionadas con la información de jugadores.

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
cd ms-football/player

### 2. Configuración de la Base de Datos
La aplicación utiliza una base de datos **H2** en memoria por defecto. Puedes acceder a la consola de la base de datos H2 en: http://localhost:8080/h2-console

### Credenciales de la Base de Datos:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Usuario**: `sa`
- **Contraseña**: `password`
