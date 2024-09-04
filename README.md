# Microservicio de Creación de Usuarios

Este repositorio contiene un microservicio que expone una API RESTful para la creación de usuarios. Está construido con Spring Boot y utiliza JPA para la persistencia de datos. La aplicación está configurada para usar un banco de datos en memoria y está lista para ser desplegada y probada.

## Requisitos

- **Java**: 8+
- **Framework**: Spring Boot
- **Persistencia**: JPA (Hibernate)
- **Base de Datos en Memoria**: H2
- **Build Tool**: Gradle o Maven

## Instalación

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/panxomon/user-registration-api
   

2. **Construir el proyecto:**:
    ```bash
    mvn clean install

3. **Ejecutar el proyecto:**
   Para iniciar la aplicación, puedes usar el siguiente comando:
    ```bash
    mvn spring-boot:run

### La aplicación estará disponible en http://localhost:8080

1. Endpoints de la API: Registro de Usuario
2. URL: /api/users/register
3. Método: POST
4. Descripción: Registra un nuevo usuario en el sistema.


## CURL

```shell
curl --location 'http://localhost:8080/api/users/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer AAIgZTY3YzVlYTVjYzEwOGRlYTg4YTdlNGM4NTc1NWZmZGPaS5J1vTbpnUws8YlGHoEwte8gqW8AYAdXL107NBIjUbbw-TkLPUJlBNIkFKROBT6dRNy7Qeh_fdN9INWgfz02c_FjEbwW5Hubbczq5gnKzsesa5T4zDxGwozN-mDgz4o-WjQr-UXDmJfOpNwBjFNW' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countryCode": "56"
        }
    ]
}'
```

## Respuesta Exitosa:

1. Código de Estado: 201 Created
```shell
    {
      "id": "uuid-del-usuario",
      "created": "2024-09-01T00:00:00Z",
      "modified": "2024-09-01T00:00:00Z",
      "last_login": "2024-09-01T00:00:00Z",
      "token": "uuid-o-jwt-del-token",
      "isactive": true
    }
```

## H2 Database Console

Este proyecto utiliza una base de datos H2 en memoria para propósitos de desarrollo y pruebas. Puedes acceder a la consola de administración de la base de datos H2 para visualizar y manipular los datos directamente.

### Acceso a la consola H2

La consola de administración de H2 está disponible en la siguiente URL:

[http://localhost:8080/h2-console/login.jsp](http://localhost:8080/h2-console/login.jsp)

### Detalles de conexión

Utiliza las siguientes credenciales para acceder a la base de datos:

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Usuario:** `sa`
- **Contraseña:** `password`

### Instrucciones

1. Abre la URL en tu navegador.
2. Introduce los detalles de conexión proporcionados.
3. Haz clic en "Conectar" para acceder a la consola y comenzar a explorar la base de datos.

**Nota:** La base de datos H2 es una base de datos en memoria, lo que significa que todos los datos se perderán cuando se detenga la aplicación.


## Diagrama de Secuencia

Aquí está el diagrama de secuencia que ilustra el flujo del proceso de registro de usuario:

![Diagrama de Secuencia](diagrams/secuencia.jpg)
