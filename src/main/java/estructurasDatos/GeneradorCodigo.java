package estructurasDatos;

import java.util.Random;

public class GeneradorCodigo {

    // Método para generar un código compuesto por una letra y un número aleatorio
    public String generarCodigo(String especialidad, String tipoCita) {
        Random random = new Random();

        // Obtener la letra correspondiente a la especialidad y al tipo de cita
        String letraEspecialidad = obtenerLetraEspecialidad(especialidad);
        String letraTipoCita = obtenerLetraTipoCita(tipoCita);

        // Generar un número aleatorio entre 100 y 999
        int numeroAleatorio = random.nextInt(900) + 100; // Rango de 100 a 999

        // Concatenar las letras de la especialidad, tipo de cita y el número aleatorio
        String codigo = letraEspecialidad + letraTipoCita + numeroAleatorio;

        return codigo;
    }

    // Método auxiliar para obtener la letra correspondiente a la especialidad
    private String obtenerLetraEspecialidad(String especialidad) {
        switch (especialidad) {
            case "Medicina general":
                return "M";
            case "Pediatria":
                return "P";
            case "Cardiologia":
                return "C";
            case "Dermatologia":
                return "D";
            case "Ginecologia":
                return "G";
            default:
                throw new IllegalArgumentException("Especialidad no válida: " + especialidad);
        }
    }

    // Método auxiliar para obtener la letra correspondiente al tipo de cita
    private String obtenerLetraTipoCita(String tipoCita) {
        switch (tipoCita) {
            case "CONTROL":
                return "C";
            case "VALORACION":
                return "V";
            case "EXAMEN":
                return "E";
            default:
                throw new IllegalArgumentException("Tipo de cita no válido: " + tipoCita);
        }
    }

    // Método para generar un identificador aleatorio de 5 caracteres (letra + números)
    public String generarIdentificador() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Generar la letra aleatoria
        char letra = generarLetraAleatoria();
        sb.append(letra);

        // Generar los números aleatorios
        for (int i = 0; i < 3; i++) {
            int numeroAleatorio = random.nextInt(10); // Números del 0 al 9
            sb.append(numeroAleatorio);
        }

        return sb.toString();
    }

    // Método auxiliar para generar una letra aleatoria (mayúscula)
    private char generarLetraAleatoria() {
        Random random = new Random();
        // Rango de letras mayúsculas en ASCII (A=65, Z=90)
        return (char) (random.nextInt(26) + 'A');
    }



}
