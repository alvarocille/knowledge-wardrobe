package acceso.dam.proyectosql.DAO;

import acceso.dam.proyectosql.domain.Usuario;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

import static acceso.dam.proyectosql.util.DBManager.getConnection;

/**
 * La clase {@code usuarioDAO} proporciona métodos para interactuar con la base de datos
 * relacionada con los objetos de tipo {@code Usuario}.
 */
public class usuarioDAO {
    private static Connection conexion;

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto {@code Usuario} que se desea insertar.
     * @throws SQLException si hay un error al realizar la operación de inserción.
     */
    public static void insertarUsuario(Usuario usuario) throws SQLException {
        conexion = getConnection();
        String sql = "INSERT INTO usuario (nombre, password, email) VALUES (?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, usuario.getNombre());
        sentencia.setString(2,  DigestUtils.sha256Hex(usuario.getPassword()));
        sentencia.setString(3, usuario.getEmail());
        sentencia.executeUpdate();
    }

    /**
     * Busca un usuario en la base de datos por nombre y contraseña.
     *
     * @param nombre   El nombre del usuario a buscar.
     * @param password La contraseña del usuario a buscar.
     * @return Un objeto {@code Usuario} si se encuentra, {@code null} en caso contrario.
     * @throws SQLException si hay un error al realizar la consulta.
     */
    public static Usuario buscarUsuario(String nombre, String password) throws SQLException {
        conexion = getConnection();
        String sql = "SELECT * FROM usuario WHERE nombre = ? AND password = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, nombre);
        sentencia.setString(2, DigestUtils.sha256Hex(password));

        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            int idUsuario = resultado.getInt("idUsuario");
            String nombreUsuario = resultado.getString("nombre");
            String passwordUsuario = resultado.getString("password");
            String email = resultado.getString("email");

            return new Usuario(idUsuario, nombreUsuario, passwordUsuario, email);
        } else {
            return null;
        }
    }

}
