package model;

import estructurasDatos.GeneradorCodigo;
import model.MotivoCita.Examen;

public class RegistroExamen {
    private String radicadoExamen;
    private Examen motivoCitaExamen;
    //revisar lo de autorizacion, si lo pongo en esta fase o en cual
    private boolean autorizado;
    private GeneradorCodigo generadorCodigo = new GeneradorCodigo();// Inicializar la instancia de GeneradorCodigo
    private String idPaciente;

    public RegistroExamen( Examen motivoCitaExamen, String idPaciente, boolean autorizado) {
        this.radicadoExamen = generadorCodigo.generarIdentificador();
        this.autorizado = autorizado;
        this.idPaciente = idPaciente;
        this.motivoCitaExamen = motivoCitaExamen;
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

    @Override
    public String toString() {
        return "RegistroExamen{" +
                "radicadoExamen='" + radicadoExamen + '\'' +
                ", motivoCitaExamen=" + motivoCitaExamen +
                ", autorizado=" + autorizado +
                ", generadorCodigo=" + generadorCodigo +
                ", idPaciente='" + idPaciente + '\'' +
                '}';
    }
}
