package oasis.Main;

import oasis.controller.UserController;
import oasis.model.domain.User;
import oasis.model.repository.UserRepository;

public class MainUser {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();

        User user = new User("user", "user");
        User user1 = new User("admin", "admin");

        userRepository.guardarUser(user);
        userRepository.guardarUser(user1);


        //buscar usuario
        System.out.println(userRepository.buscarUsuarioPorUsuario(user));
        System.out.println(userRepository.buscarUsuarioPorUsuario(user1));


    }
}