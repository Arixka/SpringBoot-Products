# SpringBoot Products API 


## Descripción
SpringBoot Products es una aplicación Full Stack desarrollada en Java con Spring Boot y el frontend en React con . Permite gestionar productos con reducción de precios y estado. Utiliza una base de datos en memoria H2 para pruebas rápidas y cuenta con autenticación JWT.

## Tecnologías
- **Backend**: Java 8, Spring Boot 2.7.2, Spring Data JPA, MapStruct, Lombok
- **Frontend**: React 18, Vite, React Router DOM, Axios, Tailwind CSS
- **Base de datos**: H2 en memoria - proxima mejora
- **Autenticación**: JWT

## Instalación y Ejecución

### Requisitos
- Java 8 o superior
- Maven (opcional, ya que el proyecto incluye `mvnw` para ejecución sin Maven instalado)
- Node.js y npm para el frontend

### Pasos para ejecutar el Backend
1. Clonar el repositorio:
   ```sh
   git clone https://github.com/usuario/SpringBoot-Products.git
   cd SpringBoot-Products/ApiRestProducts
   ```
2. Construir y ejecutar la aplicación:
   ```sh
   ./mvnw spring-boot:run
   ```
   En Windows:
   ```sh
   mvnw.cmd spring-boot:run
   ```
3. Acceder a la API en `http://localhost:8080`

### Pasos para ejecutar el Frontend
1. Ir a la carpeta del frontend:
   ```sh
   cd ../react-client
   ```
2. Instalar dependencias:
   ```sh
   npm install
   ```
3. Ejecutar en modo desarrollo:
   ```sh
   npm run dev
   ```
4. Acceder a la aplicación en `http://localhost:5173`

### Consola H2
Se puede acceder a la base de datos en `http://localhost:8080/h2-ui`.

## Funcionalidades del Frontend
- **Login y autenticación con JWT**.
- **Listado de productos** con información detallada.
- **Creación, edición y eliminación de productos**.
- **Protección de rutas** para usuarios autenticados.

## Endpoints Principales
- **GET** `/products`: Lista los productos.
- **POST** `/products`: Crea un nuevo producto.
- **PUT** `/products/{id}`: Actualiza un producto.
- **DELETE** `/products/{id}`: Elimina un producto.

## Mejoras futuras
- Añadir una base de datos persistente (MySQL o PostgreSQL).
- Mejorar la seguridad con roles y permisos.
- Implementar tests unitarios y de integración en frontend y backend.

## Licencia
Este proyecto está licenciado bajo la MIT License - ver el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Para consultas o más información, puedes contactarme en:

[![Gmail](https://img.shields.io/badge/-Gmail-c14438?style=flat&logo=Gmail&logoColor=white)](mailto:marisiver25@gmail.com)
[![Linkedin](https://img.shields.io/badge/-LinkedIn-blue?style=flat&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/maria-siverio/)

