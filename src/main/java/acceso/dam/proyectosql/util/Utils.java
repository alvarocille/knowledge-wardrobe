package acceso.dam.proyectosql.util;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * La clase {@code Utils} contiene métodos de utilidad para la manipulación
 * de elementos de la interfaz de usuario en la aplicación.
 */
public class Utils {

    /**
     * Cambia la visibilidad del texto de un campo contraseña alternando entre un PasswordField y un TextField.
     *
     * @param visible     el {@code TextField} que se hace visible o no.
     * @param oculto      el {@code PasswordField} que se hace visible o no.
     * @param imagen      el {@code ImageView} que muestra el icono del botón de visibilidad.
     * @param visibilidad el estado actual de visibilidad; si es {@code true}, se oculta el campo de texto y se muestra
     *                    el de contraseña, y viceversa.
     * @return {@code true} si el campo de contraseña es visible después de cambiar la visibilidad, o {@code false} en caso contrario.
     */
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