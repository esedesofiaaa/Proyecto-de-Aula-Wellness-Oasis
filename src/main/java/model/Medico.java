package model;

public class Medico {
    /* Esta clase representa la abstraccion de un medico, solo lo limitamos al nombre
     * identificacion, que seria la cedula, la especialidad del medico para poder tener
     * claro la asignacion de las citas dependiendo de la especialidad y el correo, creemos
     * que en una clinca un paciente no debe saber mas del medico por privacidad del trabajador */
    
    // Atributos de la clase Medico, 
    private String nombre;
    private String identificacion;
    private Especialidad especialidad;
    private String correo;

    // Constructor
    public Medico(String nombre, String identificacion, Especialidad especialidad, String correo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.especialidad = especialidad;
        this.correo = correo;
    }// Constructor

    // Getters y Setters: metodos de acceso
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }// Getters y Setters
}