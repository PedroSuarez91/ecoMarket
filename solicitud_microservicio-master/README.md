#Servicio empleado con Spring + lombok + JPA + MySQL 

##Puerto
server.port=8101

##Configuracion MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/dbsolicitud
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

##Dependencia para validaciones
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>