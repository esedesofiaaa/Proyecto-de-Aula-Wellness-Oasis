package model.MotivoCita;

public enum Control {
    PEDIATRIA("PEDIATRIA", "Gratis", "Dr. Lopez"),
    CARDIOLOGIA( "CARDIOLOGIA","Gratis", "Dr. Perez"),
    DERMATOLOGIA("DERMATOLOGIA","Gratis", "Dr. Silva"),
    PSIQUIATRIA("PSIQUIATRIA","Gratis", "Dr. Fuentes"),
    NUTRICION("NUTRICION","Gratis", "Dr. Camargo"),
    ODONTOLOGIA("ODONTOLOGIA","Gratis", "Dr. Bueno"),
    MEDICINA_GENERAL("MEDICINA_GENERAL","Gratis", "Dr. Gamboa");

    private final String especialidad;
    private final String costo;
    private final String profesionalAsignado;

    Control(String especialidad, String costo, String profesionalAsignado) {
        this.especialidad = especialidad;
        this.costo = costo;
        this.profesionalAsignado = profesionalAsignado;
    }

    public String getCosto() {
        return costo;
    }

    public String getProfesionalAsignado() {
        return profesionalAsignado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return " Control" +
                ", especialidad='" + especialidad + '\'' +
                ", costo='" + costo + '\'' +
                ", profesionalAsignado='" + profesionalAsignado;
    }
}
