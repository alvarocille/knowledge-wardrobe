package acceso.dam.proyectosql;

import acceso.dam.proyectosql.Controlador.ArmarioControlador;
import acceso.dam.proyectosql.Controlador.RegistroControlador;
import acceso.dam.proyectosql.domain.Usuario;
import acceso.dam.proyectosql.util.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import static acceso.dam.proyectosql.DAO.usuarioDAO.buscarUsuario;
import static acceso.dam.proyectosql.util.BD.conectar;
import static acceso.dam.proyectosql.util.Utils.cambiarVisibilidad;

public class InicioControlador {
    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfPasswordVisible;
    @FXML
    private ImageView eyeImageView;
    @FXML
    private Button loginBtn, cleanBtn, registroBtn, passwordBtn;
    @FXML
    private Label notFound;

    private Image icono;
    private boolean passwordVisible = false;

    public InicioControlador(Image icono) {
        this.icono = icono;
    }

    @FXML
    public void initialize() {
        Image ojoCerrado = new Image(Objects.requireNonNull(R.getImage("ojoCerrado.png")));
        eyeImageView.setImage(ojoCerrado);
        tfPassword.setVisible(true);
        tfPasswordVisible.setVisible(false);
    }

    @FXML
    protected void iniciarSesion(ActionEvent event) throws SQLException {
        if (tfPasswordVisible.isVisible()) {
            tfPassword.setText(tfPasswordVisible.getText());
        }
        if (!tfPassword.getText().isEmpty() && !tfUser.getText().isEmpty()) {
            Usuario usuario = buscarUsuario(tfUser.getText(), tfPassword.getText());
            notFound.setVisible(false);
            limpiarCampos();
            if (usuario != null) {
                try {
                    ArmarioControlador controladorArmario = new ArmarioControlador();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(R.getUI("armario.fxml"));
                    loader.setController(controladorArmario);
                    VBox vbox = loader.load();

                    Stage currentStage = (Stage) loginBtn.getScene().getWindow();
                    Scene scene = new Scene(vbox);
                    controladorArmario.setUsuario(usuario);
                    currentStage.setScene(scene);
                    currentStage.show();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                notFound.setVisible(true);
            }
        }

    }

    @FXML
    protected void abrirRegistro(ActionEvent event) {
        try {
            RegistroControlador controladorRegistro = new RegistroControlador(this);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("registro.fxml"));
            loader.setController(controladorRegistro);
            VBox vbox = loader.load();

            Stage stage = new Stage();
            stage.getIcons().add(icono);
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void limpiarCampos() {
        tfUser.clear();
        tfPassword.clear();
        tfPasswordVisible.clear();
    }

    @FXML
    protected void verPassword() {
        passwordVisible = cambiarVisibilidad(tfPasswordVisible, tfPassword, eyeImageView, passwordVisible);
    }

    public void autocompletarUsuario(String nombreUsuario) {
        tfUser.setText(nombreUsuario);
    }


}