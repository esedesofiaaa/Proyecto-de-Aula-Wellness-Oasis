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
    private Button IdRegistrarExamen; // Este es el botón que ejecuta la acción de registrar examen en la vista del paciente

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

    @FXML
    void abrirRegistrarExamen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewRegistroExamen.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registo de Examen");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @FXML
    void abrirSolicitarAutorizacion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewSolicitarAutorizacion.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Solicitar  Autorizacion");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void abrirRegistroCitaExamen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewRegistrarCitaExamen.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Solicitar Toma de Examen");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void abrirModificarCita(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewModificarCita.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Modificar Cita");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void abrirCancelarCita(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewCancelarCita.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Cancelar Cita");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Aquí puedes agregar más métodos para controlar otros aspectos de la vista del paciente

}