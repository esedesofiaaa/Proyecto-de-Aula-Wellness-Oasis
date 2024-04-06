package model.repository;

import estructurasDatos.listas.StackList;
import model.RegistroExamen;
import shared.FileJsonAdapter;

public class AutorizacionRepository {

    private final FileJsonAdapter<RegistroExamen> jsonAdapterAutorizacion; // Adaptador para manejar la lectura y escritura de objetos Paciente en formato JSON

    private final String pathFile; // Esta variable de referencia guarda la ruta del archivo JSON que contiene los datos de los pacientes
    // pero guarda la direccion dentro del proyecto no dentro del disco
    private StackList<RegistroExamen> examenesPorAutorizar;


    /**
     * Constructor de la clase RegistroExamenRepository
     */
    public AutorizacionRepository() {
        this.pathFile = "src/main/java/dataBase/Autorizacion.Json"; // Ruta del archivo JSON dentro del proyecto
        this.jsonAdapterAutorizacion = FileJsonAdapter.getInstance(); // Obtener una instancia del adaptador JSON para Pacientes
        this.examenesPorAutorizar = jsonAdapterAutorizacion.getObjectsStack(pathFile, RegistroExamen[].class);

    }

    public StackList<RegistroExamen> obtenerTodos() {
        return examenesPorAutorizar; // Retorna la lista de pacientes
    }


    public void agregarNuevoRegistroExamenAPila(RegistroExamen nuevoRegistroExamen) {
        examenesPorAutorizar.push(nuevoRegistroExamen); // Agrega el nuevo paciente a la lista
        jsonAdapterAutorizacion.writeObjectsStack(pathFile, examenesPorAutorizar); // Actualizar el archivo JSON con la lista actualizada de pacientes
    }

    //Hacer pop
    public void eliminarRegistroExamenPila() {
        examenesPorAutorizar.pop();
        jsonAdapterAutorizacion.writeObjectsStack(pathFile, examenesPorAutorizar);
    }

    //Metodo para cambiar el boolean autorizado a true
    public void actualizarAutorizacionExamen() {
        RegistroExamen examen = examenesPorAutorizar.peek();
        examen.setAutorizado(true);
        jsonAdapterAutorizacion.writeObjectsStack(pathFile, examenesPorAutorizar);
    }
}