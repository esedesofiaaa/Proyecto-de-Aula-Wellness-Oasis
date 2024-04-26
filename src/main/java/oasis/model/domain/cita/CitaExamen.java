package oasis.model.domain.cita;

import oasis.model.MotivoCita.Examen;
import oasis.model.RegistroExamen;
import oasis.model.RegistroExamen;
import oasis.model.domain.cita.Cita;

public class CitaExamen extends Cita {



    private String radicadoExamen;

    private boolean autorizado;


    public CitaExamen(String idPaciente, String especialidad, String medico, boolean pagado, String radicadoExamen, boolean autorizado) {
        super(idPaciente, "EXAMEN", especialidad, medico, radicadoExamen,autorizado,pagado);
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
                "Motivo : Examen =" + '\'' +
                "radicadoExamen='" + radicadoExamen + '\'' +
                ", autorizado=" + autorizado +
                '}';
    }
}
