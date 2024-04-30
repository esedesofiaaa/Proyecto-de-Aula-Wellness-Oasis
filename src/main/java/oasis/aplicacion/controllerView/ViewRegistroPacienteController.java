package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import oasis.controller.PacientesController;
import oasis.model.domain.paciente.TipoDocumento;
import oasis.model.domain.paciente.Paciente;

/*Los nombres de los atributos que represetan los campos de la vista que vamos a extraer
 * deben coincidirt con el FX: id*/
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
        /* Se debe declaran las variables que se quieren extraer con su tipo de variable y nombre y se igualan
         * a la get de los id de los campos de la vista, por eso estos nombres deben coincidir con los id creados
         * para cada campo*/
        TipoDocumento tipoDocumento = tipoDocumentoComboBox.getValue();
        String numeroDocumento = idNumeroDeDocumento.getText();
        String nombre = idNombre.getText();
        String apellido = idApellido.getText();
        String edad = idEdad.getText();
        String telefono = idTelefono.getText();
        String correo = idCorreo.getText();

        // Crear un nuevo objeto Paciente
        Paciente nuevoPaciente = new Paciente(tipoDocumento, numeroDocumento, nombre, apellido, edad, telefono, correo);

        // Agregar el nuevo paciente utilizando el controlador de pacientes
        pacientesController.agregarPaciente(nuevoPaciente);

        // Limpiar los campos después de agregar el paciente (opcional)
        limpiarCampos();

        System.out.println("Paciente guardado con éxito");
    }

    private void limpiarCampos() {
        tipoDocumentoComboBox.getSelectionModel().clearSelection();
        idNumeroDeDocumento.clear();
        idNombre.clear();
        idApellido.clear();
        idEdad.clear();
        idTelefono.clear();
        idCorreo.clear();
    }//limpiarCampos
}
