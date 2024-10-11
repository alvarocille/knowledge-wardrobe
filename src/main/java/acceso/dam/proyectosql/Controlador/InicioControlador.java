package acceso.dam.proyectosql.Controlador;

import acceso.dam.proyectosql.DAO.usuarioDAO;
import acceso.dam.proyectosql.domain.Usuario;
import acceso.dam.proyectosql.util.AlertUtils;
import acceso.dam.proyectosql.util.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static acceso.dam.proyectosql.DAO.usuarioDAO.buscarUsuario;
import static acceso.dam.proyectosql.util.Utils.cambiarVisibilidad;

/**
 * La clase {@code InicioControlador} gestiona la lógica de la interfaz de usuario
 * en la ventana de inicio de sesión de la aplicación.
 */
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

    /**
     * Constructor de la clase {@code InicioControlador}.
     *
     * @param icono El icono de la aplicación que se mostrará en la ventana.
     */
    public InicioControlador(Image icono) {
        this.icono = icono;
    }

    /**
     * Inicializa el controlador, configurando la imagen del ojo y la visibilidad de los campos de contraseña.
     */
    @FXML
    public void initialize() {
        Image ojoCerrado = new Image(Objects.requireNonNull(R.getImage("ojoCerrado.png")));
        eyeImageView.setImage(ojoCerrado);
        tfPassword.setVisible(true);
        tfPasswordVisible.setVisible(false);
    }

    /**
     * Maneja el evento de inicio de sesión. Intenta autenticar al usuario y redirige a la interfaz correspondiente
     * si el inicio de sesión es exitoso.
     *
     * @param event El evento de acción.
     * @throws SQLException si hay un error al realizar la consulta de usuario.
     */
    @FXML
    protected void iniciarSesion(ActionEvent event) throws SQLException {
        if (tfPasswordVisible.isVisible()) {
            tfPassword.setText(tfPasswordVisible.getText());
        }
        Usuario usuario = buscarUsuario(tfUser.getText(), tfPassword.getText());
        notFound.setVisible(false);
        limpiarCampos();
        if (usuario != null) {
            try {
                ArmarioControlador controller = new ArmarioControlador();
                controller.setUsuario(usuario.getNombre(), usuario.getIdUsuario());

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(R.getUI("armario.fxml"));
                loader.setController(controller);
                VBox vbox = loader.load();

                // Obtener las dimensiones de la pantalla
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

                // Ajustar el tamaño del Stage para ocupar toda la pantalla sin fullscreen
                Stage currentStage = (Stage) loginBtn.getScene().getWindow();
                currentStage.setX(screenBounds.getMinX());
                currentStage.setY(screenBounds.getMinY());
                currentStage.setWidth(screenBounds.getWidth());
                currentStage.setHeight(screenBounds.getHeight());
                Scene scene = new Scene(vbox);
                scene.getStylesheets().add(R.getEstilos("estilos.css").toExternalForm());
                currentStage.setScene(scene);
                currentStage.show();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            notFound.setVisible(true);
        }
    }

    /**
     * Abre la ventana de registro para permitir a un nuevo usuario crear una cuenta.
     *
     * @param event El evento de acción.
     */
    @FXML
    protected void abrirRegistro(ActionEvent event) {
        try {
            RegistroControlador controladorRegistro = new RegistroControlador(this, icono);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("registro.fxml"));
            loader.setController(controladorRegistro);
            VBox vbox = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(vbox);
            scene.getStylesheets().add(R.getEstilos("estilos.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Limpia los campos de entrada de usuario y contraseña.
     */
    @FXML
    public void limpiarCampos() {
        tfUser.clear();
        tfPassword.clear();
        tfPasswordVisible.clear();
    }

    /**
     * Alterna la visibilidad de la contraseña en el campo de entrada correspondiente.
     */
    @FXML
    protected void verPassword() {
        passwordVisible = cambiarVisibilidad(tfPasswordVisible, tfPassword, eyeImageView, passwordVisible);
    }

    /**
     * Completa automáticamente el campo de nombre de usuario con el nombre proporcionado.
     *
     * @param nombreUsuario El nombre de usuario a autocompletar.
     */
    public void autocompletarUsuario(String nombreUsuario) {
        tfUser.setText(nombreUsuario);
    }
}
