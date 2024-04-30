package oasis.Main;

import oasis.controller.UserController;
import oasis.model.domain.User;

public class MainUser {
    public static void main(String[] args) {

        UserController userController = new UserController();

       // userController.agregarUsuario("admin", "admin");

        User admin = new User("admin", "admin");

        userController.interfazUsuario(admin.getIdUser(), admin.getPassword());
    }
}
