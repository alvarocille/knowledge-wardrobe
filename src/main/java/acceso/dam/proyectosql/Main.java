package acceso.dam.proyectosql;

import acceso.dam.proyectosql.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        InicioControlador controller = new InicioControlador();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("inicio.fxml"));
        loader.setController(controller);
        VBox vbox = loader.load();

        Scene scene = new Scene(vbox);
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

