package oasis.model.domain.paciente;

import java.util.Objects;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.TipoDocumento;
import oasis.model.domain.cita.Cita;

public class Paciente {
    public TipoDocumento tipoDocumento;
    private String id;
    private String nombre;
    private String apellido;
    private String edad;
    private String telefono;
    private String correo;
    private DoubleLinkedList<Cita> historialCitas; // Historial de citas del paciente

    public Paciente(TipoDocumento tipoDocumento, String id, String nombre, String apellido, String edad, String telefono, String correo) {
        this.tipoDocumento = tipoDocumento;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Método para agregar una cita al historial de citas del paciente


    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //Para poder modificar el Paciente

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "tipoDocumento=" + tipoDocumento +
                ", id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad='" + edad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", historialCitas=" + historialCitas +
                '}';
    }
}
