package model.citas;
public class Cita {

    private String idPaciente;

    private boolean pagado;

    private boolean tomado;


    public Cita() {
    }

    public Cita(String idPaciente, boolean pagado) {
        this.idPaciente = idPaciente;
        this.tomado = false;
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
