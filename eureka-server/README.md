# ⚽ Eureka server - MS Football

## 1. **¿Qué es Eureka?**

Eureka es un servicio de descubrimiento desarrollado por **Netflix** y utilizado ampliamente en arquitecturas de microservicios. Actúa como un **registro de servicio**, donde los microservicios (también llamados **clientes Eureka**) pueden registrarse, descubrir otros servicios y mantenerse informados sobre la disponibilidad y el estado de los demás servicios en el ecosistema.

En una arquitectura de microservicios, donde los servicios pueden escalar dinámicamente, se necesitan mecanismos para localizar y conectar servicios sin depender de configuraciones estáticas. Aquí es donde entra Eureka.

### Funciones principales de Eureka:

- **Registro de servicios**: Los microservicios se registran a sí mismos con el servidor Eureka.
- **Descubrimiento de servicios**: Los microservicios pueden consultar Eureka para descubrir otros servicios.
- **Manejo de fallos**: Si un microservicio deja de estar disponible (cae), Eureka puede eliminarlo del registro tras un período de tiempo, lo que permite que otros servicios eviten enviar solicitudes a servicios no disponibles.

## 2. **Motivos para implementar Eureka Server**

1. **Escalabilidad dinámica**: Los microservicios pueden escalar dinámicamente sin necesidad de configuraciones estáticas (como direcciones IP o URLs) gracias a que Eureka mantiene un registro actualizado de las instancias disponibles.

2. **Alta disponibilidad**: Con Eureka, puedes crear una arquitectura de alta disponibilidad al implementar réplicas del servidor Eureka. Los clientes pueden conectarse a cualquiera de las réplicas en caso de que una falle.

3. **Desacoplamiento**: Los microservicios no necesitan conocer de antemano las direcciones de otros servicios. En lugar de configuraciones estáticas, pueden consultar a Eureka para obtener la información necesaria.

4. **Manejo de fallos**: Si un servicio se cae o se vuelve no disponible, Eureka lo detectará y lo removerá del registro después de un tiempo, asegurando que los servicios que intentan consumirlo no tengan problemas.

5. **Simplicidad**: Eureka reduce la complejidad en la gestión de configuraciones, especialmente en arquitecturas distribuidas con muchos servicios en constante cambio.


## 3. **Configuración**
Se añade la siguiente dependencia en el pom.xml
```bash
<dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

En la clase principal se añade la siguiente anotación
```bash
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

Así quedaría también el properties.yml del servidor
```bash
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
    client:
      register-with-eureka: false
      fetch-registry: false
      service-url:
        defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

Para acabar la configuración, en los microservicios que vayamos a registrar en eureka.
Deberemos de añadir la siguiente dependencia:
```bash
<dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```
Y en su properties.yml:
```bash
eureka:
  instance:
    hostname: localhost
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

Con esto ya podremos acceder al server de eureka en la siguiente url: http://localhost:8761