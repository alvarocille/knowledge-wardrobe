package acceso.dam.proyectosql.DAO;

import acceso.dam.proyectosql.domain.Conocimiento;
import static acceso.dam.proyectosql.util.DBManager.getConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * La clase {@code conocimientoDAO} proporciona métodos para interactuar con la base de datos
 * relacionada con los objetos de tipo {@code Conocimiento}.
 */
public class conocimientoDAO {
    private static Connection conexion;

    /**
     * Carga los conocimientos asociados a un usuario específico desde la base de datos.
     *
     * @param idUsuario El identificador del usuario para el cual se desean cargar los conocimientos.
     * @return Una lista de objetos {@code Conocimiento} que representan los conocimientos del usuario.
     * @throws SQLException           si hay un error al realizar la consulta.
     * @throws IOException            si hay un error al cargar el archivo de propiedades.
     * @throws ClassNotFoundException si no se encuentra el controlador JDBC.
     */
    public static ArrayList<Conocimiento> cargarConocimiento(int idUsuario) throws SQLException, IOException, ClassNotFoundException {
        conexion = getConnection();
        ArrayList<Conocimiento> conocimientos = new ArrayList<>();
        PreparedStatement sentencia;
        if (idUsuario != 1) {
            String sql = "SELECT * FROM conocimiento WHERE idUsuario = ?";
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idUsuario);
        } else {
            String sql = "SELECT * FROM conocimiento";
            sentencia = conexion.prepareStatement(sql);
        }

        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {
            int idConocimiento = resultado.getInt("idConocimiento");
            String nombre = resultado.getString("nombre");
            String descripcion = resultado.getString("descripcion");
            int estado = resultado.getInt("estado");

            Conocimiento conocimiento = new Conocimiento(idConocimiento, nombre, estado, descripcion, idUsuario);
            conocimientos.add(conocimiento);
        }
        return conocimientos;
    }

    /**
     * Agrega un nuevo conocimiento a la base de datos.
     *
     * @param conocimiento El objeto {@code Conocimiento} que se desea agregar.
     * @throws SQLException           si hay un error al realizar la operación de inserción.
     * @throws IOException            si hay un error al cargar el archivo de propiedades.
     * @throws ClassNotFoundException si no se encuentra el controlador JDBC.
     */
    public static void addConocimiento(Conocimiento conocimiento) throws SQLException, IOException, ClassNotFoundException {
        conexion = getConnection();
        String sql = "INSERT INTO conocimiento (nombre, estado, descripcion, idUsuario) VALUES (?, ?, ?, ?)";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, conocimiento.getNombre());
        sentencia.setInt(2, conocimiento.getEstado());
        sentencia.setString(3, conocimiento.getDescripcion());
        sentencia.setInt(4, conocimiento.getIdUsuario());
        sentencia.executeUpdate();
    }
}
