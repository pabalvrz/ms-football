# ⚽ API Gateway - MS Football

## Descripción

El **API Gateway** es un patrón de diseño utilizado en arquitecturas de microservicios para gestionar todas las solicitudes de entrada y redirigirlas a los microservicios adecuados. Actúa como el único punto de entrada para las solicitudes de los clientes, lo que simplifica la interacción entre clientes y servicios, y proporciona funciones como:

- Balanceo de carga
- Autenticación y autorización
- Enrutamiento dinámico
- Monitorización y logging centralizado
- Control de versiones de API

## ¿Por qué usar un API Gateway?

1. **Centralización de las solicitudes**: El API Gateway permite que los clientes (aplicaciones web, móviles, etc.) interactúen con múltiples microservicios a través de un solo punto de acceso, reduciendo la complejidad en la comunicación.
2. **Seguridad**: Permite aplicar políticas de autenticación y autorización en un solo lugar, en lugar de en cada microservicio.
3. **Balanceo de carga**: Puede distribuir las solicitudes entre múltiples instancias de microservicios, mejorando la disponibilidad y escalabilidad.
4. **Control de versiones**: Facilita la gestión de versiones de las APIs, permitiendo que los microservicios evolucionen sin interrumpir el funcionamiento de las aplicaciones clientes.
5. **Monitorización**: Centraliza la recolección de métricas y logs, facilitando el monitoreo de todo el sistema.

## Arquitectura

El API Gateway actúa como intermediario entre los clientes y los microservicios. En esta implementación, utilizamos **Spring Cloud Gateway**, que es una solución ligera basada en el ecosistema de Spring Boot. Este gateway enruta las solicitudes a los microservicios registrados en **Eureka Server** utilizando balanceo de carga automático (basado en Ribbon).

## Configuración

### Dependencias

Asegúrate de que en el archivo `pom.xml` de tu proyecto tengas las siguientes dependencias:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

### Configuración del `application.yml`

El siguiente es un ejemplo de la configuración del **API Gateway** en el archivo `application.yml`:
```batch
logging.level.root=DEBUG
spring.application.name=api-gateway
cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.default-filters[0]=AddRequestHeader=X-Request-Foo, Bar
spring.cloud.gateway.default-filters[1]=AddResponseHeader=X-Response-Foo, Baz
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedOrigins=*
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedMethods=GET, POST, PUT, DELETE, OPTIONS
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedHeaders=*
spring.cloud.gateway.routes[0].id=PLAYER-SERVICE
spring.cloud.gateway.routes[0].uri=lb://PLAYER-SERVICE
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/players/**
spring.cloud.gateway.routes[1].id=TEAM-SERVICE
spring.cloud.gateway.routes[1].uri=lb://TEAM-SERVICE
spring.cloud.gateway.routes[1].predicates[0]=Path=/api/teams/**
server.port=8080
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
management.endpoints.web.exposure.include=*
````

### Estructura de rutas

- Las solicitudes que llegan a `/api/players/**` serán redirigidas al servicio `player-service`.
- Las solicitudes que llegan a `/api/teams/**` serán redirigidas al servicio `team-service`.

### Arranque del sistema

El orden de arranque recomendado de los servicios es el siguiente:

1. **Eureka Server**: Asegúrate de que Eureka Server esté corriendo para permitir el registro de los microservicios.
2. **player-service** y **team-service**: Los microservicios deben estar registrados en **Eureka** antes de que el API Gateway comience a enrutar las solicitudes.
3. **API Gateway**: Finalmente, inicia el API Gateway.

### Ejemplo de llamada

Una vez configurado e iniciado, puedes hacer una solicitud como:

(GET http://localhost:8080/api/players?page=0&size=10)

Esto redirigirá al `player-service`, asumiendo que esté registrado en **Eureka** y funcionando en su puerto correspondiente.

## Conclusión

El **API Gateway** proporciona una capa crucial en la arquitectura de microservicios para gestionar el tráfico y mejorar la seguridad, escalabilidad y mantenibilidad del sistema. Esta implementación usando **Spring Cloud Gateway** junto con **Eureka** permite un manejo flexible y eficiente de las solicitudes, facilitando el desarrollo y la administración de microservicios.
