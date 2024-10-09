package acceso.dam.proyectosql.Controlador;

import acceso.dam.proyectosql.DAO.conocimientoDAO;
import acceso.dam.proyectosql.domain.Conocimiento;
import acceso.dam.proyectosql.util.R;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static acceso.dam.proyectosql.DAO.conocimientoDAO.addConocimiento;
import static acceso.dam.proyectosql.DAO.conocimientoDAO.cargarConocimiento;

public class ArmarioControlador {
    @FXML
    public TableView<Conocimiento> tvConocimientos;
    @FXML
    private TableColumn<Conocimiento, String> colNombre;
    @FXML
    private TableColumn<Conocimiento, String> colDescripcion;
    @FXML
    private TableColumn<Conocimiento, Image> colEstado;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField descripcionTextField;
    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private Label labelUsuario, errorMsg;

    public int idUsuario;
    public String nombreUsuario;

    @FXML
    public void initialize() throws SQLException, IOException, ClassNotFoundException {
        labelUsuario.setText("Conocimientos de " + nombreUsuario);
        configurarColumnas();
        cargarDatos();
        cbEstado.setItems(FXCollections.observableArrayList("Aprendiendo...", "Principiante", "Dominado"));
    }

    @FXML
    private void configurarColumnas() {
        colNombre.prefWidthProperty().bind(tvConocimientos.widthProperty().multiply(0.3));
        colDescripcion.prefWidthProperty().bind(tvConocimientos.widthProperty().multiply(0.6));
        colEstado.prefWidthProperty().bind(tvConocimientos.widthProperty().multiply(0.098));

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        Image aprendiendo = new Image(Objects.requireNonNull(R.getImage("aprendiendo.png")));
        Image principiante = new Image(Objects.requireNonNull(R.getImage("bebe.png")));
        Image dominado = new Image(Objects.requireNonNull(R.getImage("experto.png")));

        colEstado.setCellValueFactory(cellData -> {
            int estado = cellData.getValue().getEstado();
            Image estadoImagen;
            switch (estado) {
                case 1 -> estadoImagen = aprendiendo;
                case 2 -> estadoImagen = principiante;
                case 3 -> estadoImagen = dominado;
                default -> estadoImagen = null;
            }
            return new SimpleObjectProperty<>(estadoImagen);
        });

        colEstado.setCellFactory(column -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);
                if (empty || image == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);
                    setGraphic(imageView);
                }
            }
        });
    }

    @FXML
    public void agregarConocimiento() throws SQLException, IOException, ClassNotFoundException {
        String nombre = nombreTextField.getText();
        String descripcion = descripcionTextField.getText();
        int estado = getEstadoCB();
        if (nombre.isEmpty() || descripcion.isEmpty() || estado == 0) {
            errorMsg.setVisible(true);
        } else {
            Conocimiento conocimiento = new Conocimiento(nombre, descripcion, estado, idUsuario);
            nombreTextField.clear();
            descripcionTextField.clear();
            cbEstado.getSelectionModel().select(null);
            errorMsg.setVisible(false);
            addConocimiento(conocimiento);
            cargarDatos();
        }
    }

    private int getEstadoCB() {
        String estadoStr = null;
        if (cbEstado.getValue() != null) {
            estadoStr = cbEstado.getSelectionModel().getSelectedItem();
        }
        int estado = 0;
        switch (estadoStr) {
            case "Aprendiendo..." -> {
                estado = 1;
            }
            case "Principiante" -> {
                estado = 2;
            }
            case "Dominado" -> {
                estado = 3;
            }
            case null -> {}
            default -> throw new IllegalStateException("Unexpected value: " + estadoStr);
        }
        return estado;
    }

    public void cargarDatos() {
        tvConocimientos.getItems().clear();
        try {
            List<Conocimiento> conocimientos = cargarConocimiento(idUsuario);
            tvConocimientos.setItems(FXCollections.observableList(conocimientos));
        } catch (Exception e) {
            System.out.println("Error cargando los datos de la aplicación: " + e.getMessage());
        }
    }

    public void setUsuario(String nombreUsuario, int idUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
    }
}
