package model.citas;

import model.MotivoCita.Examen;

public class CitaExamen extends Cita {


    private Examen motivoCitaExamen;

    public CitaExamen(String idPaciente, boolean pagado, Examen motivoCitaExamen) {
        super(idPaciente, pagado);
        this.motivoCitaExamen = motivoCitaExamen;
    }

    public Examen getMotivoCitaExamen() {
        return motivoCitaExamen;
    }

    public void setMotivoCitaExamen(Examen motivoCitaExamen) {
        this.motivoCitaExamen = motivoCitaExamen;
    }


    @Override
    public String toString() {
        return  "idPaciente='" + getIdPaciente() + '\'' +
                ", pagado=" + isPagado() +
                ", tomado=" + isTomado() +
                ", motivo Cita=" + motivoCitaExamen +
                '}';
    }
}
