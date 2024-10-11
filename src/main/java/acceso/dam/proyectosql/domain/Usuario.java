package acceso.dam.proyectosql.domain;

/**
 * La clase {@code Usuario} representa un usuario del sistema, con un identificador, nombre, contraseña y correo electrónico.
 * Los atributos corresponden con los campos de la base de datos.
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String password;
    private String email;

    /**
     * Constructor de la clase {@code Usuario} que recibe todos los parámetros.
     *
     * @param idUsuario El identificador único del usuario.
     * @param nombre    El nombre del usuario.
     * @param password  La contraseña del usuario.
     * @param email     El correo electrónico del usuario.
     */
    public Usuario(int idUsuario, String nombre, String password, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
    }

    /**
     * Constructor de la clase {@code Usuario} sin el identificador de usuario (AutoIncrement en la base de datos).
     *
     * @param nombre   El nombre del usuario.
     * @param password La contraseña del usuario.
     * @param email    El correo electrónico del usuario.
     */
    public Usuario(String nombre, String password, String email) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
    }

    /**
     * Devuelve el identificador único del usuario.
     *
     * @return El identificador del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Devuelve el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Devuelve el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Devuelve una representación en cadena del usuario, que incluye su identificador, nombre, contraseña y correo electrónico.
     *
     * @return Una cadena de texto que representa al usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
