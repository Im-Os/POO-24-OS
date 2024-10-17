package medicos;

import java.time.LocalDate;
import java.util.List;

public class Medico {
    private String id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String rfc;
    private List<String> consultasProgramadas;

    public Medico(String id, String nombre, String apellidos, LocalDate fechaNacimiento, String telefono, String rfc, List<String> consultasProgramadas) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.rfc = rfc;
        this.consultasProgramadas = consultasProgramadas;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public List<String> getConsultasProgramadas() {
        return consultasProgramadas;
    }

    public void setConsultasProgramadas(List<String> consultasProgramadas) {
        this.consultasProgramadas = consultasProgramadas;
    }

    public String mostrarDatos() {
        return String.format("Id: %s, Nombre: %s, Apellidos: %s, Fecha de Nacimiento: %s, Teléfono: %s, RFC: %s",
                id, nombre, apellidos, fechaNacimiento, telefono, rfc);
    }

    public void verConsultasProgramadas() {
        System.out.println("Consultas Programadas:");
        for (String consulta : consultasProgramadas) {
            System.out.println(consulta);
        }
    }
}
