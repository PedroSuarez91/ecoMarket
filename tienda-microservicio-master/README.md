# Registrar tienda
POST http://localhost:8083/api/v1/tiendas

{
  "nombre": "Tienda EcoMarket Centro",
  "ubicacion": "Concepción",
  "normas": [
    "Mantener limpieza del local",
    "Atender cordialmente a los clientes"
  ],
  "horarioTienda": "Lunes a viernes de 09:00 a 18:00",
  "listaEmpleados": []
}

# Modificar tienda
PUT http://localhost:8083/api/v1/tiendas/"IDTIENDA"

{
  "nombre": "Nueva Tienda EcoMarket"
}

# Agregar empleados
PUT http://localhost:8083/api/v1/tiendas/"IDTIENDA"/empleados/"IDEMPLEADO"

# Eliminar empleados
DELETE http://localhost:8083/api/v1/tiendas/"IDTIENDA"/empleados/"IDEMPLEADO"



# application.properties

spring.application.name=tienda-microservicio

server.port=8083

# MySQL Connection Settings

spring.datasource.url=jdbc:mysql://localhost:3306/tiendasdb

spring.datasource.username=root

spring.datasource.password=

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Settings

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
