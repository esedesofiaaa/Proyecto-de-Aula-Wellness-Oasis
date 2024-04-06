package model.citas;

import model.MotivoCita.Examen;

public class CitaExamen extends Cita {


    private Examen motivoCitaExamen;
    //private boolean autorizado;

    public CitaExamen(String idPaciente, boolean pagado, boolean tomado,boolean autorizado, Examen motivoCitaExamen) {
        super(idPaciente, pagado, tomado);
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
