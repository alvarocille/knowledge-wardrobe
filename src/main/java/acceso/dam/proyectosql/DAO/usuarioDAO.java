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
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public void registrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (idUsuario, nombre, password, email) VALUES (?, ?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, String.valueOf(usuario.getIdUsuario()));
        sentencia.setString(2, usuario.getNombre());
        sentencia.setString(3, usuario.getPassword());
        sentencia.setString(4, usuario.getEmail());
        sentencia.executeUpdate();
    }

    public void iniciarSesion(String nombre, String password) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE nombre = ? AND password = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, nombre);
        sentencia.setString(2, password);

        ResultSet resultado = sentencia.executeQuery();

        print(resultado);
    }




}
