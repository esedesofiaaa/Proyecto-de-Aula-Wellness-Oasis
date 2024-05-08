package oasis.aplicacion.controllerView;



import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import oasis.controller.CitasController;
import oasis.controller.MedicosController;
import oasis.controller.RegistroExamenController;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.Especialidad;
import oasis.model.domain.cita.Cita;
import oasis.model.domain.medico.Medico;
import javafx.scene.control.ComboBox;
import oasis.model.domain.registroExamen.RegistroExamen;
import oasis.model.repository.SalaEsperaRepository;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ViewRegistrarCitaExamenController {

    @FXML
    private TextField idDocumentoPaciente;

    @FXML
    private TextField idMedico;

    @FXML
    private TextField idPagado;

    @FXML
    private ComboBox<String>   idRadicadoComboBox;

    @FXML
    private ComboBox<String> idComboBoxMedicos;

    @FXML
    private Label idMensajeLabel;

    private static final Logger logger = Logger.getLogger(ViewLoginController.class.getName());


    private final CitasController citaController;
    private final MedicosController medicosController;
    private final RegistroExamenController registroExamenController ;
    private final SalaEsperaRepository salaEsperaRepository;

    public ViewRegistrarCitaExamenController() {
        this.medicosController = new MedicosController();
        this.citaController = new CitasController();
        this.registroExamenController = new RegistroExamenController();
        this.salaEsperaRepository = new SalaEsperaRepository();
    }

    @FXML
    private void initialize() {

        // Limpiar los elementos actuales del ComboBox
        idRadicadoComboBox.getItems().clear();

        // Agregar un listener al TextField para detectar cambios en su contenido
        idDocumentoPaciente.textProperty().addListener((observable, oldValue, newValue) -> {
            actualizarComboBox(newValue);
        });


        idRadicadoComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Limpiar los médicos anteriores
                idComboBoxMedicos.getItems().clear();
                // Obtener los nombres de los médicos por especialidad y agregarlos al ComboBox
                DoubleLinkedList<Medico> medicoEspecialidad = filtrarMedicosPorEspecialidad(Especialidad.valueOf(idRadicadoComboBox.getValue().split(" - ")[2]));
                for (int i = 0; i < medicoEspecialidad.tamano(); i++) {
                    Medico medico = medicoEspecialidad.buscarPorIndiceIterar(i);
                    idComboBoxMedicos.getItems().add(medico.getNombre());
                }

            }
        });

    }//Elementos del combo box

    @FXML
    private void guardarCitaExamen() {
        // Obtener los valores de los campos de la vista
        String documentoPaciente = idDocumentoPaciente.getText().trim();
        String radicado = idRadicadoComboBox.getValue().split(" - ")[0];
        String medico =medicosController.buscarIdPorNombre(idComboBoxMedicos.getValue()); // Obtener el valor del ComemboBox directamente
        boolean pagado = obtenerPagadoSeleccionado();

        if (documentoPaciente.isEmpty() || radicado.isEmpty() || medico.isEmpty()) {
            System.out.println("Por favor completa todos los campos.");
            idMensajeLabel.setText("Por favor completa todos los campos");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);
            return; // Salir del método si falta algún dato
        }
        // Verificar si ya existe una cita con el mismo radicado
        if (!citaController.existeCitaPorRadicado(idRadicadoComboBox.getValue().split(" - ")[0])) {
            Cita cita = citaController.agendarCitaExamen(documentoPaciente, radicado, medico, pagado); // Obtener el código de la cita

            if (pagado) {
                salaEsperaRepository.agregarCitaASalaDeEspera(cita);

                // Actualizar el Label dentro del hilo de la interfaz de usuario
                Platform.runLater(() -> {
                    idMensajeLabel.setText("Cita agendada con éxito (\nCódigo: " + cita.getIdCita() + ").\n Puede pasar a la sala de espera.");
                    idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
                });
            } else {
                // Actualizar el Label dentro del hilo de la interfaz de usuario
                Platform.runLater(() -> {
                    idMensajeLabel.setText("Cita agendada con éxito (\nCódigo: " + cita.getIdCita() + "). \nEl pago está pendiente.");
                    idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
                });
            }

            System.out.println("Cita guardada con éxito");

            // Limpiar los campos después de agregar la cita
            limpiarCampos();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cita Existente");
            alert.setHeaderText(null);
            alert.setContentText("Ya existe una cita con el radicado seleccionado.");
            alert.showAndWait();

            // Limpiar los campos y reiniciar la interfaz
            limpiarCampos();
            return;

        }



    }

    private boolean obtenerPagadoSeleccionado() {
        // Lógica para obtener la opción de "true" o "false" del TextField, por ejemplo:
        String valorPagado = idPagado.getText().trim().toLowerCase(); // Convertir a minúsculas para ser case-insensitive
        return "true".equals(valorPagado); // Devuelve true si el texto es "true", de lo contrario, devuelve false
    }

    // Método para limpiar los campos de la vista
    private void limpiarCampos() {
        idDocumentoPaciente.clear(); // Limpiar el campo de documento del paciente
        idRadicadoComboBox.getItems().clear(); // Limpiar el ComboBox de radicados
        idComboBoxMedicos.getItems().clear(); // Limpiar el ComboBox de médicos
        idPagado.clear(); // Limpiar el campo de pagado
        idMensajeLabel.setText(""); // Limpiar el mensaje de la etiqueta
    }


    //Metodo para filtrar los medicos por especialidad
    private DoubleLinkedList<Medico> filtrarMedicosPorEspecialidad(Especialidad especialidad) {
        // Este método debería devolver la lista de médicos filtrada por la especialidad
        return medicosController.buscarMedicoPorEspecialidad(especialidad);
    }//filtrarMedicosPorEspecialidad



    private void actualizarComboBox(String documentoPaciente) {
        // Limpiar el ComboBox
        idRadicadoComboBox.getItems().clear();

        // Verificar si el documento del paciente no está vacío
        if (!documentoPaciente.isEmpty()) {
            // Obtener la lista de exámenes del paciente usando el controlador correspondiente
            DoubleLinkedList<RegistroExamen> listaExamenes = registroExamenController.buscarPorIdPacienteAutorizado(documentoPaciente);
            logger.log(Level.INFO, "Lista Examenes: {0}", listaExamenes.toString());

            for (int i = 0; i < listaExamenes.tamano(); i++) {
                RegistroExamen registroTemp = listaExamenes.buscarPorIndiceIterar(i);
                logger.log(Level.INFO, "registro encontrado: {0}", registroTemp.toString());
                idRadicadoComboBox.getItems().add(registroTemp.getRadicadoExamen() + " - " + registroTemp.getMotivoCitaExamen().getTipoExamen()+ " - " + registroTemp.getMotivoCitaExamen().getEspecialidad());
            }

            // Verificar si se encontraron exámenes para el paciente
            if (idRadicadoComboBox.getItems().isEmpty()) {
                idMensajeLabel.setText("No se encontraron exámenes para el paciente.");
                idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);
            } else {
                idMensajeLabel.setText("Selecciona un examen.");
                idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
            }


        } else {
            idMensajeLabel.setText("Ingrese el documento del paciente.");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);

        }
    }



    //Del combo box radicado extraigo el codigo del examen, debo encontrar la especialida para de ahi encontrar el medico y llenar la listaDoble
}
