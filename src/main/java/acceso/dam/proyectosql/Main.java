package acceso.dam.proyectosql;

import acceso.dam.proyectosql.util.R;
import acceso.dam.proyectosql.Controlador.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

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
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }

}

