package acceso.dam.proyectosql.DAO;

import acceso.dam.proyectosql.domain.Usuario;
import acceso.dam.proyectosql.util.AlertUtils;

import java.io.IOException;
import java.sql.*;

import static acceso.dam.proyectosql.util.BD.conectar;

public class usuarioDAO {

    public static void insertarUsuario(Usuario usuario) throws SQLException {
        try {
            Connection conexion = conectar();
            String sql = "INSERT INTO usuario (nombre, password, email) VALUES (?, ?, ?)";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, usuario.getNombre());
            sentencia.setString(2, usuario.getPassword());
            sentencia.setString(3, usuario.getEmail());
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuración");
        }
    }

    public static Usuario buscarUsuario(String nombre, String password) throws SQLException {
        try {
            Connection conexion = conectar();
            String sql = "SELECT * FROM usuario WHERE nombre = ? AND password = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, password);
            ResultSet resultado = sentencia.executeQuery();

            if (resultado.next()) {
                int idUsuario = resultado.getInt("idUsuario");
                String nombreUsuario = resultado.getString("nombre");
                String passwordUsuario = resultado.getString("password");
                String email = resultado.getString("email");

                return new Usuario(idUsuario, nombreUsuario, passwordUsuario, email);
            }
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuración");
        }
        return null;
    }




}