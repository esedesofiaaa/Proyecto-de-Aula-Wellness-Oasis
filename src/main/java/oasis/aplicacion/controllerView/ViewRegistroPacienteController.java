package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oasis.controller.PacientesController;
import oasis.model.domain.paciente.TipoDocumento;
import oasis.model.domain.paciente.Paciente;

public class ViewRegistroPacienteController {

    @FXML
    private ComboBox<TipoDocumento> tipoDocumentoComboBox;

    @FXML
    private TextField idNumeroDeDocumento;

    @FXML
    private TextField idNombre;

    @FXML
    private TextField idApellido;

    @FXML
    private TextField idEdad;

    @FXML
    private TextField idTelefono;

    @FXML
    private TextField idCorreo;

    @FXML
    private Label mensajeLabel; // Nuevo atributo para el Label

    private final PacientesController pacientesController;

    public ViewRegistroPacienteController() {
        this.pacientesController = new PacientesController();
    }

    @FXML
    private void initialize() {
        // Agregar los tipos de documento al ComboBox
        tipoDocumentoComboBox.getItems().addAll(
                TipoDocumento.CC,
                TipoDocumento.TI,
                TipoDocumento.CE,
                TipoDocumento.RC,
                TipoDocumento.PA
        );
    }

    public void guardar() {
        // Obtener los valores de los campos de la vista
        TipoDocumento tipoDocumento = tipoDocumentoComboBox.getValue();
        String numeroDocumento = idNumeroDeDocumento.getText();
        String nombre = idNombre.getText();
        String apellido = idApellido.getText();
        String edad = idEdad.getText();
        String telefono = idTelefono.getText();
        String correo = idCorreo.getText();

        // Verificar si los campos están vacíos
        if (numeroDocumento.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || edad.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            mensajeLabel.setText("Por favor completa todos los campos");
            mensajeLabel.setTextFill(javafx.scene.paint.Color.RED);
        } else {
            // Crear un nuevo objeto Paciente
            Paciente nuevoPaciente = new Paciente(tipoDocumento, numeroDocumento, nombre, apellido, edad, telefono, correo);

            // Agregar el nuevo paciente utilizando el controlador de pacientes
            pacientesController.agregarPaciente(nuevoPaciente);

            // Limpiar los campos después de agregar el paciente (opcional)
            limpiarCampos();

            // Mostrar mensaje de éxito
            mensajeLabel.setText("Paciente registrado con éxito");
            mensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
        }
    }

    private void limpiarCampos() {
        tipoDocumentoComboBox.getSelectionModel().clearSelection();
        idNumeroDeDocumento.clear();
        idNombre.clear();
        idApellido.clear();
        idEdad.clear();
        idTelefono.clear();
        idCorreo.clear();
    }
}
