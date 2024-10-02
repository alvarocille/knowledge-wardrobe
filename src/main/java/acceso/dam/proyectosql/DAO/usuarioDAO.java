package acceso.dam.proyectosql.DAO;

import acceso.dam.proyectosql.domain.Usuario;
import acceso.dam.proyectosql.util.R;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class usuarioDAO {
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

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public static void insertarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nombre, password, email) VALUES (?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, usuario.getNombre());
        sentencia.setString(2, usuario.getPassword());
        sentencia.setString(3, usuario.getEmail());
        sentencia.executeUpdate();
    }

    public static Usuario buscarUsuario(String nombre, String password) throws SQLException {
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
        } else {
            return null;
        }
    }




}