---

# Sistema de Gestión de Seguros

Autor: **Cristian Camilo Vera**

Aplicación de escritorio moderna y minimalista desarrollada en Java bajo una **Arquitectura Hexagonal (Puertos y Adaptadores)**, utilizando **JavaFX** para la interfaz gráfica y una base de datos relacional **MySQL** mediante conectividad **JDBC**.

---

##  Tabla de Contenidos

1. [Descripción del Proyecto](https://www.google.com/search?q=%23-descripci%C3%B3n-del-proyecto)
2. [Tecnologías Utilizadas](https://www.google.com/search?q=%23-tecnolog%C3%ADas-utilizadas)
3. [Estructura del Proyecto](https://www.google.com/search?q=%23-estructura-del-proyecto)
4. [Operaciones CRUD](https://www.google.com/search?q=%23-operaciones-crud)
5. [Configuración de la Base de Datos](https://www.google.com/search?q=%23-configuraci%C3%B3n-de-la-base-de-datos)
6. [Instalación y Ejecución](https://www.google.com/search?q=%23-instalaci%C3%B3n-y-ejecuci%C3%B3n)
7. [Diagramas y Video de Demostración](https://www.google.com/search?q=%23-diagramas-y-video-de-demostraci%C3%B3n)

---

##  Descripción del Proyecto

El **Sistema de Gestión de Seguros** es una solución de software diseñada para administrar pólizas de seguros de manera eficiente. La aplicación permite gestionar dos tipos de seguros principales (*Seguro de Vida* y *Seguro de Vehículo*) asociados a un *Asegurado* mediante una relación de uno a muchos (1 a N). Cuenta con una interfaz visual limpia y intuitiva en tonos oscuros y morados, validaciones de campos estrictas y control de errores mediante alertas gráficas de JavaFX (cero consola).

---

## Tecnologías Utilizadas

* **Java (JDK 17+)**: Lenguaje de programación principal orientada a objetos.
* **JavaFX 21**: Framework gráfico para la interfaz de usuario moderna basada en FXML y CSS personalizado.
* **MySQL Server**: Sistema de gestión de bases de datos relacionales.
* **JDBC (Java Database Connectivity)**: API de Java para la ejecución de consultas y transacciones SQL nativas.
* **Maven**: Herramienta de automatización de compilación y gestión de dependencias.

---

##  Estructura del Proyecto

El proyecto sigue estrictamente los principios de diseño de arquitectura limpia y hexagonal:

```text
GestionSeguros/
│
├── src/main/java/
│   └── co/edu/poli/seguros/
│       ├── Aplicacion/
│       │   ├── Puerto/
│       │   │   ├── Entrada/   (Casos de uso: Crear, Buscar, Editar, Eliminar)
│       │   │   └── Salida/    (Interfaces de repositorio)
│       │   └── Servicios/     (Implementación de los casos de uso)
│       ├── Dominio/           (Clases de negocio: Asegurado, Seguro, SeguroVida, SeguroVehiculo)
│       ├── Infraestructura/
│       │   ├── Persistencia/  (Conexión JDBC y MySqlSeguroRepository)
│       │   └── UI/            (Controlador JavaFX: SeguroControlador)
│       └── Vista/             (Clase principal App.java)
│
├── src/main/resources/
│   └── co/edu/poli/seguros/Vista/
│       ├── Seguros.fxml       (Interfaz gráfica minimalista)
│       └── estilos.css        (Estilos visuales personalizados)
│
└── module-info.java           (Configuración de módulos de Java)

```

---

##  Operaciones CRUD

El sistema implementa de forma íntegra las operaciones básicas sobre los seguros:

* **Crear**: Permite registrar un nuevo seguro (Vida o Vehículo) validando campos obligatorios y asociándolo a un asegurado predeterminado.
* **Buscar (Listar)**: Muestra en tiempo real todos los seguros registrados en una tabla interactiva con detalles específicos de cada póliza.
* **Editar**: Permite modificar los datos de un seguro existente buscando por su número de póliza único.
* **Eliminar**: Permite borrar de forma segura un seguro seleccionado directamente desde la tabla de registros.

---

##  Configuración de la Base de Datos

La base de datos relacional se llama **`GestionSeguros`**. Para configurarla, ejecuta el siguiente script en tu gestor MySQL:

```sql
CREATE DATABASE IF NOT EXISTS GestionSeguros;
USE GestionSeguros;

CREATE TABLE IF NOT EXISTS asegurado (
    identificacion VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS seguro (
    numero VARCHAR(20) PRIMARY KEY,
    fecha_expedicion DATE NOT NULL,
    valor DOUBLE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    nombre_beneficiario VARCHAR(100),
    parentesco VARCHAR(50),
    marca VARCHAR(50),
    asegurado_id VARCHAR(20) NOT NULL,
    CONSTRAINT fk_asegurado FOREIGN KEY (asegurado_id) REFERENCES asegurado(identificacion) ON DELETE CASCADE
);

```

---

##  Instalación y Ejecución

Sigue estos pasos para poner en marcha la aplicación en tu entorno local:

1. **Clonar o descargar** el repositorio del proyecto.
2. **Configurar la Base de Datos**: Asegúrate de tener MySQL Server activo y ejecuta el script SQL provisto en la sección anterior.
3. **Verificar Credenciales**: Abre la clase `co.edu.poli.seguros.Infraestructura.Persistencia.ConexionBD.java` y ajusta el usuario y contraseña de tu servidor MySQL si es necesario.
4. **Abrir en tu IDE**: Importa el proyecto en tu entorno de desarrollo compatible (IntelliJ IDEA o Eclipse con soporte para Maven y JavaFX).
5. **Ejecutar la Aplicación**: Corre la clase principal ubicada en `co.edu.poli.seguros.Vista.App.java`.

---

* 📐 **Diagrama de Clases y secuencia**: https://drive.google.com/file/d/1gKR5rPjqJ9IrbhRLiNSdlWEjwAekrX8h/view?usp=sharing
* 🎥 **Video de Demostración**: [Ver Video Explicativo en YouTube / Drive (Enlace de ejemplo)](https://www.google.com/search?q=https://tu-video-demostracion-aqui.com)
