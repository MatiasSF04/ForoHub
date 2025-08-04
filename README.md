# ForoHub API

>**ForoHub** es una API para administrar un foro de discusión gestionando tanto tópicos, como respuestas.

 _@MatiasSF04_

---

## Funcionalidades principales

### Tópicos
- `POST /topicos`: Crear y guardar un nuevo tópico.
- `GET /topicos`: Listar **todos** los tópicos activos.
- `GET /topicos/antiguos`: Listar los 10 tópicos activos más antiguos del archivo.
- `GET /topicos/recientes`: Listar los 10 tópicos activos más recientes del archivo.
- `GET /topicos/curso`: Filtrar los tópicos por curso.
- `GET /topicos/anio`: Filtrar los tópicos por año.
- `GET /topicos/{id}`: Mostrar el detalle completo de un tópico (Buscar tópico por id).
- `PUT /topics`: Actualizar los datos de un tópico (Autor y Fecha de Creación son inmutables).
- `DELETE /topics/{id}`: Eliminar un tópico.

### Autenticación
- `POST /login`: Generar el Token de Autenticación.

---

## Seguridad y autenticación

- JWT como mecanismo de autenticación.
- Filtros de seguridad con `SecurityFilterChain`.
- Swagger habilitado con botón **Authorize** (soporta bearer token).

---

## Tecnologías Utilizadas

- **Spring Boot 3.x**, **Spring Security**, **JWT**
- **MySQL**, **JPA**, **Flyway**
- **Lombok**, **SpringAI**, **OpenAI**
- **Swagger UI**, **Insomnia**

---

## Requisitos

- Java 17+
- Maven
- MySQL

---

## Instalación rápida

1. Clonar el repo:

   ```bash
   git clone https://github.com/MatiasSF04/ForoHub.git
   
Configurar base de datos y variables de entorno:
Crea un archivo application.properties local o usa .env/perfil prod con las siguientes variables:

spring.datasource.url=jdbc:mysql://[DB_HOST]:[DB_PORT]/[DB_NAME]  
spring.datasource.username=[DB_USER]  
spring.datasource.password=[DB_PASSWORD]  
api.security.token.secret=[JWT_SECRET]

Levantar la app:  
./mvnw spring-boot:run  

Swagger:  
http://localhost:8080/swagger-ui.html  

Variables de entorno requeridas  
Variable----------------Descripción  
DB_HOST-------------Host de la base de datos (puede ser localhost para desarrollo).  
DB_PORT-------------El puerto en que se desenvuelve la base de datos.  
DB_NAME------------El nombre de la base de datos del proyecto.  
DB_USER-------------Usuario de base de datos  
DB_PASSWORD----Contraseña de base de datos  
JWT_SECRET-------Clave secreta para firmar tokens  

---

Autor:  _@MatiasSF04_
