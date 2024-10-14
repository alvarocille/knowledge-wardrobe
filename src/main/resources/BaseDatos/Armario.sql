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
VALUES ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin@admin.admin'), -- pswd: "admin"
       ('alvarocille', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'alvaro.cilsin@educa.jcyl.es'), -- pswd: "password"
       ('mariafernandez', 'c2bd8e185bdee8ba3906293e11efa7b5519ce951116b3f11a3741ff1c61dd083', 'maria.fernandez@educa.jcyl.es'), -- pswd: "mariapass"
       ('joseperez', '237a75722f471dc3b91625f1555054f2375bc1745b398f73ccbc3ed9cccfbe6f', 'jose.perez@educa.jcyl.es'), -- pswd: "joseperez"
       ('luissanchez', 'c9d33dafc90cae266a0ef43ce32fc1a0ef71ec17cdd1132b4524ac8a14556080', 'luis.sanchez@educa.jcyl.es'), -- pswd: "luispass"
       ('pablomartinez', 'e5566854b235dabf3fad02584474b16201224ed344fe11f1b2d1a30e559e03c4', 'pablo.martinez@educa.jcyl.es'); -- pswd: "pablopass"

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