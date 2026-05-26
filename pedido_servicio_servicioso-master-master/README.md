# Servicio envio con Spring + Lombok + JPA + MySQL

## application.properties

```properties

Puerto
    server.port=8102

Propiedades MySQL
    spring.datasource.url=jdbc:mysql://localhost:3306/dbenvio
    spring.datasource.username=root
    spring.datasource.password=

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

Dependencias Agregadas
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>