package acceso.dam.proyectosql.Controlador;

import acceso.dam.proyectosql.DAO.conocimientoDAO;
import acceso.dam.proyectosql.domain.Conocimiento;
import acceso.dam.proyectosql.domain.Usuario;
import acceso.dam.proyectosql.util.R;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;

import static java.util.Collections.addAll;

public class ArmarioControlador {
    @FXML
    private Label txtUsuario;
    @FXML
    private ListView<String> conocimientoListView;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private ImageView imagenView;

    private File imagenSeleccionada;
    private int usuarioActualId;
    ObservableList<Conocimiento> conocimientos = FXCollections.observableArrayList();

    public void initialize() {
        cbEstado.setItems(FXCollections.observableArrayList("Activo", "Inactivo", "Pendiente"));

        // Cargar imagen por defecto
        Image imagenDefecto = new Image(R.getImage("imagen_placeholder.webp")); // Asegúrate de tener esta imagen en tu proyecto
        imagenView.setImage(imagenDefecto);

        // Cargar conocimientos al iniciar
        cargarConocimientos();
    }

    @FXML
    private void seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        imagenSeleccionada = fileChooser.showOpenDialog(null);

        if (imagenSeleccionada != null) {
            Image imagen = new Image(imagenSeleccionada.toURI().toString());
            imagenView.setImage(imagen);
        } else {
            // Si no se selecciona ninguna imagen, volver a mostrar la imagen por defecto
            imagenView.setImage(new Image(R.getImage("imagen_placeholder.webp"))); // Usa la ruta correcta
        }
    }

    @FXML
    private void agregarConocimiento() {
        String nombre = tfNombre.getText();
        String descripcion = taDescripcion.getText();
        String estadoTexto = cbEstado.getValue();
        int estado = convertirEstado(estadoTexto);

        if (!nombre.isEmpty() && !descripcion.isEmpty() && imagenSeleccionada != null) {
            conocimientoDAO dao = new conocimientoDAO();

            // Crear el objeto Conocimiento
            Conocimiento nuevoConocimiento = new Conocimiento(nombre, descripcion, estado, new Image(imagenSeleccionada.toURI().toString()), usuarioActualId);

            try {
                dao.guardarConocimiento(nuevoConocimiento);
                cargarConocimientos(); // Recargar la lista de conocimientos

                // Limpiar los campos
                tfNombre.clear();
                taDescripcion.clear();
                cbEstado.setValue(null);
                imagenView.setImage(new Image(R.getImage("imagen_placeholder.webp"))); // Imagen por defecto

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Mensaje de error si falta información
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, complete todos los campos y seleccione una imagen.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private int convertirEstado(String estadoTexto) {
        switch (estadoTexto) {
            case "Activo":
                return 1;
            case "Inactivo":
                return 2;
            case "Pendiente":
                return 3;
            default:
                return 0;
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioActualId = usuario.getIdUsuario();
        txtUsuario.setText("Conocimientos de " + usuario.getNombre());
        cargarConocimientos();
    }

    public void cargarConocimientos() {
        conocimientos.clear();
        ArrayList<Conocimiento> listaConocimientos = obtenerConocimientos(usuarioActualId);
        conocimientoListView.setItems(FXCollections.observableList(conocimientos));
    }
}
