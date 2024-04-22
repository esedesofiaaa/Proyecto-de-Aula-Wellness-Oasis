package model.domain.cita;

import model.MotivoCita.Examen;
import model.RegistroExamen;
import model.RegistroExamen;
import model.domain.cita.Cita;

public class CitaExamen extends Cita {



    private String radicadoExamen;

    private boolean autorizado;

    public CitaExamen(String idPaciente, String motivoCita, String especialidad, String medico, boolean pagado, String radicadoExamen, boolean autorizado) {
        super(idPaciente, motivoCita, especialidad, medico, pagado);
        this.radicadoExamen = radicadoExamen;
        this.autorizado = autorizado;
    }

    public String getRadicadoExamen() {
        return radicadoExamen;
    }

    public void setRadicadoExamen(String radicadoExamen) {
        this.radicadoExamen = radicadoExamen;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    @Override
    public String toString() {
        return "CitaExamen{" +
                "radicadoExamen='" + radicadoExamen + '\'' +
                ", autorizado=" + autorizado +
                '}';
    }
}
