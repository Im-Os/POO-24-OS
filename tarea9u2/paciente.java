package paciente;

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
}
