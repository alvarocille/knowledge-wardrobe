package acceso.dam.proyectosql;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InicioControlador {
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button loginBtn, cleanBtn, registroBtn;

    @FXML
    protected void iniciarSesion(ActionEvent event) {
        limpiarCampos();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("titulo");
        alert.setContentText("mensaje");
        alert.showAndWait();
    }

    @FXML
    protected void abrirRegistro(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("titulo");
        alert.setContentText("mensaje");
        alert.showAndWait();
    }

    public void limpiarCampos() {
        tfUser.clear();
        tfPassword.clear();
    }
}