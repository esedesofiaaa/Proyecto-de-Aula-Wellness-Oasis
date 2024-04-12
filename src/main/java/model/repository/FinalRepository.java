package model.repository;

import estructurasDatos.listas.DoubleLinkedList;
import model.domain.cita.Cita;
import shared.FileJsonAdapter;

public class FinalRepository {

    private final FileJsonAdapter<Cita> jsonAdapterFinal;

    private final String pathFile;

    private DoubleLinkedList<Cita> finalCitas;



    public FinalRepository() {
        this.pathFile = "src/main/java/dataBase/Final.Json";
        this.jsonAdapterFinal = FileJsonAdapter.getInstance();
        this.finalCitas = jsonAdapterFinal.getObjects(pathFile, Cita[].class);
    }

    public DoubleLinkedList<Cita> obtenerTodos() {
        return finalCitas;
    }

    public void agregarCitaAFinal(Cita nuevaCita) {
        finalCitas.agregarAlFinal(nuevaCita);
        jsonAdapterFinal.writeObjects(pathFile, finalCitas);
    }




}
