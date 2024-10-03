DROP DATABASE IF EXISTS armario;
CREATE DATABASE armario;
Use armario;


CREATE TABLE Usuario(
                        idUsuario int unsigned auto_increment primary key,
                        Nombre varchar(32) NOT NULL,
                        Password varchar(64),
                        Email varchar(32)
);

INSERT INTO Usuario VALUES
    (1, "alvarocille","password","alvaro.cilsin@educa.jcyl.es");

CREATE TABLE Conocimiento (
                              idConocimiento int unsigned auto_increment primary key,
                              Nombre VARCHAR(32) NOT NULL,
                              Descripcion VARCHAR(64),
                              Estado INT,
                              Imagen BLOB,
                              idUsuario INT);

INSERT INTO Conocimiento (nombre, descripcion, estado, idUsuario) VALUES
    ('JavaFX', 'Conocimiento sobre interfaces gráficas en JavaFX', 1, 1);
