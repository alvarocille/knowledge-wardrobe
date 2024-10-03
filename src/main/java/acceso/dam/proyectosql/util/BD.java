package acceso.dam.proyectosql.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BD {

    public static Connection conectar() throws ClassNotFoundException, SQLException, IOException {
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
            return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC", username, password);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos:" + e);
        }
        return null;
    }

    public static void desconectar(Connection conexion) throws SQLException {
        conexion.close();
    }
}
