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

public class ViewSolicitarAutorizacionController {


    @FXML
    private ComboBox<RegistroExamen> idComboBoxExamenesAutorizar;
    @FXML
    private Label idMensajeLabel; // Nuevo campo para el Label
    @FXML
    private TextField   idDocumentoPaciente;

    private final RegistroExamenController registroExamenController ;

    private final AutorizacionController  autorizacionController;

    private final StackList<RegistroExamen> pilaAutorizaciones;


    public ViewSolicitarAutorizacionController() {


        this.registroExamenController = new RegistroExamenController();
        this.autorizacionController = new AutorizacionController();
        this.pilaAutorizaciones = new StackList<>();
    }

    @FXML
    private void initialize() {

        idComboBoxExamenesAutorizar.getItems().clear();

        DoubleLinkedList<RegistroExamen> listaExamenes =registroExamenController.buscarPorIdPaciente(idDocumentoPaciente.getText());
        for (int i = 0; i < listaExamenes.tamano(); i++) {
            RegistroExamen registroTemp = listaExamenes.buscarPorIndiceIterar(i);
                idComboBoxExamenesAutorizar.getItems().add(registroTemp);
        }
    }



}
