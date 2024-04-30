package oasis.Main;

import oasis.controller.UserController;

public class MainUser {
    public static void main(String[] args) {

        UserController userController = new UserController();

        userController.agregarUsuario("admin", "admin");


    }
}
