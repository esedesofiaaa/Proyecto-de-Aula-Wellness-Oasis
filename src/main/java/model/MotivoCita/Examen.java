package model.MotivoCita;

public enum Examen {
    EXAMEN_FISICO_INFANTIL("Examen físico infantil", "PEDIATRIA", "6000", "Dr. Lopez", "Evaluación exhaustiva de la salud y desarrollo del niño, incluyendo mediciones de peso, altura, perímetro cefálico, y examen de sistemas como cardiovascular, respiratorio, digestivo, etc."),
    ECOGRAFIA_ABDOMINAL("Ecografía abdominal", "PEDIATRIA", "6000", "Dr. Lopez", "Imagen por ultrasonido del abdomen para evaluar órganos internos como el hígado, riñones, bazo, vejiga, etc."),
    ELECTROCARDIOGRAMA  ( "Electrocardiograma","CARDIOLOGIA","6000", "Dr. Perez"," Registro de la actividad eléctrica del corazón para detectar problemas en el ritmo cardíaco, hipertrofia ventricular, isquemia, entre otros."),
    ECOCARDIOGRAFIA ( "Ecocardiografía","CARDIOLOGIA","6000", "Dr. Perez", " Utilización de ultrasonido para producir imágenes en tiempo real del corazón y sus estructuras, útil para evaluar la función cardíaca, anomalías estructurales, etc."),
    DERMATOSCOPIA("Dermatoscopia","DERMATOLOGIA","6000", "Dr. Silva", "Examen no invasivo de la piel que permite evaluar lesiones pigmentadas y no pigmentadas, útil para detectar melanomas, carcinomas, nevos, etc."),
    BIOPSIA_DE_PIEL("Biopsia de piel","DERMATOLOGIA","6000", "Dr. Silva", "Extracción de una muestra de piel para su análisis en laboratorio, útil para diagnosticar enfermedades de la piel como cáncer, infecciones, etc."),
    ENTREVISTA_PSIQUIATRICA("Entrevista psiquiátrica","PSIQUIATRIA","6000", "Dr. Fuentes", "Evaluación de la salud mental del paciente a través de una entrevista clínica, útil para diagnosticar trastornos psiquiátricos como depresión, ansiedad, esquizofrenia, etc."),
    EVALUACION_NEUROPSICOLOGICA("Evaluación neuropsicológica","PSIQUIATRIA","6000", "Dr. Fuentes", "Evaluación de las funciones cognitivas del paciente como memoria, atención, lenguaje, etc., útil para diagnosticar trastornos neurológicos como demencia, Alzheimer, etc."),
    ANALISIS_DE_COMPOSICION_CORPORAL("Análisis de composición corporal","NUTRICION","6000", "Dr. Camargo", "Análisis de la composición corporal del paciente a través de mediciones de peso, altura, índice de masa corporal, porcentaje de grasa, etc., útil para evaluar el estado nutricional y diseñar un plan de alimentación adecuado."),
    EVALUACION_DIETETICA("Evaluación dietética","NUTRICION","6000", "Dr. Camargo", "Evaluación de la alimentación del paciente para identificar hábitos saludables y perjudiciales, útil para diseñar un plan de alimentación personalizado."),
    LIMPIEZA_DENTAL("Limpieza dental","ODONTOLOGIA","6000", "Dr. Bueno", "Remoción de placa bacteriana y sarro de los dientes para prevenir enfermedades bucales como caries, gingivitis, periodontitis, etc."),
    RADIOGRAFIAS_DENTALES("Radiografías dentales","ODONTOLOGIA","6000", "Dr. Bueno", "Imágenes de los dientes y estructuras orales para detectar caries, infecciones, fracturas, etc."),
    ANALISIS_DE_SANGRE("Análisis de sangre (hemograma completo)","MEDICINA_GENERAL","5000", "Dr. Gamboa", "Análisis de los componentes de la sangre como glóbulos rojos, glóbulos blancos, plaquetas, hemoglobina, etc., útil para diagnosticar anemias, infecciones, trastornos de coagulación, etc."),
    RADIOGRAFIA_DE_TORAX("Radiografía de tórax","MEDICINA_GENERAL","5000", "Dr. Gamboa", "Imagen de los pulmones y estructuras torácicas para detectar enfermedades pulmonares, infecciones, tumores, etc.");

    private final String tipoExamen;
    private final String especialidad;
    private final String costo;
    private final String profesionalAsignado;
    private final String breveDescripcion;

    Examen(String tipoExamen, String especialidad, String costo, String profesionalAsignado, String breveDescripcion) {
        this.tipoExamen = tipoExamen;
        this.especialidad = especialidad;
        this.costo = costo;
        this.profesionalAsignado = profesionalAsignado;
        this.breveDescripcion = breveDescripcion;
    }

    @Override
    public String toString() {
        return " Examen" +
                ", tipoExamen='" + tipoExamen + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", costo='" + costo + '\'' +
                ", profesionalAsignado='" + profesionalAsignado + '\'' +
                ", breveDescripcion='" + breveDescripcion ;
    }
}
