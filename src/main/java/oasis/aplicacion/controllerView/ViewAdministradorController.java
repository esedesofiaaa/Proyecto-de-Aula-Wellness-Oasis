package oasis.aplicacion.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewAdministradorController {

    /*Atributos de la clase, pero son los botones, solo toca crear el atributo de tipo boton*/
    @FXML
    private Button idBottonRegistroMedico;

    @FXML
    private Button idBottonRegistroPaciente;

    @FXML
    private Button idBottonPagarCita;
    @FXML
    private Button idBottonAutorizarExamen;


    // Otros botones y métodos aquí

    @FXML
    void abrirRegistroMedico(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewRegistroMedico.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro Médico");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//abrirRegistroMedico

    @FXML
    void abrirRegistroPaciente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewRegistroPaciente.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro de Paciente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//abrirRegistroPaciente

    @FXML
    void abrirCitaControlOValoracion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewRegistroCita.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro Cita o Control");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//abrirCitaControlOValoracion

    @FXML
    void abrirPagarCita(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewPagarCita.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Pagar Cita");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//abrirPagarCita


    @FXML
    void autorizarExamen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewAutorizar.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Autorizar Examen");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//abrirPagarCita
    // Otros métodos para abrir otras vistas con los otros botones
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
            stage.setTitle("Autorizar Examen");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void abrirSalaDeEspera(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewSalaDeEspera.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Autorizar Examen");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}