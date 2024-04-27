package oasis.controller;
/*
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import model.domain.paciente.Paciente;
import model.TipoDocumento;
import controller.PacientesController;
*/
public class PruebaController {
    /*
    @FXML
    private TextField txtTipoDeDocumento;
    @FXML
    private TextField txtNumeroDeDocumento;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private Button btnRegistrar;

    private PacientesController pacientesController = new PacientesController();

    @FXML
    public void registrarPaciente() {
        // Obtener los valores de los campos del formulario
        String tipoDocumento = txtTipoDeDocumento.getText();
        String numeroDocumento = txtNumeroDeDocumento.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String edad = txtEdad.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();

        // Crear un nuevo objeto Paciente
        Paciente nuevoPaciente = new Paciente(
                TipoDocumento.valueOf(tipoDocumento),
                numeroDocumento,
                nombre,
                apellido,
                edad,
                telefono,
                correo
        );

        // Agregar el nuevo paciente utilizando el controlador
        pacientesController.agregarPaciente(nuevoPaciente);

        // Limpiar los campos del formulario después de registrar al paciente
        limpiarCampos();

        // Puedes agregar aquí lógica adicional, como mostrar un mensaje de éxito
    }

    private void limpiarCampos() {
        txtTipoDeDocumento.clear();
        txtNumeroDeDocumento.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtEdad.clear();
        txtTelefono.clear();
        txtCorreo.clear();
    }*/
}