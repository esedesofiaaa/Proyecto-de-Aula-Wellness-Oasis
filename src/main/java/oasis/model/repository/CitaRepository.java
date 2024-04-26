package oasis.model.repository;
import oasis.estructurasDatos.listas.DoubleLinkedList; //Imoortamos la clase de la lista enlazada doble para poder usarla
import oasis.model.domain.cita.Cita;
import oasis.shared.FileJsonAdapter;

public class CitaRepository {

    private final FileJsonAdapter<Cita> jsonAdapterCita; // Adaptador para manejar la lectura y escritura de objetos Paciente en formato JSON
    private final String pathFile; // Esta variable de referencia guarda la ruta del archivo JSON que contiene los datos de los pacientes
    // pero guarda la direccion dentro del proyecto no dentro del disco
    private DoubleLinkedList<Cita> listaCitas;

    /**
     * Constructor de la clase RegistroExamenRepository
     */
    public CitaRepository() {

        this.pathFile = "src/main/oasis/java/dataBase/Citas.Json"; // Ruta del archivo JSON dentro del proyecto
        this.jsonAdapterCita = FileJsonAdapter.getInstance(); // Obtener una instancia del adaptador JSON para Pacientes
        this.listaCitas = jsonAdapterCita.getObjects(pathFile, Cita[].class);
    }

    /**
     * Método para guardar un nuevo registro de examen en el archivo JSON
     *
     * @param cita Objeto de tipo Cita que se va a guardar en el archivo JSON
     */
    public void guardarCita(Cita cita) {
        listaCitas.agregarAlFinal(cita); // Agregar el nuevo registro de examen a la lista de registros
        jsonAdapterCita.writeObjects(pathFile, listaCitas); // Guardar la lista de registros en el archivo JSON
    }

    //Metodo eliminar cita
    public void eliminarCita(Cita cita) {
        int indice = listaCitas.buscarElemento(cita);
        if (indice != -1) {
            listaCitas.eliminarEnMedio(indice);
            jsonAdapterCita.writeObjects(pathFile, listaCitas);
            System.out.println("Cita eliminada: " + cita.getIdCita());
        } else {
            System.out.println("La cita con id " + cita.getIdCita() + " no existe.");
        }
    }




    //Metodo buscar cita por idPaciente
    public DoubleLinkedList<Cita> buscarCitasPorIdPaciente(String idPaciente) {
        DoubleLinkedList<Cita> citasPaciente = new DoubleLinkedList<>();

        int indice = 0; // Inicializamos el índice en 0 para el primer nodo
        int tamano = listaCitas.tamano(); // Obtenemos el tamaño de la lista

        while (indice < tamano) { // Mientras el índice sea menor que el tamaño de la lista
            Cita cita = listaCitas.buscarPorIndiceIterar(indice); // Obtener el registro en el índice actual
            if (cita != null && cita.getIdPaciente().equals(idPaciente)) { // Verificar si el registro no es nulo y el idPaciente coincide
                citasPaciente.agregarAlFinal(cita); // Agregar el registro a la lista de registros del paciente
            }
            indice++; // Incrementar el índice para pasar al siguiente nodo
        }
        return citasPaciente; // Retornar la lista de registros de examen del paciente
    }

    //Metodo para mostrar la lista de citas
    public DoubleLinkedList<Cita> obtenerTodos() {
        return listaCitas; // Retorna la lista de pacientes
    }


    //metodo de buscar cita por idCita
    public Cita buscarCitaPorId(String idCita) {
        DoubleLinkedList<Cita> citas = obtenerTodos(); // Obtener la lista completa de pacientes
        for (int i = 0; i < citas.tamano(); i++) { // Recorrer la lista de pacientes
            Cita cita = citas.buscarPorIndiceIterar(i); // Obtener el paciente en el índice actual
            if (cita.getIdCita().equals(idCita)) { // Verificar si el ID del paciente coincide
                return cita; // Retornar el paciente encontrado
            }
        }
        // Si no se encontró ningún paciente con el ID especificado, retornar null
        return null;
    }

}
