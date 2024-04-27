package oasis.controller;

import oasis.model.domain.cita.Cita;
import oasis.model.repository.FinalRepository;

public class FinalController {

    private final FinalRepository finalRepository;



    public FinalController() {
        this.finalRepository = new FinalRepository();
    }

    public void agregarCitaAFinal(Cita cita){
        finalRepository.agregarCitaAFinal(cita);
    }
}
