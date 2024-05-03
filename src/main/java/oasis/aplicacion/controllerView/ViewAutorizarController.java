package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oasis.controller.AutorizacionController;
import oasis.model.domain.registroExamen.RegistroExamen;

public class ViewAutorizarController {

    @FXML
    private Label  idDocumentoLabel;
    @FXML
    private Label  idRadicadoLabel;
    @FXML
    private Label idTipoExamenLabel;

    @FXML
    private ComboBox<String>idComboBoxAutorizar;

    private final AutorizacionController autorizacionController;

    public ViewAutorizarController() {
        this.autorizacionController = new AutorizacionController();
    }
    @FXML
    private void initialize() {
        idComboBoxAutorizar.getItems().addAll("Sí", "No");

        RegistroExamen examen = autorizacionController.obtenerTodos().peek();

        idDocumentoLabel.setText(examen.getIdPaciente());
        idRadicadoLabel.setText(examen.getRadicadoExamen());
        idTipoExamenLabel.setText(examen.getMotivoCitaExamen().getTipoExamen());

    }

    @FXML
    private void autorizar (){
        if(idComboBoxAutorizar.getValue().equals("Sí")) {
            autorizacionController.autorizarExamen();
        } else{
            autorizacionController.noAutorizarExamen();
        }

      if(autorizacionController.peek() !=null){
          initialize();
      } else{
            System.out.println("No hay más exámenes por autorizar");

            //poner esto dentrode un label
      }
    }

//Separar el combobox en dos botones y que cada uno se inicialice, falta hacer los botones en la visual

}
