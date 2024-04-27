package estructurasDatos.interfaces;

import model.domain.paciente.Paciente;

public interface DoubleLinkedListInterface<T> {
        void agregarAlInicio(T valor);
        void agregarAlFinal(T valor);
        T eliminarDelInicio();
        T eliminarDelFinal();
        boolean estaVacia();
        int tamano();
        void insertarEnMedio (T valor, int indice);
        T eliminarEnMedio ( int indice);
        int buscarElemento (T valor);
        void mostrarLista ();
        //T navegarMetodo (int posicion);

        Paciente buscarPacientePorId(String id);//Metodo para buscar un paciente por su id en la lista y asi no tener que escribir todos los datos para encontrarlo

}
