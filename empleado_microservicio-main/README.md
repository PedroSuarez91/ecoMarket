Servicio empleado con Spring + lombok + JPA + MySQL
server.port=8100

Configuracion MySQL:
    spring.datasource.url=jdbc:mysql://localhost:3306/dbempleado
    spring.datasource.username=root
    spring.datasource.password=
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

Dependencias agregadas
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>