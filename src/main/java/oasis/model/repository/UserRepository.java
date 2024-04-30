package oasis.model.repository;

import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.User;
import oasis.model.domain.cita.Cita;
import oasis.model.domain.medico.Medico;
import oasis.shared.FileJsonAdapter;

public class UserRepository {
    private final FileJsonAdapter<User> jsonAdapterUser; // Adaptador para manejar la lectura y escritura de objetos Paciente en formato JSON
    private final String pathFile; // Esta variable de referencia guarda la ruta del archivo JSON que contiene los datos de los pacientes
    // pero guarda la direccion dentro del proyecto no dentro del disco
    private DoubleLinkedList<User> listaUsers;

    /**
     * Constructor de la clase RegistroExamenRepository
     */
    public UserRepository() {
        this.pathFile = "src/main/java/oasis/dataBase/Users.Json"; // Ruta del archivo JSON dentro del proyecto
        this.jsonAdapterUser = FileJsonAdapter.getInstance(); // Obtener una instancia del adaptador JSON para Pacientes
        this.listaUsers = jsonAdapterUser.getObjects(pathFile, User[].class);
    }

    public void guardarUser(User user) {
        listaUsers.agregarAlFinal(user); // Agregar el nuevo registro de examen a la lista de registros
        jsonAdapterUser.writeObjects(pathFile, listaUsers); // Guardar la lista de registros en el archivo JSON
    }

    public void eliminarUser(User user) {
        int indice = listaUsers.buscarElemento(user);
        if (indice != -1) {
            listaUsers.eliminarEnMedio(indice);
            jsonAdapterUser.writeObjects(pathFile, listaUsers);
            System.out.println("User eliminado: " + user.getIdUser());
        } else {
            System.out.println("El user con id " + user.getIdUser() + " no existe.");
        }
    }
    public void iniciarSession(User user) {
        int indice = listaUsers.buscarElemento(user);
        if (indice != -1) {
            user.setLogged(true);
            jsonAdapterUser.writeObjects(pathFile, listaUsers);
            System.out.println("User loggeado: " + user.getIdUser());
        } else {
            System.out.println("El user con id " + user.getIdUser() + " no existe.");
        }
    }


    //Metodo para buscar usuario por contraseña y usuario que retorne boolean si lo encuentra

    public boolean buscarPorUsuario(String user ,String password) {
        for (int i = 0; i < listaUsers.tamano(); i++) { // Recorrer la lista de pacientes
            User usuario = listaUsers.buscarPorIndiceIterar(i); // Obtener el paciente en el índice actual
            if (usuario.getIdUser()==user&& usuario.getPassword()==password) { // Verificar si el ID del paciente coincide
                return true; // Retornar el usuario encontrado
            }
        }
        return false;
    }

    public User buscarUsuario(String user ,String password) {
        for (int i = 0; i < listaUsers.tamano(); i++) { // Recorrer la lista de pacientes
            User usuario = listaUsers.buscarPorIndiceIterar(i); // Obtener el paciente en el índice actual
            if (usuario.getIdUser()==user&& usuario.getPassword()==password) { // Verificar si el ID del paciente coincide
                return usuario; // Retornar el usuario encontrado
            }
         }
        return null;
    }

}
