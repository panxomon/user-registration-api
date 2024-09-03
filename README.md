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

## Diagrama de Secuencia

Aquí está el diagrama de secuencia que ilustra el flujo del proceso de registro de usuario:

![Diagrama de Secuencia](diagrams/secuencia.jpg)
