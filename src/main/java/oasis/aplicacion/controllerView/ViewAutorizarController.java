package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import oasis.controller.AutorizacionController;
import oasis.model.domain.registroExamen.RegistroExamen;

public class ViewAutorizarController {

    @FXML
    private Label idDocumentoLabel;
    @FXML
    private Label idRadicadoLabel;
    @FXML
    private Label idTipoExamenLabel;
    @FXML
    private Label idNotaLabel;

    @FXML
    private ComboBox<String> idComboBoxAutorizar;

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
    private void autorizar() {
        autorizacionController.autorizarExamen();
        mostrarAlerta("Examen autorizado con éxito", Alert.AlertType.INFORMATION);
        siguienteExamen();
    }

    @FXML
    private void noAutorizar() {
        autorizacionController.noAutorizarExamen();
        mostrarAlerta("Autorización negada con éxito", Alert.AlertType.ERROR);
        siguienteExamen();
    }

    @FXML
    private void siguienteExamen() {
        if (!autorizacionController.obtenerTodos().estaVacia()) {
            initialize(); // Mostrar el próximo examen si hay más
        } else {
            idNotaLabel.setText("No hay más exámenes por autorizar");
            idNotaLabel.setTextFill(Color.RED);
            System.out.println("No hay más exámenes por autorizar");
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
