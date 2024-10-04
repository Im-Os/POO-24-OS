package usuarios.administrador;
import usuarios.Usuario;
import usuarios.utils.Rol;

import java.time.LocalDate;

public class administrador extends Usuario{

    public String sueldo;
    private String rfc;
    public String añosTrabajados;


    public Administrador(String id, String nombre, String apellidos, LocalDate fechaNacimiento, String telefono, String rfc, String sueldo, String añosTrabajados, String contrasenia ) {

        super(id, nombre, apellidos, fechaNacimiento, telefono, contrasenia, Rol.ADMIN);
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.rfc = rfc;
        this.sueldo = sueldo;
        this.añosTrabajados = añosTrabajados;
    }

    public String getSueldo() {
        return sueldo;
    }

    public String getRfc() {
        return rfc;
    }

    public String getAñosTrabajados() {
        return añosTrabajados;
    }

    public String mostrarDatosAdmin() {
        String datos = String.format("Id: %s, nombre: %s, apellidos: %s, fecha de nacimiento: %s, telefono: %s, rfc: %s, sueldo: %s, años Trabajados: %s",
                id,
                nombre,
                apellidos,
                fechaNacimiento,
                telefono,
                rfc,
                sueldo,
                añosTrabajados);
        return datos;
    }
}