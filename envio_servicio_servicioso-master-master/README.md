# Servicio envio con Spring + Lombok + JPA + MySQL

## application.properties

```properties
server.port=8102

spring.datasource.url=jdbc:mysql://localhost:3306/dbenvio
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>