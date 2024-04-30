package oasis.controller;

import oasis.model.domain.User;
import oasis.model.repository.UserRepository;

public class UserController {
    private final UserRepository userRepository;

    public UserController( ) {
        this.userRepository = new UserRepository();
    }

    //mETDOO AGREGAR USUARIO
    public void agregarUsuario(String idUsuario, String password) {
        User usuario = new User(idUsuario, password);
        userRepository.guardarUser(usuario);
    }

    //METODO ELIMINAR USUARIO
    public void eliminarUsuario(User user) {
        userRepository.eliminarUser(user);
    }

    public String interfazUsuario(String user, String password) {
        if (userRepository.buscarPorUsuario(user, password)) {
            if (user.equals("admin") && password.equals("admin")) {
                User usuarioNew = userRepository.buscarUsuario(user, password);
                userRepository.iniciarSession(usuarioNew);
                return "admin";
                // Se activa el controller de vista de admin
            } else {
                return "user";
                // Se activa el controller de vista de usuario
            }
        }
        return "No existe";
    }
}
