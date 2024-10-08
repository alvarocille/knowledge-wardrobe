DROP DATABASE IF EXISTS armario;
CREATE DATABASE armario;
Use armario;


CREATE TABLE Usuario(
idUsuario int unsigned auto_increment primary key,
Nombre varchar(32),
Password varchar(64),
Email varchar(32)
);

CREATE TABLE Conocimiento(
    idConocimiento int unsigned auto_increment primary key,
    Nombre varchar(32),
    Descripcion varchar(64),
    Estado int,
    idUsuario int
);

 INSERT INTO Usuario VALUES
    (1,"alvarocille","password","alvaro.cilsin@educa.jcyl.es");

INSERT INTO conocimientos (nombre, descripcion, estado, idUsuario) VALUES
    ('JavaFX', 'Conocimiento sobre interfaces gráficas en JavaFX', 1, 1);



