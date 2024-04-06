package model;

public enum MotivoCita {
    EXAMENES(50),
    CONTROL(30),
    VALORACION(40);

    private final int costo;

    MotivoCita(int costo) {
        this.costo = costo;
    }

    public int getCosto() {
        return costo;
    }
}
