# Knowledge Wardrobe | Armario del Conocimiento
Este proyecto es una aplicación de gestión de conocimientos desarrollada en Java utilizando JavaFX y MySQL. Permite a los usuarios registrar sus conocimientos y gestionarlos de manera efectiva.

## Funcionalidades

- Inicio de sesión.
- Registro de usuarios.
- Agregar y visualizar conocimientos.
- Cambiar el estado de los conocimientos (Aprendiendo, Principiante, Dominado).

## Tecnologías Utilizadas

- Java 23
- JavaFX
- MySQL

## Estructura del Proyecto

- **src/**: Contiene el código fuente del proyecto.
    - **acceso.dam.proyectosql.Controlador**: Clases que manejan la lógica de la interfaz de usuario.
    - **acceso.dam.proyectosql.DAO**: Clases de acceso a datos para interactuar con los objetos de la base de datos.
    - **acceso.dam.proyectosql.domain**: Clases que representan las entidades del sistema (Usuario, Conocimiento), coincidentes con las tablas de la base de datos.
    - **acceso.dam.proyectosql.util**: Clases utilitarias para manejar alertas, recursos y utilidades varias.
- **images/**: Carpeta que contiene las imágenes utilizadas en la aplicación.
- **configuration/**: Archivos de configuración de la aplicación.
- **ui/**: Archivos FXML para la interfaz de usuario.
- **styles/**: Archivos CSS para los estilos de la aplicación.

## Instalación

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/alvarocille/knowledge-wardrobe.programming-project.git
2. Abre el proyecto en un IDE (IntelliJ Idea).
3. Compila el proyecto y ejecuta la clase Main.

## Cambios Pendientes
- Gestionar la edición y eliminación de conocimientos.
- Implementar un sistema de gestión de usuarios que permita la edición y eliminación de usuarios existentes.
- Añadir soporte para múltiples idiomas en la interfaz de usuario.
- Mejorar el rendimiento de carga de datos al optimizar las consultas a la base de datos.

## Autor

Álvaro Cilleruelo Sinovas   
alvaro@cillesino.com
