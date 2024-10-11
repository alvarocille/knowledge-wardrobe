DROP DATABASE IF EXISTS armario;
CREATE DATABASE armario;
USE armario;

CREATE TABLE Usuario
(
    idUsuario INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Nombre    VARCHAR(32),
    Password  VARCHAR(64),
    Email     VARCHAR(32)
);

CREATE TABLE Conocimiento
(
    idConocimiento INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Nombre         VARCHAR(32),
    Descripcion    VARCHAR(64),
    Estado         INT,
    idUsuario      INT
);

INSERT INTO Usuario (Nombre, Password, Email)
VALUES ('admin', 'admin', 'admin@admin.admin'),
       ('alvarocille', 'password', 'alvaro.cilsin@educa.jcyl.es'),
       ('mariafernandez', 'mariapass', 'maria.fernandez@educa.jcyl.es'),
       ('joseperez', 'josepass', 'jose.perez@educa.jcyl.es'),
       ('luissanchez', 'luispass', 'luis.sanchez@educa.jcyl.es'),
       ('pablomartinez', 'pablopass', 'pablo.martinez@educa.jcyl.es');

INSERT INTO Conocimiento (Nombre, Descripcion, Estado, idUsuario)
VALUES ('JavaFX', 'Conocimiento sobre interfaces gráficas en JavaFX', 1, 2),
       ('JavaScript', 'Lenguaje de programación para desarrollo web', 2, 2),
       ('SQL', 'Lenguaje de consulta estructurado', 3, 2),
       ('Python', 'Lenguaje de programación de alto nivel', 1, 2),
       ('Java', 'Lenguaje de programación orientado a objetos', 3, 2),
       ('Spring Framework', 'Framework para aplicaciones Java', 2, 3),
       ('HTML y CSS', 'Tecnologías para el desarrollo web', 2, 4),
       ('C#', 'Lenguaje de programación de Microsoft', 2, 5),
       ('React', 'Biblioteca de JavaScript para interfaces de usuario', 1, 6),
       ('Django', 'Framework web para Python', 3, 6);




