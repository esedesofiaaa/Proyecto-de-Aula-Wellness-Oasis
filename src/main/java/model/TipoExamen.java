package model;

public enum TipoExamen {
    /* Dentro de nuestro proyecto partimos de la base que en la IPS solo se pueden realizar estos
     * examenes, y que en este enum estan discriminados por tipo de especilidad en la cual podrian
     * ordenarlos y el precio respectivo de cada examen */
    
    // Exámenes para Medicina General
    EXAMEN_SANGRE("Examen de Sangre", 50000),
    ECOGRAFIA("Ecografía", 150000),
    ELECTROCARDIOGRAMA("Electrocardiograma", 80000),
    COLPOSCOPIA("Colposcopía", 120000),
    RESONANCIA_MAGNETICA("Resonancia Magnética", 300000),
    
    // Exámenes para Pediatría
    OTRO_EXAMEN("Otro Examen", 60000),
    EXAMEN_SEMIOLOGIA("Semiologia", 70000),
    EXAMEN_PED2("Examen Pediátrico 2", 90000),
    EXAMEN_PED3("Examen Pediátrico 3", 110000),
    EXAMEN_PED4("Examen Pediátrico 4", 100000),
    
    // Exámenes para Cardiología
    EXAMEN_CARD1("Examen Cardiológico 1", 100000),
    EXAMEN_CARD2("Examen Cardiológico 2", 120000),
    EXAMEN_CARD3("Examen Cardiológico 3", 90000),
    EXAMEN_CARD4("Examen Cardiológico 4", 110000),
    EXAMEN_CARD5("Examen Cardiológico 5", 130000),
    
    // Exámenes para Dermatología
    EXAMEN_DER1("Examen Dermatológico 1", 80000),
    EXAMEN_DER2("Examen Dermatológico 2", 100000),
    EXAMEN_DER3("Examen Dermatológico 3", 70000),
    EXAMEN_DER4("Examen Dermatológico 4", 90000),
    EXAMEN_DER5("Examen Dermatológico 5", 95000),
    
    // Exámenes para Ginecología
    EXAMEN_GIN1("Examen Ginecológico 1", 120000),
    EXAMEN_GIN2("Examen Ginecológico 2", 140000),
    EXAMEN_GIN3("Examen Ginecológico 3", 110000),
    EXAMEN_GIN4("Examen Ginecológico 4", 130000),
    EXAMEN_GIN5("Examen Ginecológico 5", 150000);

    // Atributos del enum TipoExamen
    private final String nombre;
    private final double costo;

    // Constructor del enum
    TipoExamen(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    // Método para obtener el nombre del examen
    public String getNombre() {
        return nombre;
    }

    // Método para obtener el costo del examen
    public double getCosto() {
        return costo;
    }
}