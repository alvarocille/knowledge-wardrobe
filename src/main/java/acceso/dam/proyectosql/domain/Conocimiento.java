package acceso.dam.proyectosql.domain;

import javafx.scene.image.Image;

public class Conocimiento {
    private int idConocimiento;
    private String nombre;
    private String descripcion;
    private int estado;
    private Image imagen;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    private int idUsuario;


    public Conocimiento(int idConocimiento, String nombre, String descripcion, int estado, Image imagen, int idUsuario) {
        this.idConocimiento = idConocimiento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.imagen = imagen;
        this.idUsuario = idUsuario;
    }

    public Conocimiento(String nombre, String descripcion, int estado, Image imagen, int idUsuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.imagen = imagen;
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

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}
