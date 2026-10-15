# Sistema de Gestión de Seguros

Autor: **Cristian Camilo Vera**

Aplicación de escritorio desarrollada con JavaFX y arquitectura hexagonal limpia, conectada a una base de datos relacional MySQL mediante JDBC.

## Requisitos previos

Antes de instalar y ejecutar la aplicación, asegúrate de contar con lo siguiente en tu entorno de desarrollo:

1. **Java Development Kit (JDK)** versión 17 o superior.
2. **JavaFX SDK** compatible con tu versión de Java.
3. **Apache Maven** instalado y configurado en las variables de entorno.
4. **MySQL Server** corriendo localmente en el puerto `3306`.
5. Un IDE compatible (IntelliJ IDEA recomendado).

## Paso a paso para la instalación y ejecución

1. **Clonar o descargar** este repositorio en tu equipo local.
2. **Configurar la Base de Datos**:
   - Abre tu cliente MySQL favorito (MySQL Workbench, DBeaver, etc.).
   - Ejecuta el script SQL proporcionado en la sección de base de datos para crear la base de datos `GestionSeguros` y sus tablas con relación 1 a muchos.
3. **Configurar la Conexión**:
   - Dirígete a la clase `co.edu.poli.seguros.Infraestructura.Persistencia.ConexionBD.java`.
   - Modifica el atributo `PASSWORD` con la contraseña de tu usuario root de MySQL.
4. **Configurar dependencias de JavaFX en Maven (`pom.xml`)**:
   - Asegúrate de incluir las dependencias de JavaFX y el driver JDBC de MySQL (`mysql-connector-j`).
5. **Ejecutar la aplicación**:
   - Ejecuta la clase principal ubicada en `co.edu.poli.seguros.Vista.App.java`.
