package oasis.aplicacion.mainView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oasis.aplicacion.controllerView.ViewLoginController;

import java.io.IOException;

public class MainViewLogin extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Cargar la vista de inicio de sesión desde el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewLogin.fxml"));
        Parent root = loader.load();
        ViewLoginController controller = loader.getController();


        // Configurar el escenario y mostrar la escena
        primaryStage.setTitle("Inicio de Sesión");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}