# 🚀 Monacohub - Forum Hub API

**Monacohub** es una API REST robusta desarrollada con **Spring Boot 3** para la gestión de un foro de discusión. Este proyecto destaca por su arquitectura de seguridad **Stateless** y una implementación profesional de **Spring Security** con **JWT**.

---

## 🛠️ Tecnologías Utilizadas

* **Java 17** (LTS)
* **Spring Boot 3.x**
* **Spring Data JPA** & **Hibernate**
* **MySQL**
* **Flyway** (Migraciones de DB)
* **Spring Security** & **JWT (Auth0)**
* **Lombok** & **Validation**

---

## 🔐 Implementación de Seguridad

El núcleo del proyecto es un sistema de seguridad avanzado que garantiza la integridad de los datos y el acceso controlado:

1.  **Autenticación Stateless**: No se utilizan sesiones de servidor. Toda la identidad viaja en un Token JWT firmado.
2.  **Cifrado de Contraseñas**: Uso de **BCryptPasswordEncoder** para el hashing seguro de credenciales.
3.  **Filtro Personalizado**: Implementación de `SecurityFilter` para interceptar cada petición, validar el token y autenticar al usuario dinámicamente.
4.  **Generación de Tokens**: Firma criptográfica con algoritmo **HMAC256** y tiempos de expiración controlados.



---

## ⚙️ Configuración y Requisitos

### 1. Variables de Entorno
Para que el sistema de seguridad funcione, debes configurar tu archivo `src/main/resources/application.properties` o definir las siguientes variables en tu entorno de desarrollo:

```properties
# Conexión a Base de Datos MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/monacohub_db
spring.datasource.username=tu_usuario_aqui
spring.datasource.password=tu_contraseña_aqui

# Configuración de Seguridad JWT
# Define una clave secreta robusta (mínimo 32 caracteres)
jwt.secret=${JWT_SECRET:mi_clave_secreta_super_segura_123456}
```

### 2. Dependencias Necesarias  
Asegúrate de tener estas dependencias en tu `pom.xml`:
- Spring Boot Starter Security
- Java JWT (Auth0)
- Spring Boot Starter Web
- Spring Data JPA

### 3. Ejecución del Proyecto
Una vez configurada la base de datos, puedes compilar y ejecutar el proyecto con:
```bash
mvn spring-boot:run
```
---
## 📂 Endpoints Principales

| Método | Endpoint | Descripción | Acceso |
| :--- | :--- | :--- | :--- |
| `POST` | `/login` | Autenticación de usuario y obtención del Token JWT. | **Público** |
| `GET` | `/topicos` | Lista todos los tópicos registrados en el foro. | **JWT Requerido** |
| `GET` | `/topicos/page` | Lista los tópicos registrados por pagina en el foro. | **JWT Requerido** |
| `GET` | `/topicos/{id}` | Obtiene los detalles de un tópico específico por su ID. | **JWT Requerido** |
| `POST` | `/topicos` | Registra un nuevo tópico en la base de datos. | **JWT Requerido** |
| `PUT` | `/topicos/{id}` | Actualiza el título o mensaje de un tópico existente. | **JWT Requerido** |
| `DELETE` | `/topicos/{id}` | Elimina de forma lógica o física un tópico del sistema. | **JWT Requerido** |
