package acceso.dam.proyectosql;

import acceso.dam.proyectosql.Controlador.InicioControlador;
import acceso.dam.proyectosql.util.DBManager;
import acceso.dam.proyectosql.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;

/**
 * Clase principal del proyecto que extiende de {@link Application}.
 * Esta clase se encarga de iniciar la aplicación JavaFX y configurar la interfaz de usuario.
 *
 * @author alvaro.cilsin
 */
public class Main extends Application {

    /**
     * Metodo principal de ejecución. Llama a {@link launch} para iniciar la aplicación JavaFX.
     *
     * @param args Argumentos de la línea de comandos (si los hubiera).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metodo de inicialización llamado antes de que la aplicación comience.
     * Puede usarse para configurar recursos necesarios antes de ejecutar la aplicación.
     *
     * @throws Exception si ocurre algún error durante la inicialización.
     */
    @Override
    public void init() throws Exception {
        super.init();
    }

    /**
     * Metodo de entrada principal de la aplicación. Configura la ventana principal (Stage),
     * carga la interfaz de usuario desde el archivo FXML, y aplica los estilos.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws Exception si ocurre algún error al cargar la interfaz de usuario.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Image icono = new Image(Objects.requireNonNull(R.getImage("icono.png")));
        stage.getIcons().add(icono);

        InicioControlador controladorInicio = new InicioControlador(icono);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("inicio.fxml"));
        loader.setController(controladorInicio);
        VBox vbox = loader.load();

        Scene scene = new Scene(vbox);
        scene.getStylesheets().add(R.getEstilos("estilos.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Proyecto SQL");
        stage.show();

        stage.setOnCloseRequest((WindowEvent _) -> DBManager.closeConnection());
    }
}
