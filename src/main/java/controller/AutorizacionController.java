package controller;

import estructurasDatos.interfaces.StackInterface;
import estructurasDatos.listas.DoubleLinkedList;
import estructurasDatos.listas.StackList;
import model.RegistroExamen;
import model.repository.AutorizacionRepository;
import model.repository.RegistroExamenRepository;

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
        registroExamenRepository.añadirNuevoRegistroExamen(registroExamenRepository.buscarPorRadicadoExamen(examenFinal.getRadicadoExamen()));
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