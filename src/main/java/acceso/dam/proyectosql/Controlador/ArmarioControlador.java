package acceso.dam.proyectosql.Controlador;

import acceso.dam.proyectosql.DAO.conocimientoDAO;
import acceso.dam.proyectosql.domain.Conocimiento;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static acceso.dam.proyectosql.DAO.conocimientoDAO.addConocimiento;
import static acceso.dam.proyectosql.DAO.conocimientoDAO.cargarConocimiento;

public class ArmarioControlador {
    @FXML
    public ListView<Conocimiento> lvConocimientos;
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
        cargarDatos();
        cbEstado.setItems(FXCollections.observableArrayList("Aprendiendo...", "Principiante", "Dominado"));
    }

    @FXML
    public void agregarConocimiento() throws SQLException, IOException, ClassNotFoundException {
        String nombre = nombreTextField.getText();
        String descripcion = descripcionTextField.getText();
        int estado = getEstado();
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

    private int getEstado() {
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
        lvConocimientos.getItems().clear();
        try {
            List<Conocimiento> conocimientos = cargarConocimiento(idUsuario);
            lvConocimientos.setItems(FXCollections.observableList(conocimientos));
        } catch (Exception e) {
            System.out.println("Error cargando los datos de la aplicación: " + e.getMessage());
        }
    }

    public void setUsuario(String nombreUsuario, int idUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
    }
}
