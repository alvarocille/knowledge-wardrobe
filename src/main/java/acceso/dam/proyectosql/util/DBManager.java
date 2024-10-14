package acceso.dam.proyectosql.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * La clase <code>DBManager</code> gestiona la conexión a la base de datos.
 * Se asegura de que solo exista una conexión a la base de datos en cualquier momento,
 * proporcionando métodos para obtener y cerrar la conexión.
 * El constructor de esta clase es privado para evitar que se instancie,
 * ya que todos los métodos son estáticos.
 */
public class DBManager {
    /** La conexión a la base de datos. */
    public static Connection conexion;

    /**
     * Constructor privado para evitar que se instancie esta clase.
     */
    private DBManager() {
    }

    /**
     * Obtiene la conexión a la base de datos.
     * Si la conexión actual es nula o está cerrada, intenta establecer una nueva conexión
     * utilizando las propiedades definidas en el archivo <code>database.properties</code>.
     *
     * @return La conexión a la base de datos.
     * @throws RuntimeException Si hay un error al acceder al archivo de configuración,
     *                          si falta el driver de la base de datos, o si ocurre un error
     *                          al intentar conectarse a la base de datos.
     */
    public static Connection getConnection() {
        try {
            if (conexion == null || conexion.isClosed()) {
                System.out.println("Conectando a la base de datos...");
                Properties configuration = new Properties();
                String host = "", port = "", name = "", username = "", password = "";
                configuration.load(R.getProperties("database.properties"));
                host = configuration.getProperty("host");
                port = configuration.getProperty("port");
                name = configuration.getProperty("name");
                username = configuration.getProperty("username");
                password = configuration.getProperty("password");
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC", username, password);
            }
        } catch (IOException eIO) {
            throw new RuntimeException("Error: al acceder al fichero de configuración de la base de datos.");
        } catch (ClassNotFoundException enf) {
            throw new RuntimeException("Error: falta la dependencia necesaria para cargar el driver de la base de datos.");
        } catch (SQLException eSQL) {
            throw new RuntimeException("Error: al conectarse a la base de datos.");
        }
        return conexion;
    }

    /**
     * Cierra la conexión a la base de datos si está abierta.
     *
     * @throws RuntimeException Si hay un error al intentar cerrar la conexión.
     */
    public static void closeConnection() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión a la base de datos terminada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: no se ha podido cerrar la conexión a la base de datos");
        }
    }
}
