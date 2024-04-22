package model.repository;
import estructurasDatos.listas.DoubleLinkedList; //Imoortamos la clase de la lista enlazada doble para poder usarla
import model.RegistroExamen;
import shared.FileJsonAdapter;

public class    RegistroExamenRepository {

    private final FileJsonAdapter<RegistroExamen> jsonAdapterRegistroExamen; // Adaptador para manejar la lectura y escritura de objetos Paciente en formato JSON
    private String pathFile; // Esta variable de referencia guarda la ruta del archivo JSON que contiene los datos de los pacientes
    // pero guarda la direccion dentro del proyecto no dentro del disco
    private DoubleLinkedList<RegistroExamen> registroExamenes; //Lista enlazada que almacena los objetos de tipo Paciente, en esta caso usamos una lista

    private final AutorizacionRepository autorizacionRepository; //Instancia de la clase AutorizacionRepository
    //doble enlazada de nuestras estructuras de datos
    /**
     * Constructor de la clase RegistroExamenRepository
     */
   public RegistroExamenRepository(){
       this.autorizacionRepository = new AutorizacionRepository();
       this.pathFile = "src/main/java/dataBase/RegistroExamen.Json"; // Ruta del archivo JSON dentro del proyecto
       this.jsonAdapterRegistroExamen = FileJsonAdapter.getInstance(); // Obtener una instancia del adaptador JSON para Pacientes
       this.registroExamenes = jsonAdapterRegistroExamen.getObjects(pathFile, RegistroExamen[].class);

   }

    /**
     * Método para obtener todos los registros de examenes
     * @return lista de registros de examenes
     */

    public DoubleLinkedList<RegistroExamen> obtenerTodos() {
        return registroExamenes; // Retorna la lista de pacientes
    }

    /**
     * Método para añadir un nuevo registro de examen
     * @param nuevoRegistroExamen
     */
    public void añadirNuevoRegistroExamen(RegistroExamen nuevoRegistroExamen) {
        registroExamenes.agregarAlFinal(nuevoRegistroExamen); // Agrega el nuevo paciente a la lista
        jsonAdapterRegistroExamen.writeObjects(pathFile, registroExamenes); // Actualizar el archivo JSON con la lista actualizada de pacientes
    }

    /**
     * Método para buscar los registros de examen relacionados a un idPaciente específico
     * @param idPaciente El id del paciente para el cual se desea buscar los registros de examen
     * @return Una lista de los registros de examen relacionados al idPaciente dado
     */
    public DoubleLinkedList<RegistroExamen> buscarPorIdPaciente(String idPaciente) {
        DoubleLinkedList<RegistroExamen> registrosPaciente = new DoubleLinkedList<>();

        int indice = 0; // Inicializamos el índice en 0 para el primer nodo
        int tamano = registroExamenes.tamano(); // Obtenemos el tamaño de la lista

        while (indice < tamano) { // Mientras el índice sea menor que el tamaño de la lista
            RegistroExamen registro = registroExamenes.buscarPorIndiceIterar(indice); // Obtener el registro en el índice actual
            if (registro != null && registro.getIdPaciente().equals(idPaciente)) { // Verificar si el registro no es nulo y el idPaciente coincide
                registrosPaciente.agregarAlFinal(registro); // Agregar el registro a la lista de registros del paciente
            }
            indice++; // Avanzar al siguiente índice
        }

        return registrosPaciente;
    }

    //Metodo para buscar un examen por su radicadoExamen
    public RegistroExamen buscarPorRadicadoExamen(String radicadoExamen) {
        RegistroExamen registroExamen = null;
        int indice = 0; // Inicializamos el índice en 0 para el primer nodo
        int tamano = registroExamenes.tamano(); // Obtenemos el tamaño de la lista

        while (indice < tamano) { // Mientras el índice sea menor que el tamaño de la lista
            RegistroExamen registro = registroExamenes.buscarPorIndiceIterar(indice); // Obtener el registro en el índice actual
            if (registro != null && registro.getRadicadoExamen().equals(radicadoExamen)) { // Verificar si el registro no es nulo y el radicadoExamen coincide
                registroExamen = registro; // Asignar el registro encontrado a la variable de retorno
                break; // Salir del bucle
            }
            indice++; // Avanzar al siguiente índice
        }

        return registroExamen;
    }

    //Metodo para buscar un examen
    public RegistroExamen buscarExamen(String radicadoExamen) {
        RegistroExamen registroExamen = null;
        int indice = 0; // Inicializamos el índice en 0 para el primer nodo
        int tamano = registroExamenes.tamano(); // Obtenemos el tamaño de la lista

        while (indice < tamano) { // Mientras el índice sea menor que el tamaño de la lista
            RegistroExamen registro = registroExamenes.buscarPorIndiceIterar(indice); // Obtener el registro en el índice actual
            if (registro != null && registro.getRadicadoExamen().equals(radicadoExamen)) { // Verificar si el registro no es nulo y el radicadoExamen coincide
                registroExamen = registro; // Asignar el registro encontrado a la variable de retorno
                break; // Salir del bucle
            }
            indice++; // Avanzar al siguiente índice
        }

        return registroExamen;
    }

//Eliminar un registro de examen
    public void eliminarExamen(RegistroExamen registroExamen) {
        // Iterar sobre la lista de pacientes para buscar el paciente a eliminar
        int indice = registroExamenes.buscarElemento(registroExamen); // Busca el índice del paciente en la lista
        if (indice != -1) { // Si se encuentra el paciente en la lista
            registroExamenes.eliminarEnMedio(indice); // Elimina el paciente de la lista
            // Actualizar el archivo JSON con la lista actualizada de pacientes
            jsonAdapterRegistroExamen.writeObjects(pathFile, registroExamenes);
        } else {
            System.out.println("El registro de examen no se encontró en la lista."); // Si el paciente no se encuentra en la lista
        }
    }




    //Metodo para cambiar el boolean autorizado a true
    /*public void actualizarAutorizacionExamen() {
        RegistroExamen examen = registroExamenes.peek();
        examen.setAutorizado(true);
        jsonAdapterRegistroExamen.writeObjects(pathFile, registroExamenes);
    }*/
    //Metodo para actualizar un registro de examen

    public void actualizarAutorizacionExamen(RegistroExamen registroExamen){
        int indice = registroExamenes.buscarElemento(registroExamen); // Busca el índice del paciente en la lista
        if (indice != -1) { // Si se encuentra el paciente en la lista
            registroExamenes.eliminarEnMedio(indice); // Elimina el paciente de la lista
            // Actualizar el archivo JSON con la lista actualizada de pacientes
            jsonAdapterRegistroExamen.writeObjects(pathFile, registroExamenes);
        } else {
            System.out.println("El registro de examen no se encontró en la lista."); // Si el paciente no se encuentra en la lista
        }
    }



}

