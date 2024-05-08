package oasis.model.repository;

import oasis.estructurasDatos.listas.QueueList;
import oasis.model.domain.cita.Cita;
import oasis.shared.FileJsonAdapter;

public class SalaEsperaRepository {

    private final FileJsonAdapter<Cita> jsonAdapterSalasEspera;
    private final String pathFile;
    private QueueList<Cita> salaDeEspera;
    private final FinalRepository finalRepository;

    public SalaEsperaRepository() {
        this.pathFile = "C:\\ProyectoEstructuras\\Proyecto-de-Aula-Wellness-Oasis\\src\\main\\java\\oasis\\dataBase\\SalaDeEspera.Json";
        this.jsonAdapterSalasEspera = FileJsonAdapter.getInstance();
        this.salaDeEspera = jsonAdapterSalasEspera.getObjectsQueue(pathFile, Cita[].class);
        this.finalRepository = new FinalRepository();
    }

    public QueueList<Cita> obtenerTodos() {
        return salaDeEspera;
    }

    public void agregarCitaASalaDeEspera(Cita nuevaCita) {
        salaDeEspera.enqueue(nuevaCita);
        jsonAdapterSalasEspera.writeObjectsQueue(pathFile, salaDeEspera);
    }

    public void actualizarCitaTomada() {
        Cita cita = salaDeEspera.dequeue(); // Cambiar a dequeue para obtener y eliminar la cita
        cita.setTomado(true);
        jsonAdapterSalasEspera.writeObjectsQueue(pathFile, salaDeEspera);
    }


    public Cita obtenerSiguienteCita() {
        Cita citaBorrar = salaDeEspera.dequeue();
        jsonAdapterSalasEspera.writeObjectsQueue(pathFile, salaDeEspera);
        citaBorrar.setTomado(true);
        finalRepository.agregarCitaAFinal(citaBorrar);

        return citaBorrar ;
    }
}
