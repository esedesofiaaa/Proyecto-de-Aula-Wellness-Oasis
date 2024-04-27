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

    public void interfazUsuario(User user) {
       if(userRepository.buscarUsuario(user) == true) {
           if (user.getIdUser().equals("admin") && user.getPassword().equals("admin")) {
               userRepository.iniciarSession(user);
               // Se activa el controller de vista de admin
           } else {
           //Se activa el controller de vista paciente
           }
       }
    }
}
