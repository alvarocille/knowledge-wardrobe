DROP DATABASE IF EXISTS Armario;
CREATE DATABASE Armario;
Use Armario;


CREATE TABLE Usuario(
Id int unsigned auto_increment primary key,
Nombre varchar(32),
Password varchar(64),
Email varchar(32)
);

 INSERT INTO Usuario VALUES
 (1,"alvarocille","password","alvaro.cilsin@educa.jcyl.es");



