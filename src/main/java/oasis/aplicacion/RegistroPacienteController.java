package oasis.aplicacion;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import oasis.controller.PacientesController;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroPacienteController implements Initializable {
    @FXML
    private TextField NombreTextField;
    @FXML
    private TextField apellidoTextField;
    @FXML
    private ChoiceBox<String> myChoiceBox;
    private String[] tipoDocumento = {"CC", "TI", "CE", "RC", "PA"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().addAll(tipoDocumento);
    }



    @FXML
    private TextField documentoTextField;
    @FXML
    private TextField edadTextField;
    @FXML
    private TextField telefonoTextField;
    @FXML
    private TextField correoTextField;
    @FXML
    private PasswordField contrasenaField;
    @FXML
    private PasswordField confirmarContraseñaField;

    @FXML
    private Button registrarButton;

    private final PacientesController pacientesController;

    public RegistroPacienteController() {
        this.pacientesController = new PacientesController();
    }

    @FXML
    private void initialize() {
        // Puedes inicializar algunos componentes aquí si es necesario
    }

    @FXML
    private void handleRegistrarButtonAction() {
        // Obtener los datos del formulario
        String id = "1"; // Esto probablemente debería generarse dinámicamente
        String nombre = NombreTextField.getText();
        String apellido = apellidoTextField.getText();
        String edad = edadTextField.getText();
        String telefono = telefonoTextField.getText();
        String correo = correoTextField.getText();
        String contrasena = contrasenaField.getText();

        // Crear un nuevo paciente
       /* Paciente nuevoPaciente = new Paciente(TipoDocumento, id, nombre, apellido, edad, telefono, correo);

        // Agregar el paciente a una lista en el controlador
        pacientesController.agregarPaciente(nuevoPaciente);*/

        // Limpiar los campos después de registrar
        limpiarCampos();
    }

    private void limpiarCampos() {
        NombreTextField.clear();
        apellidoTextField.clear();
        edadTextField.clear();
        telefonoTextField.clear();
        correoTextField.clear();
        contrasenaField.clear();
        confirmarContraseñaField.clear();
    }


}
