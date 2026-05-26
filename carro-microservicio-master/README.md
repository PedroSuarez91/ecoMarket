# Crear carro
POST http://localhost:8089/api/v1/carros

JSON
{
  "total": 0,
  "subtotal": 0,
  "listaProductos": []
}

# Agregar productos al carro
PUT http://localhost:8089/api/v1/carros/"IDCARRO"/productos/"IDPRODUCTO"

# Eliminar productos del carro
DELETE http://localhost:8089/api/v1/carros/"IDCARRO"/productos/"IDPRODUCTO"

# application.properties

spring.application.name=carro-microservicio

server.port=8089

 # MySQL Connection Settings

spring.datasource.url=jdbc:mysql://localhost:3306/dbcarro

spring.datasource.username=root

spring.datasource.password=

# JPA/Hibernate Settings

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true
