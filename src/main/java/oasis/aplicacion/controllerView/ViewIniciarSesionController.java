package oasis.aplicacion.controllerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewIniciarSesionController {

    @FXML
    private TextField idUsuario;

    @FXML
    private TextField idContraseña;

    @FXML
    private Button idBotonIniciarSesion;

    @FXML
    void iniciarSesion(ActionEvent event) {
        // Aquí puedes implementar la lógica para verificar las credenciales del usuario
        // Por ahora, simplemente imprimir los valores de los campos de usuario y contraseña
        System.out.println("Usuario: " + idUsuario.getText());
        System.out.println("Contraseña: " + idContraseña.getText());

        // Verificar las credenciales y abrir la siguiente vista si son válidas
        if (credencialesValidas()) {
            // Obtener el Stage de la vista actual
            Stage stageActual = (Stage) idBotonIniciarSesion.getScene().getWindow();
            // Cerrar la vista actual
            stageActual.close();

            // Cargar y mostrar la siguiente vista
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ruta/de/la/siguiente/vista.fxml"));
                Parent root = loader.load();
                Stage siguienteStage = new Stage();
                siguienteStage.setTitle("Título de la siguiente vista");
                siguienteStage.setScene(new Scene(root));
                siguienteStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Mostrar un mensaje de error o realizar alguna acción si las credenciales no son válidas
        }
    }

    // Método ficticio para verificar las credenciales del usuario
    private boolean credencialesValidas() {
        // Aquí puedes implementar la lógica para verificar las credenciales del usuario
        // Por ahora, simplemente retornamos true si los campos no están vacíos
        return !idUsuario.getText().isEmpty() && !idContraseña.getText().isEmpty();
    }
}
