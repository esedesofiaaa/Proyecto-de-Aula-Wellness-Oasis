package model.repository;

import estructurasDatos.listas.DoubleLinkedList;
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
        examen.setNota("AUTORIZADO");

        jsonAdapterAutorizacion.writeObjectsStack(pathFile, examenesPorAutorizar);
    }

    /**
     * Método para buscar los registros de examen relacionados a un idPaciente específico
     *
     * @param autorizado El estado de autorización del examen
     * @return Una lista de los registros de examen relacionados al idPaciente dado
     */
    public RegistroExamen buscarPorAutorizado(boolean autorizado) {
        RegistroExamen registroAutorizado = null;

        // Crear una lista temporal para almacenar los elementos de la pila
        DoubleLinkedList<RegistroExamen> tempList = new DoubleLinkedList<>();

        // Transferir elementos de la pila a la lista temporal
        while (!examenesPorAutorizar.estaVacia()) {
            tempList.agregarAlInicio(examenesPorAutorizar.pop()); // Agregar al inicio para mantener el orden
        }

        // Iterar sobre la lista temporal y buscar el primer elemento autorizado
        for (int i = 0; i < tempList.tamano(); i++) {
            RegistroExamen registro = tempList.buscarPorIndiceIterar(i);
            if (registro != null && registro.isAutorizado() == autorizado) {
                registroAutorizado = registro;
                break; // Detener la iteración al encontrar el primer elemento autorizado
            }
        }

        // Transferir los elementos nuevamente a la pila original
        while (!tempList.estaVacia()) {
            examenesPorAutorizar.push(tempList.eliminarDelInicio()); // Eliminar del inicio para mantener el orden
        }

        return registroAutorizado; // Retornar el registro de examen autorizado encontrado (o null si no hay ninguno)
    }


}