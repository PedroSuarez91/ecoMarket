# Integrantes
- Benjamin Carrillo
- Nicolas Castillo
- Pedro Suarez

## Tecnologías utilizadas

- Java 24
- Spring Boot 4.0.6
- Spring Data JPA
- MySQL (XAMPP / MySQL local)
- Lombok
- RestTemplate
- Maven

---

## Arquitectura general

```
usuario_microservicio   →   carro_microservicio   →   producto_servicio
                                    ↓
                        pedido_servicio_servicioso
                                    ↓
                            compra_servicio   →   cupon_servicio
                                    ↓             producto_servicio
                            pago_servicio     →   boleta_servicio
                                    ↓
                        envio_servicio_servicioso  →  pago_servicio
                                                   →  pedido_servicio

Servicios independientes: empleado_microservicio, tienda_microservicio,
                          proveedor_microservicio, solicitud_microservicio
```

---

## Microservicios

| Servicio | Puerto | Base de datos |
|---|---|---|
| `usuario_microservicio` | 8081 | `usuariosdb` |
| `tienda_microservicio` | 8083 | `tiendasdb` |
| `proveedor_microservicio` | 8085 | `proveedoresdb` |
| `carro_microservicio` | 8089 | `dbcarro` |
| `empleado_microservicio` | 8100 | `dbempleado` |
| `solicitud_microservicio` | 8101 | `dbsolicitud` |
| `envio_servicio_servicioso` | 8102 | `dbenvio` |
| `pedido_servicio_servicioso` | 8104 | `dbpedido` |
| `boleta_servicio` | 9091 | `dbboleta` |
| `compra_servicio` | 9092 | `dbcompra` |
| `cupon_servicio` | 9093 | `dbcupon` |
| `producto_servicio` | 9094 | `dbproducto` |
| `pago_servicio` | 9095 | `dbpago` |

---

## Funcionalidades implementadas

### Usuario
- Crear, listar, modificar y eliminar usuarios
- Asignar un carro de compras a un usuario (`PUT /api/v1/usuarios/{id}/carro/{idCarro}`)
- Desactivar cuenta de usuario (`PATCH /api/v1/usuarios/{id}/desactivar`)

### Carro
- Crear y eliminar carros
- Agregar productos al carro — consulta precio real a `producto_servicio` y recalcula total con IVA (19%)
- Eliminar productos del carro — descuenta del subtotal y recalcula

### Producto
- CRUD completo de productos (marca, nombre, precio, categoría, stocks, descripciones)
- Categorías disponibles: Alimentos, Limpieza, Higiene, Tecnología, Hogar, Ropa, Reciclaje

### Compra
- Crear compra — verifica stock de cada producto en `producto_servicio`
- Calcular total — consulta precios reales a `producto_servicio`
- Aplicar descuento — valida el cupón en `cupon_servicio` (FIJO o PORCENTAJE)
- Confirmar compra — crea el pago en `pago_servicio`
- Cancelar compra

### Cupón / Descuento
- CRUD completo de cupones
- Tipos de cupón: `FIJO` (monto fijo en pesos) y `PORCENTAJE`

### Pago
- Registrar intento de pago
- Seleccionar método de pago (EFECTIVO, DÉBITO, CRÉDITO, TRANSFERENCIA)
- Confirmar pago — genera boleta automáticamente en `boleta_servicio`
- Cancelar pago

### Boleta / Factura
- Generar documento (BOLETA o FACTURA)
- Imprimir documento
- Enviar por email
- Exportar como PDF

### Pedido
- CRUD completo de pedidos
- Crear pedido desde un carro (`POST /api/v1/pedidos/carro/{idCarro}`) — copia total, subtotal y ubicación

### Envío
- CRUD completo de envíos
- Generar envío automático (`POST /api/v1/envios/generar?idPago=X&idPedido=Y`):
  1. Consulta al `pago_servicio` si el pago está aceptado
  2. Si está aceptado, obtiene la dirección del `pedido_servicio`
  3. Crea el envío con estado PENDIENTE y fecha de entrega en 5 días

### Empleado
- CRUD completo de empleados
- Cargos: Gerente, Subgerente, Cajero, Vendedor, Bodeguero, entre otros
- Turnos: Mañana, Mediodía, Noche

### Tienda
- Crear y listar tiendas
- Agregar y eliminar empleados de una tienda — verifica existencia en `empleado_microservicio`
- Listar normas de una tienda
- Modificar datos de la tienda

### Proveedor
- CRUD completo de proveedores

### Solicitud
- CRUD completo de solicitudes
- Tipos: Reclamo, Devolución, Reabastecimiento
- Estados: Pendiente, Enviado, Cancelado, Respondido

---

## Pasos para ejecutar

### 1. Requisitos previos

- Java 25 instalado
- Maven instalado
- XAMPP (o MySQL) corriendo en el puerto 3306
- Usuario MySQL: `root` sin contraseña (o ajustar en cada `application.properties`)

### 2. Crear las bases de datos

Abrir MySQL/phpMyAdmin y ejecutar:

```sql
CREATE DATABASE usuariosdb;
CREATE DATABASE tiendasdb;
CREATE DATABASE proveedoresdb;
CREATE DATABASE dbcarro;
CREATE DATABASE dbempleado;
CREATE DATABASE dbsolicitud;
CREATE DATABASE dbenvio;
CREATE DATABASE dbpedido;
CREATE DATABASE dbboleta;
CREATE DATABASE dbcompra;
CREATE DATABASE dbcupon;
CREATE DATABASE dbproducto;
CREATE DATABASE dbpago;
```

### 3. Ejecutar cada microservicio

Abrir una terminal por cada microservicio y ejecutar:

```bash
cd nombre_del_microservicio
mvn spring-boot:run
```

O desde el IDE (IntelliJ / Eclipse): abrir cada carpeta como proyecto Maven y ejecutar la clase `Application` principal.

**Orden recomendado de arranque** (de menos a más dependencias):

1. `producto_servicio` — `:9094`
2. `cupon_servicio` — `:9093`
3. `boleta_servicio` — `:9091`
4. `pago_servicio` — `:9095`
5. `carro_microservicio` — `:8089`
6. `compra_servicio` — `:9092`
7. `pedido_servicio_servicioso` — `:8104`
8. `envio_servicio_servicioso` — `:8102`
9. `empleado_microservicio` — `:8100`
10. `tienda_microservicio` — `:8083`
11. `usuario_microservicio` — `:8081`
12. `proveedor_microservicio` — `:8085`
13. `solicitud_microservicio` — `:8101`

### 4. Verificar que los servicios están corriendo

Probar en el navegador o Postman:

```
GET http://localhost:9094/api/v1/productos
GET http://localhost:8089/api/v1/carros
GET http://localhost:9095/api/v1/pagos
```

Deben responder `204 No Content` (si no hay datos aún) o `200 OK`.

---

## Flujo principal de una compra

```
1. POST /api/v1/carros                          → crear carro
2. PUT  /api/v1/carros/{id}/productos/{idProd}  → agregar productos
3. POST /api/v1/pedidos/carro/{idCarro}?ubicacion=... → crear pedido desde carro
4. POST /api/v1/compras                         → crear compra con los productos
5. PUT  /api/v1/compras/{id}/calcular-total     → calcular total real
6. PUT  /api/v1/compras/{id}/aplicar-descuento?cuponId=1 → aplicar cupón (opcional)
7. PUT  /api/v1/compras/{id}/confirmar?metodoPago=DEBITO → confirmar y crear pago
8. PUT  /api/v1/pagos/{id}/confirmar            → confirmar pago (genera boleta)
9. POST /api/v1/envios/generar?idPago=X&idPedido=Y → generar envío
```

---

## Notas

- Todas las tablas se crean automáticamente al iniciar cada servicio (`ddl-auto=update`).
- Si se cambia el puerto de un servicio, actualizar la URL correspondiente en el `application.properties` del servicio que lo consume.
- Los servicios no requieren autenticación para ser consumidos.
  
