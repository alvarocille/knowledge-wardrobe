package acceso.dam.proyectosql;

import acceso.dam.proyectosql.DAO.usuarioDAO;
import acceso.dam.proyectosql.util.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class InicioControlador {
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button loginBtn, cleanBtn, registroBtn;

    public InicioControlador() {
        try {
            usuarioDAO.conectar();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuración");
        }
        System.out.println(System.getProperty("user.home"));
    }

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