# Crear proveedor

POST http://localhost:8085/api/v1/proveedores

{
  "nombre": "Distribuidora EcoMarket",
  "email": "contacto@ecomarket.cl",
  "telefono": "+56987654321",
  "direccion": "Av. Los Carrera 1234, Concepción"
}

# application.properties

spring.application.name=proveedor-microservicio

server.port=8085

# MySQL Connection Settings

spring.datasource.url=jdbc:mysql://localhost:3306/proveedoresdb

spring.datasource.username=root

spring.datasource.password=

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Settings

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
