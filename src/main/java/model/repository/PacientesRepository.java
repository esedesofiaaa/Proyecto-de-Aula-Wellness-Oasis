package model.repository;

import estructurasDatos.listas.DoubleLinkedList; //Imoortamos la clase de la lista enlazada doble para poder usarla
import model.domain.paciente.Paciente;
import shared.FileJsonAdapter;

public class PacientesRepository {
    private final FileJsonAdapter<Paciente> jsonAdapterPacientes; // Adaptador para manejar la lectura y escritura de objetos Paciente en formato JSON
    private final String pathFile; // Esta variable de referencia guarda la ruta del archivo JSON que contiene los datos de los pacientes
                                   // pero guarda la direccion dentro del proyecto no dentro del disco
    private DoubleLinkedList<Paciente> listaPacientes; //Lista enlazada que almacena los objetos de tipo Paciente, en esta caso usamos una lista
    //doble enlazada de nuestras estructuras de datos


    //Constructor
    public PacientesRepository() {
        this.pathFile = "src/main/java/dataBase/Pacientes.Json"; // Ruta del archivo JSON dentro del proyecto
        this.jsonAdapterPacientes = FileJsonAdapter.getInstance(); // Obtener una instancia del adaptador JSON para Pacientes
        // Inicializar la lista de pacientes obteniendo los datos del archivo JSON
        this.listaPacientes = jsonAdapterPacientes.getObjects(pathFile, Paciente[].class);
        /* Si revisamos bien el argumento del metodo getObjets recibe la direccion donde se guarda el archivo Json que es la
         * variable de referencia pathFile y recibe el tipo de dato que va a guardar que es de tipo Paciente
         */
    }//Constructor

    //obtenerTodos: Método para obtener todos los pacientes
    public DoubleLinkedList<Paciente> obtenerTodos() {//si funciona
        return listaPacientes; // Retorna la lista de pacientes
    }//obtenerTodos


    //añadirNuevoPaciente: Método para añadir un nuevo paciente
    public void añadirNuevoPaciente(Paciente nuevoPaciente) {
        listaPacientes.agregarAlFinal(nuevoPaciente); // Agrega el nuevo paciente a la lista
        /* Aqui estamos reutilizando el metodo de la lista doble enlazada que nos permite agregar un elemento al final
         * de la lista doble enlazada*/
        jsonAdapterPacientes.writeObjects(pathFile, listaPacientes); // Actualizar el archivo JSON con la lista actualizada de pacientes
    }//añadirNuevoPaciente

    //añadirPacientes: Método para añadir varios pacientes a la lista
    public void añadirPacientes(DoubleLinkedList<Paciente> nuevosPacientes) {
        while (!nuevosPacientes.estaVacia()) { // Itera hasta que la lista de nuevos pacientes esté vacía
            Paciente paciente = nuevosPacientes.eliminarDelInicio(); // Obtiene y elimina el primer paciente de la lista de nuevos pacientes
            listaPacientes.agregarAlFinal(paciente); // Agrega el paciente a la lista de pacientes
        }
        jsonAdapterPacientes.writeObjects(pathFile, listaPacientes); // Actualizar el archivo JSON con la lista actualizada de pacientes
    }//añadirPacientes

    //eliminarPaciente: Método para eliminar un paciente
    public void eliminarPaciente(Paciente paciente) {
        // Iterar sobre la lista de pacientes para buscar el paciente a eliminar
        int indice = listaPacientes.buscarElemento(paciente); // Busca el índice del paciente en la lista
        if (indice != -1) { // Si se encuentra el paciente en la lista
            listaPacientes.eliminarEnMedio(indice); // Elimina el paciente de la lista
            // Actualizar el archivo JSON con la lista actualizada de pacientes
            jsonAdapterPacientes.writeObjects(pathFile, listaPacientes);
        } else {
            System.out.println("El paciente no se encontró en la lista."); // Si el paciente no se encuentra en la lista
        }
    }//eliminarPaciente

    // Método para obtener un paciente por su ID
    public Paciente obtenerPorId(String idPaciente) {
        DoubleLinkedList<Paciente> pacientes = obtenerTodos(); // Obtener la lista completa de pacientes
        for (int i = 0; i < pacientes.tamano(); i++) { // Recorrer la lista de pacientes
            Paciente paciente = pacientes.buscarPorIndiceIterar(i); // Obtener el paciente en el índice actual
            if (paciente.getId().equals(idPaciente)) { // Verificar si el ID del paciente coincide
                return paciente; // Retornar el paciente encontrado
            }
        }
        // Si no se encontró ningún paciente con el ID especificado, retornar null
        return null;
    }//obtenerPorId


}