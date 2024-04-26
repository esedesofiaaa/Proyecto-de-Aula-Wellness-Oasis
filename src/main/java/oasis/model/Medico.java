package oasis.model;

public class Medico {

        private String nombre;
        private Especialidad especialidad;
        private String idMedico;

        public Medico(String nombre, Especialidad especialidad, String idMedico) {
            this.nombre = nombre;
            this.especialidad = especialidad;
            this.idMedico = idMedico;
        }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "nombre='" + nombre + '\'' +
                ", especialidad=" + especialidad +
                ", idMedico='" + idMedico + '\'' +
                '}';
    }
}
