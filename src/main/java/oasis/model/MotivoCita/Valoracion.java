package oasis.model.MotivoCita;

public enum Valoracion {
    PEDIATRIA("PEDIATRIA", "4500", "Dr. Lopez"),
    CARDIOLOGIA( "CARDIOLOGIA","4500", "Dr. Perez"),
    DERMATOLOGIA("DERMATOLOGIA","4500", "Dr. Silva"),
    PSIQUIATRIA("PSIQUIATRIA","4500", "Dr. Fuentes"),
    NUTRICION("NUTRICION","4500", "Dr. Camargo"),
    ODONTOLOGIA("ODONTOLOGIA","4500", "Dr. Bueno"),
    MEDICINA_GENERAL("MEDICINA_GENERAL","2500", "Dr. Gamboa");

    private final String especialidad;
    private final String costo;
    private final String profesionalAsignado;

    Valoracion(String especialidad, String costo, String profesionalAsignado) {
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
    @Override
    public String toString() {
        return " Valoracion" +
                ", especialidad='" + especialidad + '\'' +
                ", costo='" + costo + '\'' +
                ", profesionalAsignado='" + profesionalAsignado;
    }
}


