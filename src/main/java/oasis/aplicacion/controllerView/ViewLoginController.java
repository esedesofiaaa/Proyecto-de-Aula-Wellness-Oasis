package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import oasis.aplicacion.mainView.MainUsuarioNoExiste;
import oasis.controller.UserController;
import oasis.model.domain.User;
import oasis.aplicacion.mainView.MainViewAdministrador;
import oasis.aplicacion.mainView.MainViewPaciente;

public class ViewLoginController {

    @FXML
    private TextField idUsuarioField;

    @FXML
    private PasswordField idPasswordField;

    @FXML
    private Button idIniciarSesionButton;

    private final UserController userController;

    public ViewLoginController() {
        this.userController = new UserController();
    }

    @FXML
    void initialize() {
        idIniciarSesionButton.setOnAction(event -> iniciarSesion());
    }

    @FXML
    private void iniciarSesion() {
        String idUsuario = idUsuarioField.getText();
        String password = idPasswordField.getText();

        // Crear un objeto User con los datos ingresados
        User usuario = new User(idUsuario, password);

        // Obtener el tipo de interfaz de usuario desde el controlador
        String tipoUsuario = userController.interfazUsuario(usuario);

        // Determinar la acción basada en el tipo de usuario
        switch (tipoUsuario) {
            case "admin":
                // Abrir la ventana de administrador
                MainViewAdministrador.main(new String[0]);
                break;
            case "user":
                // Abrir la ventana de usuario
                MainViewPaciente.main(new String[0]);
                break;
            case "No existe":
                // Mostrar mensaje de error si el usuario no existe
                MainUsuarioNoExiste.main(new String[0]);
                break;
            default:
                // Manejar cualquier otro caso inesperado
                mostrarMensajeError("Error desconocido.");
                break;
        }
    }

    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}