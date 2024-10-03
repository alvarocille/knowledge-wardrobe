package acceso.dam.proyectosql.Controlador;

import acceso.dam.proyectosql.DAO.usuarioDAO;
import acceso.dam.proyectosql.InicioControlador;
import acceso.dam.proyectosql.domain.Usuario;
import acceso.dam.proyectosql.util.AlertUtils;
import acceso.dam.proyectosql.util.R;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static acceso.dam.proyectosql.DAO.usuarioDAO.insertarUsuario;
import static acceso.dam.proyectosql.util.AlertUtils.mostrarConfirmacion;
import static acceso.dam.proyectosql.util.AlertUtils.mostrarError;
import static acceso.dam.proyectosql.util.BD.conectar;
import static acceso.dam.proyectosql.util.Utils.cambiarVisibilidad;

public class RegistroControlador {
    @FXML
    private TextField tfUser, tfEmail, tfPasswordVisible, tfPasswordVisible2;
    @FXML
    private PasswordField tfPassword, tfConfirmPassword;
    @FXML
    private ImageView eyeImageView, eyeImageView2;
    @FXML
    private Button registroBtn, passwordBtn, password2Btn;

    private final InicioControlador controladorInicio;
    private boolean passwordVisible = false;
    private boolean passwordVisible2 = false;

    public RegistroControlador(InicioControlador controladorInicio) {
        this.controladorInicio = controladorInicio;

        try {
            conectar();
        } catch (SQLException sqle) {
            mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            mostrarError("Error al cargar la configuración");
        }
        System.out.println(System.getProperty("user.home"));
    }

    @FXML
    public void initialize() {
        Image ojoCerrado = new Image(Objects.requireNonNull(R.getImage("ojoCerrado.png")));
        eyeImageView.setImage(ojoCerrado);
        eyeImageView2.setImage(ojoCerrado);
        tfPassword.setVisible(true);
        tfPasswordVisible.setVisible(false);
        tfConfirmPassword.setVisible(true);
        tfPasswordVisible2.setVisible(false);
    }

    @FXML
    public void verPassword() {
        passwordVisible = cambiarVisibilidad(tfPasswordVisible, tfPassword, eyeImageView, passwordVisible);
    }

    @FXML
    public void verPassword2() {
        passwordVisible2 = cambiarVisibilidad(tfPasswordVisible2, tfConfirmPassword, eyeImageView2, passwordVisible2);
    }

    @FXML
    public void registrarUsuario() throws SQLException {
        boolean registroCorrecto = true;
        if (tfPasswordVisible.isVisible()) {
            tfPassword.setText(tfPasswordVisible.getText());
        }
        if (tfPasswordVisible2.isVisible()) {
            tfConfirmPassword.setText(tfPasswordVisible2.getText());
        }
        if (tfUser.getText().isEmpty() || tfEmail.getText().isEmpty() || tfPassword.getText().isEmpty()) {
            registroCorrecto = false;
            mostrarError("Campos vacíos.");
        } else if (!tfConfirmPassword.getText().equals(tfPassword.getText())) {
            registroCorrecto = false;
            mostrarError("Contraseñas no coinciden.");
        }
        if (registroCorrecto) {
            String nombreUsuario = tfUser.getText();
            Usuario usuario = new Usuario(nombreUsuario, tfPassword.getText(), tfEmail.getText());
            insertarUsuario(usuario);
            controladorInicio.autocompletarUsuario(nombreUsuario);
            limpiarCampos();
            Stage currentStage = (Stage) registroBtn.getScene().getWindow();
            currentStage.close();
            mostrarConfirmacion(String.format("Usuario %s registrado", nombreUsuario));
        }
    }

    private void limpiarCampos() {
        tfUser.clear();
        tfEmail.clear();
        tfPassword.clear();
        tfConfirmPassword.clear();
        tfPasswordVisible.clear();
        tfPasswordVisible2.clear();
    }

}
