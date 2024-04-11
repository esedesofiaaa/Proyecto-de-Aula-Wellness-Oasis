package model.citas;

import estructurasDatos.GeneradorCodigo;
import model.Medico;

/**
 * Clase que representa una cita
 */
public class Cita {
    private String idCita;
    private String idPaciente;
    private boolean pagado = false;
    private boolean tomado = false;
    private String costo;
    private String motivoCita;
    private String especialidad;
    private Medico medico;

    /**
     * Instancia de la clase GeneradorCodigo
     */
    GeneradorCodigo generadorCodigo = new GeneradorCodigo();

    /**
     * Constructor de la clase Cita
     * @param costo Costo de la cita
     * @param especialidad Especialidad de la cita
     * @param idCita Identificador de la cita
     * @param idPaciente Identificador del paciente
     * @param motivoCita Motivo de la cita
     * @param pagado Estado de pago de la cita
     * @param tomado Estado de toma de la cita
     */
    public Cita(String costo, String especialidad, String idCita, String idPaciente, String motivoCita, boolean pagado, boolean tomado,String medico) {
        this.costo = costo;

        if (motivoCita == "CONTROL") {
            this.costo = "Gratis";
        } else if (motivoCita == "VALORACION" && especialidad != "MEDICINA_GENERAL") {
            this.costo = "4500";
        } else if (motivoCita == "VALORACION" && especialidad == "MEDICINA_GENERAL") {
            this.costo = "2500";
        }


        this.idCita = generadorCodigo.generarCodigo(motivoCita, especialidad);
        this.idPaciente = idPaciente;
        if (motivoCita == "CONTROL" || motivoCita == "VALORACION") {
            this.motivoCita = motivoCita;
        }
        else {
            throw new IllegalArgumentException("Motivo de cita no válido: " + motivoCita);
        }

        this.pagado = pagado;
        this.tomado = tomado;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
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

    /**
     * Método que permite obtener la información de la cita
     * @return Información de la cita
     */
    @Override
    public String toString() {
        return "Cita{" +
                "idCita='" + idCita + '\'' +
                ", idPaciente='" + idPaciente + '\'' +
                ", pagado=" + pagado +
                ", tomado=" + tomado +
                ", costo='" + costo + '\'' +
                ", motivoCita='" + motivoCita + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", medico=" + medico +
                '}';
    }
}
