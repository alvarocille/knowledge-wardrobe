package acceso.dam.proyectosql.domain;

import acceso.dam.proyectosql.util.R;
import javafx.scene.image.Image;

import java.util.Objects;

public class Conocimiento {
    private int idConocimiento;
    private String nombre;
    private String descripcion;
    private int estado;
    private int idUsuario;


    public Conocimiento(int idConocimiento, String nombre, int estado, String descripcion, int idUsuario) {
        this.idConocimiento = idConocimiento;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
    }

    public Conocimiento(String nombre, String descripcion, int estado, int idUsuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

    public int getIdConocimiento() {
        return idConocimiento;
    }

    public void setIdConocimiento(int idConocimiento) {
        this.idConocimiento = idConocimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String toString() {
        return String.format("%s (%s) %s", nombre, descripcion, estado);
    }
}
