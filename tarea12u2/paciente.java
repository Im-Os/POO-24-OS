package paciente;

import java.time.LocalDate;

public class Paciente {
    public int id;
    public String nombre;
    public String apellidos;
    public LocalDate fechaNacimiento;
    public String tipoSangre;
    public char sexo;
    private String telefono;

    public Paciente(int id, String telefono, char sexo, String tipoSangre, LocalDate fechaNacimiento, String apellidos, String nombre) {
        this.id = id;
        this.telefono = telefono;
        this.sexo = sexo;
        this.tipoSangre = tipoSangre;
        this.fechaNacimiento = fechaNacimiento;
        this.apellidos = apellidos;
        this.nombre = nombre;
    }

    public Paciente(String id, String nombre, String apellido, LocalDate fechaNacimiento, String tipoSangre, String sexo, String telefono) {
        this.id = Integer.parseInt(id);
        this.nombre = nombre;
        this.apellidos = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
        this.sexo = sexo.charAt(0);
        this.telefono = telefono;
    }

    public String mostrarDatos() {
        return String.format(
                "Id: %d, Nombre: %s, Apellidos: %s, Fecha de Nacimiento: %s, Tipo de Sangre: %s, Sexo: %s, Telefono: %s",
                id, nombre, apellidos, fechaNacimiento, tipoSangre, sexo, telefono);
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

    public String getTipoSangre() {
        return tipoSangre;
    }

    public char getSexo() {
        return sexo;
    }

    public String getTelefono() {
        return telefono;
    }
}
