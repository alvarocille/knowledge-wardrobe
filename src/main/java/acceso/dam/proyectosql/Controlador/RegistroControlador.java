package acceso.dam.proyectosql.Controlador;

import acceso.dam.proyectosql.DAO.usuarioDAO;
import acceso.dam.proyectosql.domain.Usuario;
import acceso.dam.proyectosql.util.R;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import static acceso.dam.proyectosql.util.Utils.cambiarVisibilidad;

/**
 * La clase {@code RegistroControlador} gestiona la lógica de la interfaz de usuario
 * en la ventana de registro de nuevos usuarios.
 */
public class RegistroControlador {
    private final InicioControlador controladorInicio;
    @FXML
    private TextField tfUser, tfEmail, tfPasswordVisible, tfPasswordVisible2;
    @FXML
    private PasswordField tfPassword, tfConfirmPassword;
    @FXML
    private ImageView eyeImageView, eyeImageView2;
    @FXML
    private Button registroBtn, passwordBtn, password2Btn;
    private Image icono;
    private boolean passwordVisible = false;
    private boolean passwordVisible2 = false;

    /**
     * Constructor de la clase {@code RegistroControlador}.
     *
     * @param controladorInicio El controlador de la ventana de inicio de sesión.
     * @param icono             El icono de la aplicación que se mostrará en la ventana.
     */
    public RegistroControlador(InicioControlador controladorInicio, Image icono) {
        this.controladorInicio = controladorInicio;
        this.icono = icono;
    }

    /**
     * Inicializa el controlador, configurando las imágenes de los ojos y la visibilidad
     * de los campos de contraseña.
     */
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

    /**
     * Alterna la visibilidad de la contraseña en el primer campo de entrada.
     */
    @FXML
    public void verPassword() {
        passwordVisible = cambiarVisibilidad(tfPasswordVisible, tfPassword, eyeImageView, passwordVisible);
    }

    /**
     * Alterna la visibilidad de la contraseña en el segundo campo de entrada.
     */
    @FXML
    public void verPassword2() {
        passwordVisible2 = cambiarVisibilidad(tfPasswordVisible2, tfConfirmPassword, eyeImageView2, passwordVisible2);
    }

    /**
     * Maneja el registro de un nuevo usuario. Valida los campos de entrada y, si son correctos,
     * inserta el nuevo usuario en la base de datos.
     *
     * @throws SQLException si hay un error al realizar la inserción en la base de datos.
     */
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

    /**
     * Limpia los campos de entrada de usuario, email y contraseñas.
     */
    private void limpiarCampos() {
        tfUser.clear();
        tfEmail.clear();
        tfPassword.clear();
        tfConfirmPassword.clear();
        tfPasswordVisible.clear();
        tfPasswordVisible2.clear();
    }
}