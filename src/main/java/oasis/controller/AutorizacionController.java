package oasis.controller;

import oasis.estructurasDatos.listas.StackList;
import oasis.model.domain.registroExamen.RegistroExamen;
import oasis.model.repository.AutorizacionRepository;
import oasis.model.repository.RegistroExamenRepository;

public class AutorizacionController {




    private RegistroExamenRepository registroExamenRepository ;

    private AutorizacionRepository autorizacionRepository = new AutorizacionRepository();

    private StackList<RegistroExamen> pilaAutorizaciones = new StackList<>();



    public void agregarPilaAutroizacion (String radicado){
        registroExamenRepository = new RegistroExamenRepository();
        RegistroExamen  prueba = registroExamenRepository.buscarPorRadicadoExamen(radicado);
        if (prueba!= null){
            autorizacionRepository.agregarNuevoRegistroExamenAPila(prueba);
            System.out.println("Examen en espera de ser autorizado: " + prueba.getRadicadoExamen());
        }
    }

    public void autorizarExamen() {

        registroExamenRepository = new RegistroExamenRepository();

        RegistroExamen examen = obtenerTodos().peek();
        //examen.setAutorizado(true);
        //examen.setNota("AUTORIZADO");
        RegistroExamen examenFinal = examen;
        examenFinal.setAutorizado(true);
        examenFinal.setNota("AUTORIZADO");

        registroExamenRepository.eliminarExamen(registroExamenRepository.buscarPorRadicadoExamen(examen.getRadicadoExamen()));

        registroExamenRepository.añadirNuevoRegistroExamen( autorizacionRepository.buscarPorAutorizado(true));
        autorizacionRepository.eliminarRegistroExamenPila();
    }

    public StackList<RegistroExamen> obtenerTodos() {
        return autorizacionRepository.obtenerTodos();
    }

    public void noAutorizarExamen() {
        registroExamenRepository = new RegistroExamenRepository();

        RegistroExamen examen = obtenerTodos().peek();
        //examen.setAutorizado(true);
        //examen.setNota("AUTORIZADO");
        RegistroExamen examenFinal = examen;
        examenFinal.setAutorizado(false);
        examenFinal.setNota("AUTORIZACION NEGADA");

        registroExamenRepository.eliminarExamen(registroExamenRepository.buscarPorRadicadoExamen(examen.getRadicadoExamen()));

        registroExamenRepository.añadirNuevoRegistroExamen( autorizacionRepository.buscarPorAutorizado(true));
        autorizacionRepository.eliminarRegistroExamenPila();
    }

    // implementa este metodo del repositorio controlParaNoRepetirExamenParaAutorizar
    public boolean controlParaNoRepetirExamenParaAutorizar(String radicado) {
        return autorizacionRepository.controlParaNoRepetirExamenParaAutorizar( radicado);
    }

    //Metodo que solo  haga un peek
    public RegistroExamen peek(){
        return autorizacionRepository.obtenerTodos().peek();
    }
}