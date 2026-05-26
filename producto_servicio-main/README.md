Repositorio Bandas Album SpringBoot + JPA + MYSQL

# Servicio envio con Spring + Lombok + JPA + MySQL

## application.properties

```properties

Puerto
server.port=8087

MySQL configuracion
spring.datasource.url=jdbc:mysql://localhost:3306/dbenvio
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Dependecia agregada
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>