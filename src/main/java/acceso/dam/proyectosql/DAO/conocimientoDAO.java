package acceso.dam.proyectosql.DAO;

import acceso.dam.proyectosql.domain.Conocimiento;
import acceso.dam.proyectosql.domain.Usuario;
import acceso.dam.proyectosql.util.R;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static acceso.dam.proyectosql.DAO.usuarioDAO.conectar;

public class conocimientoDAO {
    private static Connection conexion;

    public static void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        String host = "", port = "", name = "", username = "", password = "";
        try {
            configuration.load(R.getProperties("database.properties"));
            host = configuration.getProperty("host");
            port = configuration.getProperty("port");
            name = configuration.getProperty("name");
            username = configuration.getProperty("username");
            password = configuration.getProperty("password");
        } catch (Exception e) {
            System.out.println("Error al cargar los datos de conexión:" + e);
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                    username, password);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos:" + e);
        }
    }

    public static ArrayList<Conocimiento> cargarConocimiento(int idUsuario) throws SQLException, IOException, ClassNotFoundException {
        conectar();
        ArrayList<Conocimiento> conocimientos = new ArrayList<>();
        String sql = "SELECT * FROM conocimiento WHERE idUsuario = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, idUsuario);
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

    public static void addConocimiento(Conocimiento conocimiento) throws SQLException, IOException, ClassNotFoundException {
        conectar();
        String sql = "INSERT INTO conocimiento (nombre, estado, descripcion, idUsuario) VALUES (?, ?, ?, ?)";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, conocimiento.getNombre());
        sentencia.setInt(2, conocimiento.getEstado());
        sentencia.setString(3, conocimiento.getDescripcion());
        sentencia.setInt(4, conocimiento.getIdUsuario());
        sentencia.executeUpdate();
    }
}
