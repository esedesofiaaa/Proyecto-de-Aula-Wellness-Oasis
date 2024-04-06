package model.citas;
public class Cita {

    private String idPaciente;

    private boolean pagado = false;

    private boolean tomado = false;


    public Cita() {
    }

    public Cita(String idPaciente, boolean pagado, boolean tomado) {
        this.idPaciente = idPaciente;
        this.tomado = tomado;
        this.pagado = pagado;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public boolean isTomado() {
        return tomado;
    }

    public void setTomado(boolean tomado) {
        this.tomado = tomado;
    }


    @Override
    public String toString() {
        return "Cita{" +
                "idPaciente='" + idPaciente + '\'' +
                ", pagado=" + pagado +
                ", tomado=" + tomado;
    }
}
