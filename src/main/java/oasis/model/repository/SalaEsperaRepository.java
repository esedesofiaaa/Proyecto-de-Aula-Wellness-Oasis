package oasis.model.repository;

import oasis.estructurasDatos.listas.QueueList;
import oasis.model.domain.cita.Cita;
import oasis.shared.FileJsonAdapter;

public class SalaEsperaRepository {

    private final FileJsonAdapter<Cita> jsonAdapterSalasEspera;
    private final String pathFile;
    private QueueList<Cita> salaDeEspera;

    public SalaEsperaRepository() {
        this.pathFile = "C:\\ProyectoEstructuras\\Proyecto-de-Aula-Wellness-Oasis\\src\\main\\java\\oasis\\dataBase\\SalaDeEspera.Json";
        this.jsonAdapterSalasEspera = FileJsonAdapter.getInstance();
        this.salaDeEspera = jsonAdapterSalasEspera.getObjectsQueue(pathFile, Cita[].class);
    }

    public QueueList<Cita> obtenerTodos() {
        return salaDeEspera;
    }

    public void agregarCitaASalaDeEspera(Cita nuevaCita) {
        salaDeEspera.enqueue(nuevaCita);
        jsonAdapterSalasEspera.writeObjectsQueue(pathFile, salaDeEspera);
    }

    public void eliminarCitaDeSalaDeEspera() {
        salaDeEspera.dequeue(); // Cambiar a dequeue para eliminar la cita
        jsonAdapterSalasEspera.writeObjectsQueue(pathFile, salaDeEspera);
    }

    public void actualizarCitaTomada() {
        Cita cita = salaDeEspera.dequeue(); // Cambiar a dequeue para obtener y eliminar la cita
        cita.setTomado(true);
        jsonAdapterSalasEspera.writeObjectsQueue(pathFile, salaDeEspera);
    }

    public Cita verCitaEnPantalla() {
        return salaDeEspera.peek();
    }

    public Cita obtenerSiguienteCita() {
        return salaDeEspera.peek();
    }
}
