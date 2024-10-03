package acceso.dam.proyectosql.DAO;

import acceso.dam.proyectosql.domain.Conocimiento;
import acceso.dam.proyectosql.util.AlertUtils;
import javafx.scene.image.Image;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import static acceso.dam.proyectosql.util.BD.conectar;
import static acceso.dam.proyectosql.util.BD.desconectar;

public class conocimientoDAO {
    public void guardarConocimiento(Conocimiento conocimiento) {
        Connection conexion = null; // Declarar la conexión aquí
        try {
            conexion = conectar();
            String sql = "INSERT INTO conocimiento (nombre, descripcion, estado, imagen, idUsuario) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, conocimiento.getNombre());
            sentencia.setString(2, conocimiento.getDescripcion());
            sentencia.setString(3, "" + conocimiento.getEstado());
            
            Image rutaImagen = conocimiento.getImagen();
            FileInputStream fis = new FileInputStream(String.valueOf(rutaImagen));
            if (rutaImagen != null && !rutaImagen.isError()) {
                sentencia.setBinaryStream(5, fis, fis.available());
            } else {
                sentencia.setNull(5, Types.BLOB);
            }

            sentencia.setString(4, "" + conocimiento.getIdUsuario());

            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al guardar el conocimiento: " + sqle.getMessage());
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al procesar la imagen: " + ioe.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (conexion != null) {
                try {
                    desconectar(conexion);
                } catch (SQLException e) {
                    AlertUtils.mostrarError("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }


    public ArrayList<Conocimiento> obtenerConocimientos(int idUsuario) {
        ArrayList<Conocimiento> conocimientos = new ArrayList<>();
        Connection conexion = null; // Declarar la conexión aquí
        ResultSet resultSet = null; // Declarar el ResultSet aquí

        try {
            conexion = conectar();
            String sql = "SELECT * FROM conocimiento WHERE idUsuario = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, "" + idUsuario);
            resultSet = sentencia.executeQuery();

            while (resultSet.next()) {
                int idConocimiento = resultSet.getInt("idConocimiento");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                int estado = resultSet.getInt("estado");
                byte[] imagenBytes = resultSet.getBytes("imagen");

                Image imagen = null;
                if (imagenBytes != null) {
                    InputStream is = new ByteArrayInputStream(imagenBytes);
                    imagen = new Image(is);
                }

                Conocimiento conocimiento = new Conocimiento(nombre, descripcion, estado, imagen, idUsuario);
                conocimientos.add(conocimiento);
            }

        } catch (SQLException | ClassNotFoundException sqle) {
            AlertUtils.mostrarError("Error al obtener los conocimientos: " + sqle.getMessage());
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al procesar la imagen: " + ioe.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    AlertUtils.mostrarError("Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    desconectar(conexion);
                } catch (SQLException e) {
                    AlertUtils.mostrarError("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }

        return conocimientos;
    }

}
