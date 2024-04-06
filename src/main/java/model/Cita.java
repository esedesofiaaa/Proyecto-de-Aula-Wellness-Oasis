package model;

public class Cita {

    private String idPaciente;
    private Especialidad especialidad;
    private String profesionalAsignado;
    private  MotivoCita motivoCita;
    private boolean pagado = false;


    public Cita(String idPaciente, Especialidad especialidad, String profesionalAsignado, MotivoCita motivoCita, boolean pagado) {
        this.idPaciente = idPaciente;
        this.especialidad = especialidad;
        this.profesionalAsignado = profesionalAsignado;
        this.motivoCita = motivoCita;
        this.pagado = pagado;
    }


    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getProfesionalAsignado() {
        return profesionalAsignado;
    }

    public void setProfesionalAsignado(String profesionalAsignado) {
        this.profesionalAsignado = profesionalAsignado;
    }

    public MotivoCita getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(MotivoCita motivoCita) {
        this.motivoCita = motivoCita;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }


    //Para poder modificar el Especialista


    @Override
    public String toString() {
        return "Cita{" +
                "idPaciente='" + idPaciente + '\'' +
                ", especialidad=" + especialidad +
                ", profesionalAsignado='" + profesionalAsignado + '\'' +
                ", motivoCita=" + motivoCita +
                ", pagado=" + pagado +
                '}';
    }
}
