package model.citas;

public class CitaExamen extends Cita {

    private String motivoCitaExamen;

    public CitaExamen(String idPaciente, boolean pagado, boolean tomado, String motivoCitaExamen) {
        super(idPaciente, pagado, tomado);
        this.motivoCitaExamen = motivoCitaExamen;
    }

    public String getMotivoCitaExamen() {
        return motivoCitaExamen;
    }

    public void setMotivoCitaExamen(String motivoCitaExamen) {
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
