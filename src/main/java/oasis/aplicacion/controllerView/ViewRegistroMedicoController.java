package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oasis.controller.MedicosController;
import oasis.model.domain.Especialidad;
import oasis.model.domain.medico.Medico;

public class ViewRegistroMedicoController {
    @FXML
    private ComboBox<Especialidad> tipoEspecialidadComboBox;
    @FXML
    private TextField idNombreMedico;
    @FXML
    private TextField idNumeroDeDocumentoMedico;
    @FXML
    private Label idMensajeLabel; // Nuevo campo para el Label

    private final MedicosController medicosController;

    public ViewRegistroMedicoController() {
        this.medicosController = new MedicosController();
    }

    @FXML
    private void initialize() {
        tipoEspecialidadComboBox.getItems().addAll(
                Especialidad.CARDIOLOGIA,
                Especialidad.DERMATOLOGIA,
                Especialidad.NUTRICION,
                Especialidad.MEDICINA_GENERAL,
                Especialidad.ODONTOLOGIA,
                Especialidad.PEDIATRIA,
                Especialidad.PSIQUIATRIA
        );
    }

    public void guardar() {
        Especialidad especialidad = tipoEspecialidadComboBox.getValue();
        String nombre = idNombreMedico.getText();
        String idMedico = idNumeroDeDocumentoMedico.getText();

        if (nombre.isEmpty() || idMedico.isEmpty() || especialidad == null) {
            idMensajeLabel.setText("Por favor completa todos los campos");
        } else {
            Medico nuevoMedico = new Medico(nombre, especialidad, idMedico);
            medicosController.agregarMedico(nuevoMedico);
            limpiarCampos();
            idMensajeLabel.setText("Médico registrado con éxito!");
        }
    }

    private void limpiarCampos() {
        tipoEspecialidadComboBox.getSelectionModel().clearSelection();
        idNombreMedico.clear();
        idNumeroDeDocumentoMedico.clear();
    }
}
