# Crear Usuario
POST http://localhost:8081/api/v1/usuarios

{
  "nombre": "Pedro Suarez",
  "username": "psuarez",
  "password": "123456",
  "email": "pedro.suarez@gmail.com",
  "telefono": "+56912345678",
  "direccion": "Concepcion, Chile",
  "activo": true,
  "idCarro": null
}

# Desactivar usuario
PATCH http://localhost:8081/api/v1/usuarios/"IDUSUARIO"/desactivar

# Asignar carro de compra al usuario
PUT http://localhost:8081/api/v1/"IDUSUARIO"/carro/"IDCARRO"

# aplication.properties

spring.application.name=usuario-microservicio

server.port=8081

# MySQL Connection Settings

spring.datasource.url=jdbc:mysql://localhost:3306/usuariosdb

spring.datasource.username=root

spring.datasource.password=

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Settings

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
