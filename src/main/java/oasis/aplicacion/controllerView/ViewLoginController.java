package oasis.aplicacion.controllerView;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oasis.model.repository.UserRepository;
import oasis.model.domain.User;
import oasis.aplicacion.mainView.MainViewAdministrador;

public class ViewLoginController {

    @FXML
    private TextField idUsuarioField;

    @FXML
    private PasswordField idContraseñaField;

    @FXML
    private Button idIniciarSesionButton;

    private final UserRepository UserRepository;
    private User user;

    private static final Logger logger = Logger.getLogger(ViewLoginController.class.getName());

    public ViewLoginController() {
        this.UserRepository = new UserRepository();
    }

    @FXML
    private void iniciarSesion() throws IOException {
        String idUsuario = idUsuarioField.getText();
        String contraseña = idContraseñaField.getText();

        logger.log(Level.INFO, "Intento de inicio de sesión con usuario: {0}", idUsuario);
        logger.log(Level.INFO, "Intento de inicio de sesión con contraseña: {0}", contraseña);

        user = new User(idUsuario, contraseña);

        logger.log(Level.INFO, "Intento de inicio de sesión con USER: {0}", user.getIdUser() + " " + user.getPassword());
        UserRepository.buscarUsuarioPorUsuario(user);

        if (user != null) {
            logger.log(Level.INFO, "Usuario encontrado en el repositorio: {0}", user.getIdUser());
            logger.log(Level.INFO, "Contraseña encontrada en el repositorio: {0}", user.getPassword());
            if ("admin".equals(user.getIdUser())) {
                UserRepository.logearUsuario(user);
                logger.log(Level.INFO, "Intento de inicio de buscar con usuario: {0}", user);

                // Abrir la ventana de administrador
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewAdministrador.fxml"));
                Parent root = loader.load();

                // Obtener el controlador de la vista de paciente
                ViewAdministradorController adminController = loader.getController();

                // Configurar el escenario y mostrar la escena
                Stage stage = (Stage) idIniciarSesionButton.getScene().getWindow();
                stage.setTitle("Admin");
                stage.setScene(new Scene(root, 600, 500));
                stage.show();
            } else {

                UserRepository.logearUsuario(user);
                logger.log(Level.INFO, "Intento de inicio de buscar con usuario: {0}", user);

                // Mostrar mensaje de error
                mostrarMensajeError("Usuario encontrado");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewPaciente.fxml"));
                Parent root = loader.load();

                // Obtener el controlador de la vista de paciente
                ViewPacienteController pacienteController = loader.getController();

                // Configurar el escenario y mostrar la escena
                Stage stage = (Stage) idIniciarSesionButton.getScene().getWindow();
                stage.setTitle("Paciente");
                stage.setScene(new Scene(root, 600, 500));
                stage.show();                // Continuar con la lógica para abrir la vista de paciente si es necesario
            }
        } else {
            mostrarMensajeError("El usuario no existe.");
            logger.log(Level.WARNING, "Usuario no encontrado en el repositorio");
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