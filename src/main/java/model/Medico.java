package model;

public class Medico {

        private String nombre;
        private Especialidad especialidad;
        private String id;

        public Medico(String nombre, Especialidad especialidad, String id) {
            this.nombre = nombre;
            this.especialidad = especialidad;
            this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
