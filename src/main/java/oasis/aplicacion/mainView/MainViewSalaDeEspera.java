package oasis.aplicacion.mainView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oasis.controller.SalaEsperaController;
import oasis.aplicacion.controllerView.ViewSalaDeEsperaController;

public class MainViewSalaDeEspera extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar la vista de la sala de espera
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewSalaDeEspera.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Obtener el controlador de la vista
            ViewSalaDeEsperaController controller = loader.getController();

            // Crear el controlador de la sala de espera
            SalaEsperaController salaEsperaController = new SalaEsperaController();

            // Establecer el controlador de la sala de espera en la vista
            controller.setSalaEsperaController(salaEsperaController);

            // Configurar la escena y mostrar la ventana principal
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
