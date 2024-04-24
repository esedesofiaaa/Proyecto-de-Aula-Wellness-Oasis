package model.repository;

import estructurasDatos.listas.DoubleLinkedList;
import estructurasDatos.listas.QueueList;
import model.domain.cita.Cita;
import shared.FileJsonAdapter;

public class SalaEsperaRepository {

    private final FileJsonAdapter<Cita>jsonAdapterSalasEspera;
    private final String pathFile; // Esta variable de referencia guarda la ruta del archivo JSON que contiene los datos de los pacientes
    // pero guarda la direccion dentro del proyecto no dentro del disco
    private QueueList<Cita> salaDeEspera;

    /**
     * Constructor de la clase RegistroExamenRepository
     */
    public SalaEsperaRepository() {
        this.pathFile = "src/main/java/dataBase/SalaDeEspera.Json"; // Ruta del archivo JSON dentro del proyecto
        this.jsonAdapterSalasEspera = FileJsonAdapter.getInstance(); // Obtener una instancia del adaptador JSON para Pacientes
        this.salaDeEspera = jsonAdapterSalasEspera.getObjectsQueue(pathFile, Cita[].class);
    }

    /**
     * Método para guardar un nuevo registro de examen en el archivo JSON
     */
    public QueueList<Cita> obtenerTodos() {
        return salaDeEspera; // Retorna la lista de pacientes
    }

    /**
     * Método para agregar un nuevo registro de examen a la lista de registros de examen
     * @param nuevaCita Objeto de tipo Cita que se va a agregar a la lista de registros de examen
     */
    public void agregarCitaASalaDeEspera(Cita nuevaCita) {
        salaDeEspera.enqueue(nuevaCita); // Agrega el nuevo paciente a la lista
        jsonAdapterSalasEspera.writeObjectsQueue(pathFile, salaDeEspera); // Actualizar el archivo JSON con la lista actualizada de pacientes
    }

    /**
     * Método para eliminar un registro de examen de la lista de registros de examen
     */
    public void eliminarCitaDeSalaDeEspera() {
        salaDeEspera.dequeue();
        jsonAdapterSalasEspera.writeObjectsQueue(pathFile, salaDeEspera);
    }

    /*
    * Metodo para cambiar el booleano de tomado a true
    * Sofia mmhvo, este metodo tiene que hacer dequeue en vez de peek
    * pero primero debes termiar el repositry de Final.Json
    * Cuando esto haga dequeue lo envia a el Json de Final con el boleen de tomado en true
     */
    public void actualizarCitaTomada() {
        Cita cita = salaDeEspera.peek();
        cita.setTomado(true);
        jsonAdapterSalasEspera.writeObjectsQueue(pathFile, salaDeEspera);
    }

//Metodo para ver la cita en pantalla
    public Cita verCitaEnPantalla() {
        return salaDeEspera.peek();
    }

}


