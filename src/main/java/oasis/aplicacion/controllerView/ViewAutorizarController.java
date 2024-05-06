package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
    private Label idNotaLabel;

    @FXML
    private ComboBox<String>idComboBoxAutorizar;

    private final AutorizacionController autorizacionController;

    public ViewAutorizarController() {
        this.autorizacionController = new AutorizacionController();
    }
    @FXML
    private void initialize() {
        if (!autorizacionController.obtenerTodos().estaVacia()) {
            RegistroExamen examen = autorizacionController.obtenerTodos().peek();

            idDocumentoLabel.setText(examen.getIdPaciente());
            idRadicadoLabel.setText(examen.getRadicadoExamen());
            idTipoExamenLabel.setText(examen.getMotivoCitaExamen().getTipoExamen());
            idNotaLabel.setText("Examen por autorizar");
            idNotaLabel.setTextFill(Color.BLACK);
        } else {
            idNotaLabel.setText("No hay exámenes por autorizar");
            idNotaLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    private void autorizar () {

        autorizacionController.autorizarExamen();
        idNotaLabel.setText("Examen autorizado con éxito");
        idNotaLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
        siguienteExamen();
    }
    @FXML
    private void noAutorizar (){

        autorizacionController.noAutorizarExamen();
        idNotaLabel.setText("Autorización negada con éxito");
        idNotaLabel.setTextFill(javafx.scene.paint.Color.RED);
        siguienteExamen();
    }

    @FXML
    private void siguienteExamen(){

        if (!autorizacionController.obtenerTodos().estaVacia()) {
            initialize(); // Mostrar el próximo examen si hay más
        } else {
            idNotaLabel.setText("No hay más exámenes por autorizar");
            idNotaLabel.setTextFill(Color.RED);
            System.out.println("No hay más exámenes por autorizar");
        }
    }

//Separar el combobox en dos botones y que cada uno se inicialice, falta hacer los botones en la visual

}
