package model.citas;

import model.MotivoCita.Control;

public class CitaControl extends Cita{

    private Control motivoCitaControl;

    public CitaControl(String idPaciente, boolean pagado, Control motivoCitaControl) {
        super(idPaciente, pagado);
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
        return "CitaControl{" +
                "Control=" + motivoCitaControl +
                "costo=" + motivoCitaControl.getCosto() +
                ", profesionalAsignado=" + motivoCitaControl.getProfesionalAsignado() +
                '}';
    }
}
