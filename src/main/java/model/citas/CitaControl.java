package model.citas;
import model.MotivoCita.Control;
public class CitaControl extends Cita{

    private Control motivoCitaControl;

    public CitaControl(String idPaciente, boolean pagado, boolean tomado, Control motivoCitaControl) {
        super(idPaciente, pagado, tomado);
        this.motivoCitaControl = motivoCitaControl;
    }

    public Control getMotivoCitaControl() {
        return motivoCitaControl;
    }

    public void setMotivoCitaControl(Control motivoCitaControl) {
        this.motivoCitaControl = motivoCitaControl;
    }

    @Override
    public String toString() {
        return  "idPaciente='" + getIdPaciente() + '\'' +
                ", pagado=" + isPagado() +
                ", tomado=" + isTomado() +
                ", motivo Cita=" + motivoCitaControl +
                '}';
    }
}
