package acceso.dam.proyectosql.domain;

/**
 * La clase {@code Conocimiento} representa un conocimiento que tiene un nombre, descripción, estado y un usuario asociado.
 * Los atributos corresponden con los campos de la base de datos.
 */
public class Conocimiento {
    private int idConocimiento;
    private String nombre;
    private String descripcion;
    private int estado;
    private int idUsuario;

    /**
     * Constructor de la clase {@code Conocimiento} que recibe todos los parámetros.
     *
     * @param idConocimiento El identificador único del conocimiento.
     * @param nombre         El nombre del conocimiento.
     * @param estado         El estado del conocimiento (indica si está activo o no).
     * @param descripcion    Una breve descripción del conocimiento.
     * @param idUsuario      El identificador del usuario asociado al conocimiento.
     */
    public Conocimiento(int idConocimiento, String nombre, int estado, String descripcion, int idUsuario) {
        this.idConocimiento = idConocimiento;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
    }

    /**
     * Constructor de la clase {@code Conocimiento} sin el identificador del conocimiento.
     *
     * @param nombre      El nombre del conocimiento.
     * @param descripcion Una breve descripción del conocimiento.
     * @param estado      El estado del conocimiento (indica si está activo o no).
     * @param idUsuario   El identificador del usuario asociado al conocimiento.
     */
    public Conocimiento(String nombre, String descripcion, int estado, int idUsuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

    /**
     * Devuelve el nombre del conocimiento.
     *
     * @return El nombre del conocimiento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la descripción del conocimiento.
     *
     * @return La descripción del conocimiento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve el estado del conocimiento.
     *
     * @return El estado del conocimiento.
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Devuelve el identificador del usuario asociado al conocimiento.
     *
     * @return El identificador del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Devuelve una representación en cadena del conocimiento, que incluye su nombre, descripción y estado.
     *
     * @return Una cadena de texto que representa al conocimiento.
     */
    @Override
    public String toString() {
        return String.format("%s (%s) %s", nombre, descripcion, estado);
    }
}
