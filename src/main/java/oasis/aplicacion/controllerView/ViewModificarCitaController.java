package oasis.aplicacion.controllerView;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import oasis.controller.CitasController;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.cita.Cita;
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
    private Label idMensajeLabel;

    private final CitasController citasController;

    private static final Logger logger = Logger.getLogger(ViewLoginController.class.getName());


    public ViewModificarCitaController() {
        this.citasController = new CitasController();
    }

    @FXML
    public void initialize() {

        idCitasComboBox.getItems().clear();

        // Agregar un listener al TextField para detectar cambios en su contenido
        idDocumento.textProperty().addListener((observable, oldValue, newValue) -> {
            actualizarComboBox(newValue);
        });


        
        
        // Agregar opciones al ComboBox idAtributoComboBox
        idAtributoComboBox.getItems().addAll("Motivo Cita", "Medico", "Especialidad", "Radicado Examen");

        if(idAtributoComboBox.getValue()!=null){
            if(idAtributoComboBox.getValue().equals("Motivo Cita")){
                idValorComboBox.getItems().addAll("CONTROL", "VALORACION", "EXAMEN");
            }

        }

    }


    @FXML
    private void modificarCita() {
        String documentoPaciente = idDocumento.getText();
        String atributo = idAtributoComboBox.getValue();
        String valor = idValorComboBox.getValue();

        Cita casoExamen = citasController.buscarCitaPorId(idCitasComboBox.getValue().split(" - ")[0]);
        if (casoExamen.getMotivoCita() == "EXAMEN") {

           // citasController.citaACitaExamen(documentoPaciente,atributo,valor);

        }
    }

    private void actualizarComboBox(String documentoPaciente) {
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

                idCitasComboBox.getItems().add(citaTemp.getIdCita() + " - " + citaTemp.getMotivoCita()+ " - " + citaTemp.getEspecialidad());
            }

            // Verificar si se encontraron exámenes para el paciente
            if (idCitasComboBox.getItems().isEmpty()) {
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
    }


    

