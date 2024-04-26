package oasis.controller;

import oasis.estructurasDatos.interfaces.StackInterface;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.estructurasDatos.listas.StackList;
import oasis.model.RegistroExamen;
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
        autorizacionRepository.actualizarAutorizacionExamen();
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
        RegistroExamen examen = pilaAutorizaciones.peek();
        examen.setAutorizado(false);
        autorizacionRepository.eliminarRegistroExamenPila();
    }
}