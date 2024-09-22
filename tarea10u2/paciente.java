package paciente;

import static java.lang.String.format;

public class Paciente {
    public int id;
    public String nombre;
    public String apellidos;
    public String fechaNacimiento;
    public String tipoSangre;
    public char sexo;
    private String telefono;

    public Paciente(int id, String telefono, char sexo, String tipoSangre, String fechaNacimiento, String apellidos, String nombre) {
        this.id = id;
        this.telefono = telefono;
        this.sexo = sexo;
        this.tipoSangre = tipoSangre;
        this.fechaNacimiento = fechaNacimiento;
        this.apellidos = apellidos;
        this.nombre = nombre;
    }

    public Paciente(String id, String nombre, String apellido, String fechaNacimiento, String tipoSangre, String sexo) {
    }

    public String mostrarDatos(){
        String datos = String.format(
                "Id: %d, Nombre: %s, Apellidos: %s, Fecha de Nacimiento: %s, Tipo de Sangre: %s, Sexo: %s, Telefono: %s",
                id, nombre, apellidos, tipoSangre, sexo, telefono);
        return datos;
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

    public String getFechaNacimiento() {
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
