package model.domain.cita;

import estructurasDatos.GeneradorCodigo;
import model.Medico;

/**
 * Clase que representa una cita
 */
public class Cita {
    private String idCita;
    private String idPaciente;
    private boolean pagado;
    private boolean tomado;
    private String costo;
    private String motivoCita;
    private String especialidad;
    private String medico;


    /**
     * Instancia de la clase GeneradorCodigo
     */
    GeneradorCodigo generadorCodigo = new GeneradorCodigo();

    /**
     * Constructor de la clase Cita
     *
     * @param idPaciente   Identificador del paciente
     * @param motivoCita   Motivo de la cita
     * @param especialidad Especialidad de la cita
     * @param medico       Identificador del médico
     * @param pagado       Estado de pago de la cita
     */
    public Cita(String idPaciente, String motivoCita, String especialidad, String medico, boolean pagado) {
        this. idCita = generadorCodigo.generarCodigo(motivoCita.toUpperCase(), especialidad.toUpperCase());
        this.idPaciente = idPaciente;
        this.motivoCita = motivoCita;
        this.especialidad = especialidad;
        this.medico = medico;
        this.pagado = pagado;
        this.tomado = false;
        actualizarCostoCita();
    }

    /**
     * Método para actualizar el costo de la cita según el motivo y la especialidad
     */
    private void actualizarCostoCita() {
        if ("CONTROL".equals(motivoCita)) {
            costo = "Gratis";
        } else if ("VALORACION".equals(motivoCita)) {
            costo = "4500";
            if ("MEDICINA_GENERAL".equals(especialidad)) {
                costo = "2500";
            }
        } else if ("EXAMEN".equals(motivoCita)) {
            costo = "5000";
        }
    }

    // Getters y setters

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

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }



    /**
     * Método toString para obtener la información de la cita
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
                ", medico='" + medico + '\'' +
                '}';
    }
}
