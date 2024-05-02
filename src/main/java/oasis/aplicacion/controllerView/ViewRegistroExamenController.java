package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oasis.controller.RegistroExamenController;
import oasis.model.domain.motivoCita.Examen;

public class ViewRegistroExamenController {

    @FXML
    private TextField idDocumentoPaciente;

    @FXML
    private ComboBox<String> idComboBoxExamenes;

    private final RegistroExamenController registroExamenController;

    @FXML
    private Label idMensajeLabel;

    public ViewRegistroExamenController() {
        this.registroExamenController = new RegistroExamenController();
    }

    @FXML
    private void initialize() {
        // Agregar los tipos de examenes al ComboBox
        idComboBoxExamenes.getItems().addAll(
                Examen.EXAMEN_FISICO_INFANTIL.getTipoExamen(),
                Examen.ECOGRAFIA_ABDOMINAL.getTipoExamen()
              /*  Examen.ELECTROCARDIOGRAMA.getTipoExamen() + " - " + Examen.ELECTROCARDIOGRAMA.getBreveDescripcion(),
                Examen.ECOCARDIOGRAFIA.getTipoExamen() + " - " + Examen.ECOCARDIOGRAFIA.getBreveDescripcion(),
                Examen.DERMATOSCOPIA.getTipoExamen() + " - " + Examen.DERMATOSCOPIA.getBreveDescripcion(),
                Examen.BIOPSIA_DE_PIEL.getTipoExamen() + " - " + Examen.BIOPSIA_DE_PIEL.getBreveDescripcion()
                Examen.ENTREVISTA_PSIQUIATRICA.getTipoExamen() + " - " + Examen.ENTREVISTA_PSIQUIATRICA.getBreveDescripcion(),
                Examen.EVALUACION_NEUROPSICOLOGICA.getTipoExamen() + " - " + Examen.EVALUACION_NEUROPSICOLOGICA.getBreveDescripcion(),
                Examen.ANALISIS_DE_COMPOSICION_CORPORAL.getTipoExamen() + " - " + Examen.ANALISIS_DE_COMPOSICION_CORPORAL.getBreveDescripcion(),
                Examen.EVALUACION_DIETETICA.getTipoExamen() + " - " + Examen.EVALUACION_DIETETICA.getBreveDescripcion(),
                Examen.LIMPIEZA_DENTAL.getTipoExamen() + " - " + Examen.LIMPIEZA_DENTAL.getBreveDescripcion(),
                Examen.RADIOGRAFIAS_DENTALES.getTipoExamen() + " - " + Examen.RADIOGRAFIAS_DENTALES.getBreveDescripcion(),
                Examen.ANALISIS_DE_SANGRE.getTipoExamen() + " - " + Examen.ANALISIS_DE_SANGRE.getBreveDescripcion(),
                Examen.RADIOGRAFIA_DE_TORAX.getTipoExamen() + " - " + Examen.RADIOGRAFIA_DE_TORAX.getBreveDescripcion()*/
        );

        Examen examenSeleccionado = registroExamenController.buscarExamenPorTipoExamen(idComboBoxExamenes.getValue());

        if (examenSeleccionado == null && idComboBoxExamenes.getValue() == null) {
            System.out.println("Por favor completa todos los campos.");

        }

    }

    @FXML
    private void guardarRegistroExamen() {
        String idDocumento = idDocumentoPaciente.getText();
        Examen examen = registroExamenController.buscarExamenPorTipoExamen(idComboBoxExamenes.getValue());
        if (idDocumento.isEmpty() || examen == null) {
            System.out.println("Por favor complete todos los campos.");
            idMensajeLabel.setText("Por favor completa todos los campos");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);
        } else {
            String idCita = registroExamenController.agregarExamen(examen, idDocumento);
            if (idCita != null) {
                idMensajeLabel.setText("Cita agendada con éxito. ID de la cita: " + idCita);
                idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
            } else {
                idMensajeLabel.setText("Error al registrar el examen. Verifica los datos.");
                idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);
            }
        }
        limpiarCampos();
    }


    private void limpiarCampos() {
        idDocumentoPaciente.clear();
        idComboBoxExamenes.getSelectionModel().clearSelection();
    }

}

