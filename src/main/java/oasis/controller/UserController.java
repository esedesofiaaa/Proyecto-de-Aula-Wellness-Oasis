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

    public String interfazUsuario(User user) {
        if (userRepository.buscarUsuarioPorUsuario(user)!=null) {
            if (user.getIdUser().equals("admin") && user.getPassword().equals("admin")) {
                userRepository.iniciarSession(user);
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
