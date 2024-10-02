package acceso.dam.proyectosql.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class AlertUtils {

    public static void mostrarAviso(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso.");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.show();
    }

    public static void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error.");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.show();
    }

    public static void mostrarConfirmacion(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Confirmación.");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.show();

        ImageView iconoOriginal = (ImageView) alerta.getDialogPane().getGraphic();
        double ancho = iconoOriginal.getImage().getWidth();
        double altura = iconoOriginal.getImage().getHeight();

        Image iconoTick = new Image(Objects.requireNonNull(R.getImage("iconoVerificado.png")));
        ImageView imageView = new ImageView(iconoTick);
        imageView.setFitWidth(ancho);
        imageView.setFitHeight(altura);
        imageView.setPreserveRatio(true);

        alerta.setGraphic(imageView);
        alerta.getButtonTypes().setAll(ButtonType.OK);

        alerta.show();
    }
}
