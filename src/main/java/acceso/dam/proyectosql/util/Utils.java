package acceso.dam.proyectosql.util;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Utils {
    public static boolean cambiarVisibilidad(TextField visible, PasswordField oculto, ImageView imagen, boolean visibilidad) {
        if (visibilidad) {
            visible.setVisible(false);
            oculto.setText(visible.getText());
            oculto.setVisible(true);
            Image ojoCerrado = new Image(Objects.requireNonNull(R.getImage("ojoCerrado.png")));
            imagen.setImage(ojoCerrado);
            return false;
        } else {
            oculto.setVisible(false);
            visible.setText(oculto.getText());
            visible.setVisible(true);
            Image ojoAbierto = new Image(Objects.requireNonNull(R.getImage("ojoAbierto.png")));
            imagen.setImage(ojoAbierto);
            return true;
        }
    }
}
