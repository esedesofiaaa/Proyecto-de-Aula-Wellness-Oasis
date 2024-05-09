package oasis.aplicacion.controllerView;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import oasis.controller.CitasController;
import oasis.controller.MedicosController;
import oasis.controller.RegistroExamenController;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.Especialidad;
import oasis.model.domain.cita.Cita;
import oasis.model.domain.medico.Medico;
import oasis.model.domain.registroExamen.RegistroExamen;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewModificarCitaController {

    @FXML
    private TextField idDocumento;

    @FXML
    private ComboBox<String> idAtributoComboBox;
    @FXML
    private ComboBox<String> idCitasComboBox;

    @FXML
    private ComboBox<String> idValorComboBox;
    @FXML
    private Text idTextMedicoExtra;

    @FXML
    private ComboBox<String> idComboBoxMedicoExtra;
    @FXML
    private ComboBox<String> idComboBoxMedicoExamen;

    @FXML
    private Label idMensajeLabel;

    private final CitasController citasController;
    private final MedicosController medicosController;
    private final RegistroExamenController registroExamenController;

    private static final Logger logger = Logger.getLogger(ViewLoginController.class.getName());


    public ViewModificarCitaController() {
        this.citasController = new CitasController();
        this.medicosController = new MedicosController();
        this.registroExamenController = new RegistroExamenController();
    }

    @FXML
    public void initialize() {
        idComboBoxMedicoExtra.setVisible(false);
        idTextMedicoExtra.setVisible(false);
        idCitasComboBox.getItems().clear();
        idComboBoxMedicoExamen.setVisible(false);
        // Agregar un listener al TextField para detectar cambios en su contenido
        idDocumento.textProperty().addListener((observable, oldValue, newValue) -> {
            actualizarComboBoxAtributo(newValue);
        });

        // Agregar opciones al ComboBox idAtributoComboBox
        idAtributoComboBox.getItems().addAll("Motivo Cita", "Medico", "Especialidad", "Radicado Examen");

        idAtributoComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizarComboBoxValor(newValue);
        });

        idValorComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizarComboBoxExtra();
        });

    }

    private void actualizarComboBoxExtra() {

        if (idValorComboBox.getValue().equals("EXAMEN")) {
            mostrarOcultarRadicado();
            DoubleLinkedList<RegistroExamen> listaExamenes = registroExamenController.buscarPorIdPaciente(idDocumento.getText());
            logger.log(Level.INFO, "Lista Examenes: {0}", listaExamenes.toString());

            for (int i = 0; i < listaExamenes.tamano(); i++) {

                RegistroExamen registroTemp = listaExamenes.buscarPorIndiceIterar(i);
                logger.log(Level.INFO, "registro encontrado: {0}", registroTemp.toString());

                idComboBoxMedicoExtra.getItems().add(registroTemp.getRadicadoExamen() + " - " + registroTemp.getMotivoCitaExamen().getTipoExamen());
                logger.log(Level.INFO, "registro encontrado: {0}", idComboBoxMedicoExtra.toString());
            }
            // Verificar si se encontraron exámenes para el paciente
            if (idComboBoxMedicoExtra.getItems().isEmpty()) {
                idMensajeLabel.setText("No se encontraron exámenes para el paciente.");
                idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);

            } else {
                idMensajeLabel.setText("Selecciona un examen.");
                idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);

            }

            //Falta llenar el combo box con los examenes ya autorizados

        }

        if(idValorComboBox.getValue().equals("CARDIOLOGIA") || idValorComboBox.getValue().equals("DERMATOLOGIA") || idValorComboBox.getValue().equals("NUTRICION") || idValorComboBox.getValue().equals("MEDICINA_GENERAL") || idValorComboBox.getValue().equals("ODONTOLOGIA") || idValorComboBox.getValue().equals("PEDIATRIA") || idValorComboBox.getValue().equals("PSIQUIATRIA")) {
            mostrarOcultarExamen();
            idComboBoxMedicoExtra.getItems().clear();
            Especialidad especialidadTemp = Especialidad.valueOf(idCitasComboBox.getValue().split(" - ")[2]);
            DoubleLinkedList<Medico> medicoEspecialidad = filtrarMedicosPorEspecialidad(especialidadTemp);
            for (int i = 0; i < medicoEspecialidad.tamano(); i++) {
                Medico medico = medicoEspecialidad.buscarPorIndiceIterar(i);
                idComboBoxMedicoExtra.getItems().add(medico.getIdMedico());
            }


        }

    }




    private void mostrarOcultarExamen() {
        String tipoSeleccionado = idAtributoComboBox.getValue();
        if (tipoSeleccionado != null && tipoSeleccionado.equals("Especialidad")) {
            idTextMedicoExtra.setText("Selecciona un medico:");

            idComboBoxMedicoExtra.setVisible(true);
            idTextMedicoExtra.setVisible(true);
        } else {
            idComboBoxMedicoExtra.setVisible(false);
            idTextMedicoExtra.setVisible(false);
        }
    }

    private void mostrarOcultarRadicado() {
        String tipoSeleccionado = idValorComboBox.getValue();
        if (tipoSeleccionado != null && tipoSeleccionado.equals("EXAMEN")) {
            idTextMedicoExtra.setText("Radicado Examen");
            idComboBoxMedicoExtra.setVisible(true);
            idTextMedicoExtra.setVisible(true);
        } else {
            idComboBoxMedicoExtra.setVisible(false);
            idTextMedicoExtra.setVisible(false);
        }
    }

    private DoubleLinkedList<Medico> filtrarMedicosPorEspecialidad(Especialidad especialidad) {
        // Este método debería devolver la lista de médicos filtrada por la especialidad
        return medicosController.buscarMedicoPorEspecialidad(especialidad);
    }//filtrarMedicosPorEspecialidad


    @FXML
    private void modificarCita() {
        String documentoPaciente = idDocumento.getText();
        String idCita = idCitasComboBox.getValue().split(" - ")[0];
        String atributo = idAtributoComboBox.getValue();
        String valor = idValorComboBox.getValue();
        String valorExtra = idComboBoxMedicoExtra.getValue();
        //Falta revisar los casos en los que entra al metodo modificar cita, a punta de if
        //Cuando entra a citaCitaExamen y usar los logs
        //Falta el combo box cuando selecciona como radicado examen
        //Usar alerts en vez de label si es necesario
        //Si la cita es de tipo examen no puede cambiar la especialidad, solo el medico y radicado
        if(documentoPaciente.isEmpty()|| idCita.isEmpty() || atributo.isEmpty() || valor.isEmpty() || valorExtra.isEmpty()){
            idMensajeLabel.setText("Por favor llene todos los campos.");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);

        } else {
            citasController.modificarCita(idCita, atributo, valor, valorExtra);
            logger.log(Level.INFO, "Resultado: {0}", citasController.modificarCita(idCita, atributo, valor, valorExtra));

            limpiarCampos();
            idMensajeLabel.setText("Cita modificada correctamente.");
        }

        /*Cita casoExamen = citasController.buscarCitaPorId(idCitasComboBox.getValue().split(" - ")[0]);
        if (casoExamen.getMotivoCita() == "EXAMEN") {
            //citasController.citaACitaExamen(documentoPaciente,atributo,valor);
        } else {
            if (atributo == "")
                citasController.modificarCita(idCita, atributo, valor);

        }*/


    }

    private void actualizarComboBoxAtributo(String documentoPaciente) {
        // Limpiar el ComboBox
        idCitasComboBox.getItems().clear();

        // Verificar si el documento del paciente no está vacío
        if (!documentoPaciente.isEmpty()) {
            // Obtener la lista de exámenes del paciente usando el controlador correspondiente
            DoubleLinkedList<Cita> listaCitas = citasController.citasDelPaciente(documentoPaciente);
            logger.log(Level.INFO, "Lista Examenes: {0}", listaCitas.toString());

            for (int i = 0; i < listaCitas.tamano(); i++) {
                Cita citaTemp = listaCitas.buscarPorIndiceIterar(i);
                logger.log(Level.INFO, "registro encontrado: {0}", citaTemp.toString());

                idCitasComboBox.getItems().add(citaTemp.getIdCita() + " - " + citaTemp.getMotivoCita() + " - " + citaTemp.getEspecialidad());
            }

            // Verificar si se encontraron exámenes para el paciente
            if (idCitasComboBox.getItems().isEmpty()) {
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

    private void actualizarComboBoxValor(String atributo) {
        if (idAtributoComboBox.getValue() != null) {
            if (idAtributoComboBox.getValue().equals("Motivo Cita")) {
                idValorComboBox.getItems().addAll("CONTROL", "VALORACION", "EXAMEN");
            }
            if (idAtributoComboBox.getValue().equals("Medico")) {
                Especialidad especialidadTemp = Especialidad.valueOf(idCitasComboBox.getValue().split(" - ")[2]);
                DoubleLinkedList<Medico> medicoEspecialidad = filtrarMedicosPorEspecialidad(especialidadTemp);
                for (int i = 0; i < medicoEspecialidad.tamano(); i++) {
                    Medico medico = medicoEspecialidad.buscarPorIndiceIterar(i);
                    idValorComboBox.getItems().add(medico.getIdMedico());
                }
            }
            if (idAtributoComboBox.getValue().equals("Especialidad")) {
                idValorComboBox.getItems().addAll(
                        "CARDIOLOGIA",
                        "DERMATOLOGIA",
                        "NUTRICION",
                        "MEDICINA_GENERAL",
                        "ODONTOLOGIA",
                        "PEDIATRIA",
                        "PSIQUIATRIA"
                );
            }
            if (idAtributoComboBox.getValue().equals("Radicado Examen")) {
                DoubleLinkedList<RegistroExamen> listaExamenes = registroExamenController.buscarPorIdPaciente(idDocumento.getText());
                logger.log(Level.INFO, "Lista Examenes: {0}", listaExamenes.toString());

                for (int i = 0; i < listaExamenes.tamano(); i++) {
                    RegistroExamen registroTemp = listaExamenes.buscarPorIndiceIterar(i);
                    logger.log(Level.INFO, "registro encontrado: {0}", registroTemp.toString());

                    idValorComboBox.getItems().add(registroTemp.getRadicadoExamen() + " - " + registroTemp.getMotivoCitaExamen().getTipoExamen());
                }
                // Verificar si se encontraron exámenes para el paciente
                if (idValorComboBox.getItems().isEmpty()) {
                    idMensajeLabel.setText("No se encontraron exámenes para el paciente.");
                    idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);

                } else {
                    idMensajeLabel.setText("Selecciona un examen.");
                    idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);

                }
            }
        }


    }

    private void limpiarCampos() {
        idDocumento.clear(); // Limpiar el campo de documento del paciente
        idCitasComboBox.getSelectionModel().clearSelection();
        idAtributoComboBox.getSelectionModel().clearSelection();
        idValorComboBox.getSelectionModel().clearSelection();
        idComboBoxMedicoExtra.getSelectionModel().clearSelection();
        idMensajeLabel.setText(""); // Limpiar el mensaje de la etiqueta
    }

}




    

