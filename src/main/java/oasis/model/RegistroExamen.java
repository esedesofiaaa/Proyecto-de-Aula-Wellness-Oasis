package oasis.model;

import oasis.estructurasDatos.GeneradorCodigo;
import oasis.model.domain.motivoCita.Examen;

public class RegistroExamen {
    private String radicadoExamen;
    private Examen motivoCitaExamen;
    //revisar lo de autorizacion, si lo pongo en esta fase o en cual
    private boolean autorizado;
    private GeneradorCodigo generadorCodigo = new GeneradorCodigo();// Inicializar la instancia de GeneradorCodigo
    private String idPaciente;
    private String nota;

    public RegistroExamen( Examen motivoCitaExamen, String idPaciente ) {
        this.radicadoExamen = generadorCodigo.generarIdentificador();
        this.autorizado = false;
        this.idPaciente = idPaciente;
        this.motivoCitaExamen = motivoCitaExamen;
        this.nota = "POR AUTORIZAR";
    }

    public String getRadicadoExamen() {
        return radicadoExamen;
    }

    public void setRadicadoExamen(String radicadoExamen) {
        this.radicadoExamen = radicadoExamen;
    }

    public Examen getMotivoCitaExamen() {
        return motivoCitaExamen;
    }

    public void setMotivoCitaExamen(Examen motivoCitaExamen) {
        this.motivoCitaExamen = motivoCitaExamen;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "RegistroExamen{" +
                "radicadoExamen='" + radicadoExamen + '\'' +
                ", motivoCitaExamen=" + motivoCitaExamen +
                ", autorizado=" + autorizado +
                ", idPaciente='" + idPaciente + '\'' +
                ", nota='" + nota + '\'' +
                '}';
    }
}
