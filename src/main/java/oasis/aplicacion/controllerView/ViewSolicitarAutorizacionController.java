package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oasis.controller.AutorizacionController;
import oasis.controller.RegistroExamenController;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.estructurasDatos.listas.StackList;
import oasis.model.domain.Especialidad;
import oasis.model.domain.registroExamen.RegistroExamen;
import oasis.model.repository.AutorizacionRepository;
import oasis.model.repository.RegistroExamenRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewSolicitarAutorizacionController {


    @FXML
    private ComboBox<String> idComboBoxExamenesAutorizar;
    @FXML
    private Label idMensajeLabel; // Nuevo campo para el Label
    @FXML
    private TextField   idDocumentoPaciente;

    private final RegistroExamenController registroExamenController ;

    private final AutorizacionController  autorizacionController;

    private final StackList<RegistroExamen> pilaAutorizaciones;


    private static final Logger logger = Logger.getLogger(ViewLoginController.class.getName());

    public ViewSolicitarAutorizacionController() {


        this.registroExamenController = new RegistroExamenController();
        this.autorizacionController = new AutorizacionController();
        this.pilaAutorizaciones = new StackList<>();
    }

    @FXML
    private void initialize() {
        // Limpiar los elementos actuales del ComboBox
        idComboBoxExamenesAutorizar.getItems().clear();

        // Agregar un listener al TextField para detectar cambios en su contenido
        idDocumentoPaciente.textProperty().addListener((observable, oldValue, newValue) -> {
            actualizarComboBox(newValue);
        });

        // Obtener el documento del paciente del TextField
        String documentoPaciente = idDocumentoPaciente.getText();

        // Verificar si el documento del paciente no está vacío
        if (documentoPaciente == null) {
            // Obtener la lista de exámenes del paciente usando el controlador correspondiente
            DoubleLinkedList<RegistroExamen> listaExamenes = registroExamenController.buscarPorIdPaciente(documentoPaciente);
            logger.log(Level.INFO, "Lista Examenes: {0}", listaExamenes.toString());

            for (int i = 0; i < listaExamenes.tamano(); i++) {
                RegistroExamen registroTemp = listaExamenes.buscarPorIndiceIterar(i);
                logger.log(Level.INFO, "registro encontrado: {0}", registroTemp.toString());

                idComboBoxExamenesAutorizar.getItems().add(registroTemp.getRadicadoExamen() + " - " + registroTemp.getMotivoCitaExamen().getTipoExamen());
            }

            // Verificar si se encontraron exámenes para el paciente
            if (idComboBoxExamenesAutorizar.getItems().isEmpty()) {
                idMensajeLabel.setText("No se encontraron exámenes para el paciente.");
            } else {
                idMensajeLabel.setText("Exámenes encontrados.");
            }
        } else {
            idMensajeLabel.setText("Ingrese el documento del paciente.");
        }
    }



    @FXML
    private void solicitarAutorizacion() {
        // Obtener el documento del paciente del TextField
        String documentoPaciente = idDocumentoPaciente.getText();
        String radicadoExamen = idComboBoxExamenesAutorizar.getValue().split(" - ")[0];
        // Verificar si el documento del paciente no está vacío
        if (!autorizacionController.controlParaNoRepetirExamenParaAutorizar(radicadoExamen)) {
            // Agregar el examen a la pila de autorizaciones
            autorizacionController.agregarPilaAutroizacion(radicadoExamen);
            idMensajeLabel.setText("Exámenes encontrados.");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);

        } else {
            idMensajeLabel.setText("El examen ya está en la lista de autorizaciones.");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);

        }

        autorizacionController.agregarPilaAutroizacion(radicadoExamen);
        idMensajeLabel.setText("Exámenes encontrados.");
        idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);

    }

    private void actualizarComboBox(String documentoPaciente) {
        // Limpiar el ComboBox
        idComboBoxExamenesAutorizar.getItems().clear();

        // Verificar si el documento del paciente no está vacío
        if (!documentoPaciente.isEmpty()) {
            // Obtener la lista de exámenes del paciente usando el controlador correspondiente
            DoubleLinkedList<RegistroExamen> listaExamenes = registroExamenController.buscarPorIdPaciente(documentoPaciente);
            logger.log(Level.INFO, "Lista Examenes: {0}", listaExamenes.toString());

            for (int i = 0; i < listaExamenes.tamano(); i++) {
                RegistroExamen registroTemp = listaExamenes.buscarPorIndiceIterar(i);
                logger.log(Level.INFO, "registro encontrado: {0}", registroTemp.toString());

                idComboBoxExamenesAutorizar.getItems().add(registroTemp.getRadicadoExamen() + " - " + registroTemp.getMotivoCitaExamen().getTipoExamen());
            }

            // Verificar si se encontraron exámenes para el paciente
            if (idComboBoxExamenesAutorizar.getItems().isEmpty()) {
                idMensajeLabel.setText("No se encontraron exámenes para el paciente.");
                idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);

            } else {
                idMensajeLabel.setText("Exámenes encontrados.");
                idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);

            }
        } else {
            idMensajeLabel.setText("Ingrese el documento del paciente.");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);

        }
    }



}