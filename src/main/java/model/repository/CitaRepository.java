package model.repository;
import estructurasDatos.listas.DoubleLinkedList; //Imoortamos la clase de la lista enlazada doble para poder usarla
import model.RegistroExamen;
import model.citas.Cita;
import shared.FileJsonAdapter;

public class CitaRepository {

    private final FileJsonAdapter<Cita> jsonAdapterCita; // Adaptador para manejar la lectura y escritura de objetos Paciente en formato JSON
    private final String pathFile; // Esta variable de referencia guarda la ruta del archivo JSON que contiene los datos de los pacientes
    // pero guarda la direccion dentro del proyecto no dentro del disco
    private DoubleLinkedList<Cita> historialCitas;

    /**
     * Constructor de la clase RegistroExamenRepository
     */
    public CitaRepository() {

        this.pathFile = "src/main/java/dataBase/HistorialCitas.Json"; // Ruta del archivo JSON dentro del proyecto
        this.jsonAdapterCita = FileJsonAdapter.getInstance(); // Obtener una instancia del adaptador JSON para Pacientes
        this.historialCitas = jsonAdapterCita.getObjects(pathFile, Cita[].class);
    }

    /**
     * Método para guardar un nuevo registro de examen en el archivo JSON
     * @param cita Objeto de tipo Cita que se va a guardar en el archivo JSON
     */
    public void guardarCita(Cita cita) {
        historialCitas.agregarAlFinal(cita); // Agregar el nuevo registro de examen a la lista de registros
        jsonAdapterCita.writeObjects(pathFile, historialCitas); // Guardar la lista de registros en el archivo JSON
    }

    //Metodo eliminar cita
    public void eliminarCita(Cita cita) {
        // Iterar sobre la lista de pacientes para buscar el paciente a eliminar
        int indice = historialCitas.buscarElemento(cita); // Busca el índice del paciente en la lista
        if (indice != -1) { // Si se encuentra el paciente en la lista
            historialCitas.eliminarEnMedio(indice); // Elimina el paciente de la lista
            // Actualizar el archivo JSON con la lista actualizada de pacientes
            jsonAdapterCita.writeObjects(pathFile, historialCitas); // Guardar la lista de registros en el archivo JSON
        } else {
            System.out.println("El registro de examen no se encontró en la lista."); // Si el paciente no se encuentra en la lista
        }
    }


    //Metodo buscar cita por idPaciente
    public DoubleLinkedList<Cita> buscarCitaPorIdPaciente(String idPaciente) {
        DoubleLinkedList<Cita> citasPaciente = new DoubleLinkedList<>();

        int indice = 0; // Inicializamos el índice en 0 para el primer nodo
        int tamano = historialCitas.tamano(); // Obtenemos el tamaño de la lista

        while (indice < tamano) { // Mientras el índice sea menor que el tamaño de la lista
            Cita cita = historialCitas.buscarPorIndiceIterar(indice); // Obtener el registro en el índice actual
            if (cita != null && cita.getIdPaciente().equals(idPaciente)) { // Verificar si el registro no es nulo y el idPaciente coincide
                citasPaciente.agregarAlFinal(cita); // Agregar el registro a la lista de registros del paciente
            }
            indice++; // Incrementar el índice para pasar al siguiente nodo
        }

        return citasPaciente; // Retornar la lista de registros de examen del paciente
    }


}
