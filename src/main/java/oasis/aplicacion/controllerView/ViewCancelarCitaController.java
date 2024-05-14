package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import oasis.controller.CitasController;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.cita.Cita;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewCancelarCitaController {

    @FXML
    private TextField idDocumento;

    @FXML
    private ComboBox<String> idCancelarCitaComboBox;

    @FXML
    private Label idMensajeLabel;

    private final CitasController citasController;

    private static final Logger logger = Logger.getLogger(ViewLoginController.class.getName());

    public ViewCancelarCitaController() {
        this.citasController = new CitasController();
    }

    @FXML
    private void initialize() {
        // Limpiar los elementos actuales del ComboBox
        idCancelarCitaComboBox.getItems().clear();

        // Agregar un listener al TextField para detectar cambios en su contenido
        idDocumento.textProperty().addListener((observable, oldValue, newValue) -> {
            actualizarComboBox(newValue);
        });

        String documentoPaciente = idDocumento.getText();


    }

    @FXML
    private void cancelarCita() {
        String documentoPaciente = idDocumento.getText();
        String idCita = idCancelarCitaComboBox.getValue().split(" - ")[0];

        //String idCita = idCancelarCitaComboBox.getValue();
        citasController.eliminarCita(idCita);
        idMensajeLabel.setText("Cita Cancelada.");
        idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
    }

    private void actualizarComboBox(String documentoPaciente) {
        // Limpiar el ComboBox
        idCancelarCitaComboBox.getItems().clear();

        // Verificar si el documento del paciente no está vacío
        if (!documentoPaciente.isEmpty()) {
            // Obtener la lista de exámenes del paciente usando el controlador correspondiente
            DoubleLinkedList<Cita> listaCitas = citasController.citasDelPaciente(documentoPaciente);
            logger.log(Level.INFO, "Lista Examenes: {0}", listaCitas.toString());

            for (int i = 0; i < listaCitas.tamano(); i++) {
                Cita citaTemp = listaCitas.buscarPorIndiceIterar(i);
                logger.log(Level.INFO, "registro encontrado: {0}", citaTemp.toString());

                idCancelarCitaComboBox.getItems().add(citaTemp.getIdCita() + " - " + citaTemp.getMotivoCita()+ " - " + citaTemp.getEspecialidad());
            }

            // Verificar si se encontraron exámenes para el paciente
            if (idCancelarCitaComboBox.getItems().isEmpty()) {
                idMensajeLabel.setText("No se encontraron citas para el paciente.");
                idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);

            } else {
                idMensajeLabel.setText("Selecciona una cita.");
                idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);

            }
        } else {
            idMensajeLabel.setText("Ingrese el documento del paciente.");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);

        }
    }
}
