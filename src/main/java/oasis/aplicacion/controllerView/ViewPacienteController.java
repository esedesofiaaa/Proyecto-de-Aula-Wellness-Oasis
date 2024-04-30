package oasis.aplicacion.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewPacienteController {

    @FXML
    private Button idAgendarCita; // Este es el botón que ejecuta la acción de agendar cita en la vista del paciente

    @FXML
    void abrirAgendarCita(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewRegistroCita.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Agendar Cita");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Aquí puedes agregar más métodos para controlar otros aspectos de la vista del paciente

}