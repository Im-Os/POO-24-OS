package medicos;

import java.time.LocalDate;

public class Medico {
    public int id;
    public String nombre;
    public String apellidos;
    public LocalDate fechaNacimiento;
    private String telefono;
    private String rfc;

    public Medico(String idMedico, String nombre, String apellidos, LocalDate fechaNacimiento, String telefono, String rfc) {
        this.id = Integer.parseInt(idMedico);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.rfc = rfc;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public String mostrarDatos() {
        return String.format(
                "Id: %d, Nombre: %s, Apellidos: %s, Fecha de Nacimiento: %s, Telefono: %s, RFC: %s",
                id, nombre, apellidos, fechaNacimiento, telefono, rfc);
    }
}
