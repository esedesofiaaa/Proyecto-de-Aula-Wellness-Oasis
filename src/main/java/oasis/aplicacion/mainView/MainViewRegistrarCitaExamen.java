package oasis.aplicacion.mainView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oasis.aplicacion.controllerView.ViewPagarCitaController;
import oasis.aplicacion.controllerView.ViewRegistrarCitaExamenController;

import java.io.IOException;

public class MainViewRegistrarCitaExamen extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Cargar la vista de inicio de sesión desde el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewRegistrarCitaExamen.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la vista de inicio de sesión
        ViewRegistrarCitaExamenController controller = loader.getController();

        // Configurar el escenario y mostrar la escena
        primaryStage.setTitle("Agendar Cita Examen");
        primaryStage.setScene(new Scene(root, 652, 526));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
