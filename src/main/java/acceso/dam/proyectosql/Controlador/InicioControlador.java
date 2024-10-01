package acceso.dam.proyectosql;

import acceso.dam.proyectosql.Controlador.ArmarioControlador;
import acceso.dam.proyectosql.DAO.usuarioDAO;
import acceso.dam.proyectosql.domain.Usuario;
import acceso.dam.proyectosql.util.AlertUtils;
import acceso.dam.proyectosql.util.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static acceso.dam.proyectosql.DAO.usuarioDAO.buscarUsuario;

public class InicioControlador {
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfPassword;
    @FXML
    private ImageView eyeImageView;
    private boolean passwordVisible = false;
    @FXML
    private Button loginBtn, cleanBtn, registroBtn, passwordBtn;
    @FXML
    private Label notFound;

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
    protected void iniciarSesion(ActionEvent event) throws SQLException {
        Usuario usuario = buscarUsuario(tfUser.getText(), tfPassword.getText());
        notFound.setVisible(false);
        limpiarCampos();
        if (usuario != null) {
            try {
                System.out.println("Usuario: " + usuario.getNombre());
                ArmarioControlador controller = new ArmarioControlador();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(R.getUI("armario.fxml"));
                loader.setController(controller);
                VBox vbox = loader.load();

                Stage stage = new Stage();
                Scene scene = new Scene(vbox);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            notFound.setVisible(true);
        }
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

    @FXML
    protected void verPassword() {
        if (passwordVisible) {
            // Mostrar contraseña
            tfPassword.setVisible(true); // Asegúrate de mostrar el PasswordField
            // Sincronizar texto del PasswordField (puedes omitir esto si usas otro método)
            tfPassword.setText(tfPassword.getText());

            // Cambiar la imagen a ojo cerrado usando R.getImage
            Image ojoCerrado = new Image(Objects.requireNonNull(R.getImage("ojoCerrado.png")));
            eyeImageView.setImage(ojoCerrado);

            passwordVisible = false; // Actualizar el estado
        } else {
            // Ocultar contraseña
            tfPassword.setVisible(false); // Puedes reemplazar esto por un TextField si deseas mostrar la contraseña

            // Cambiar la imagen a ojo abierto usando R.getImage
            Image ojoAbierto = new Image(Objects.requireNonNull(R.getImage("ojoAbierto.png")));
            eyeImageView.setImage(ojoAbierto);

            passwordVisible = true; // Actualizar el estado
        }
    }

}