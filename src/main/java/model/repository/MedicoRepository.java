package model.repository;

import estructurasDatos.listas.DoubleLinkedList;
import model.Medico;
import model.Paciente;
import shared.FileJsonAdapter;

public class MedicoRepository
{
    private final FileJsonAdapter<Medico> jsonAdapterMedicos; // Adaptador para manejar la lectura y escritura de objetos Paciente en formato JSON
    private final String pathFile; // Esta variable de referencia guarda la ruta del archivo JSON que contiene los datos de los pacientes
    // pero guarda la direccion dentro del proyecto no dentro del disco
    private DoubleLinkedList<Medico> listaMedicos; //Lista enlazada que almacena los objetos de tipo Paciente, en esta caso usamos una lista
    //doble enlazada de nuestras estructuras de datos


    //Constructor
    public MedicoRepository() {
        this.pathFile = "src/main/java/dataBase/Medicos.Json"; // Ruta del archivo JSON dentro del proyecto
        this.jsonAdapterMedicos = FileJsonAdapter.getInstance(); // Obtener una instancia del adaptador JSON para Pacientes
        // Inicializar la lista de pacientes obteniendo los datos del archivo JSON
        this.listaMedicos = jsonAdapterMedicos.getObjects(pathFile, Medico[].class);
        /* Si revisamos bien el argumento del metodo getObjets recibe la direccion donde se guarda el archivo Json que es la
         * variable de referencia pathFile y recibe el tipo de dato que va a guardar que es de tipo Paciente
         */
    }//Constructor

    public DoubleLinkedList<Medico> obtenerTodos() {//si funciona
        return listaMedicos; // Retorna la lista de pacientes
    }//obtenerTodos


    public void añadirNuevoMedico(Medico nuevoMedico) {
        listaMedicos.agregarAlFinal(nuevoMedico); // Agrega el nuevo paciente a la lista
        /* Aqui estamos reutilizando el metodo de la lista doble enlazada que nos permite agregar un elemento al final
         * de la lista doble enlazada*/
        jsonAdapterMedicos.writeObjects(pathFile, listaMedicos); // Actualizar el archivo JSON con la lista actualizada de pacientes
    }//añadirNuevoPaciente

    public void eliminarMedico(Medico medico) {
        // Iterar sobre la lista de pacientes para buscar el paciente a eliminar
        int indice = listaMedicos.buscarElemento(medico); // Busca el índice del paciente en la lista
        if (indice != -1) { // Si se encuentra el paciente en la lista
            listaMedicos.eliminarEnMedio(indice); // Elimina el paciente de la lista
            // Actualizar el archivo JSON con la lista actualizada de pacientes
            jsonAdapterMedicos.writeObjects(pathFile, listaMedicos); // Actualizar el archivo JSON con la lista actualizada de pacientes
        } else {
            System.out.println("El paciente no se encontró en la lista."); // Si el paciente no se encuentra en la lista
        }
    }//eliminarPaciente

    public Medico obtenerPorId(String idMedico) {
        DoubleLinkedList<Medico> medicos = obtenerTodos(); // Obtener la lista completa de pacientes
        for (int i = 0; i < medicos.tamano(); i++) { // Recorrer la lista de pacientes
            Medico medico = medicos.buscarPorIndiceIterar(i); // Obtener el paciente en el índice actual
            if (medico.getIdMedico().equals(idMedico)) { // Verificar si el ID del paciente coincide
                return medico; // Retornar el paciente encontrado
            }
        }
        // Si no se encontró ningún paciente con el ID especificado, retornar null
        return null;
    }//obtenerPorId

    //Metodo para buscar medicos segun su especialidad
    public DoubleLinkedList<Medico> buscarPorEspecialidad(String especialidad) {
        DoubleLinkedList<Medico> medicos = obtenerTodos(); // Obtener la lista completa de pacientes
        DoubleLinkedList<Medico> medicosEspecialidad = new DoubleLinkedList<>();
        for (int i = 0; i < medicos.tamano(); i++) { // Recorrer la lista de pacientes
            Medico medico = medicos.buscarPorIndiceIterar(i); // Obtener el paciente en el índice actual
            if (medico.getEspecialidad().name().equals(especialidad)) {
                medicosEspecialidad.agregarAlFinal(medico);
            }
        }
        // Si no se encontró ningún paciente con el ID especificado, retornar null
        return medicosEspecialidad;
    }//obtenerPorEspecialidad



}
