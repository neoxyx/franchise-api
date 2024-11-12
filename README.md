# API de Franquicias

Este proyecto proporciona una API RESTful para gestionar una red de franquicias, permitiendo a los usuarios crear, actualizar y consultar franquicias, sucursales y productos asociados a cada sucursal.

## Tabla de Contenidos
- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso](#uso)
- [Endpoints](#endpoints)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Características
- **Gestión de Franquicias**: Permite crear y gestionar franquicias.
- **Gestión de Sucursales**: Añadir y administrar sucursales asociadas a cada franquicia.
- **Gestión de Productos**: Permite agregar productos a las sucursales y gestionar el stock de los productos.
- **PostgreSQL y R2DBC**: Utiliza PostgreSQL como base de datos y R2DBC para la comunicación reactiva.

## Requisitos
- **Java 17** o superior.
- **Spring Boot 2.5+**.
- **PostgreSQL**.
- **Maven** para la gestión de dependencias.
- **Docker** (opcional, para ejecutar PostgreSQL en contenedor).
- **Git** para clonar el proyecto.

## Instalación

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tu-usuario/api-franquicias.git
   cd api-franquicias
2. **Instalar dependencias: Si estás usando Maven**:
   mvn clean install
3. **Configurar la base de datos**:
   Asegúrate de tener PostgreSQL en ejecución.
Crea una base de datos llamada franchise_db.
Configura las credenciales en src/main/resources/application.properties (o usa un archivo .env para configuraciones locales).
4. **Ejecutar la aplicación**:
  mvn spring-boot:run

**Configuración**
Configura la conexión de base de datos y otros ajustes en el archivo application.properties o mediante variables de entorno:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/franchise_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/franchise_db
spring.r2dbc.username=postgres
spring.r2dbc.password=your_password

**Uso**
La API está disponible en http://localhost:8080/api/franchises.

**Endpoints**
1. Crear una franquicia
Endpoint: POST /api/franchises
Body:
json
{
  "name": "Nombre de la Franquicia"
}
Response: Devuelve el objeto de la franquicia creada.

2. Añadir sucursal a una franquicia
Endpoint: POST /api/franchises/{franchiseId}/branches
Body:
json
{
  "location": "Ubicación de la sucursal",
  "manager": "Nombre del Gerente"
}
Response: Devuelve el objeto de la sucursal creada.

4. Añadir producto a una sucursal
Endpoint: POST /api/franchises/branches/{branchId}/products
Body:
json
{
  "name": "Nombre del producto",
  "stock": 100
}
Response: Devuelve el objeto del producto creado.

6. Actualizar stock de un producto
Endpoint: PUT /api/franchises/products/{productId}
Body:
json
{
  "stock": 50
}
Response: Devuelve el producto actualizado con el nuevo stock.

8. Consultar productos con mayor stock para una franquicia
Endpoint: GET /api/franchises/{franchiseId}/products/highest-stock
Response: Devuelve una lista de productos ordenada por stock descendente.

**Estructura del Proyecto**
controller/: Controladores que manejan las solicitudes HTTP.
service/: Lógica de negocio de la aplicación.
repository/: Interfaces de acceso a la base de datos usando R2DBC.
model/: Modelos de datos que representan las entidades en la base de datos.

**Tecnologías Utilizadas**
Spring Boot y Spring WebFlux para API reactiva.
R2DBC para la conectividad reactiva a la base de datos.
PostgreSQL como base de datos relacional.
Maven como herramienta de construcción.
Docker (opcional) para contenedores.
Contribuciones
Las contribuciones son bienvenidas. Por favor, sigue los siguientes pasos:

Haz un fork de este repositorio.
Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
Realiza tus cambios y haz un commit (git commit -am 'Añadir nueva funcionalidad').
Sube tu rama (git push origin feature/nueva-funcionalidad).
Abre un Pull Request.

**Licencia**
Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.

Este README proporciona una guía completa sobre la API, su instalación, configuración, uso y contribución. Puedes adaptarlo a las necesidades específicas de tu proyecto.

