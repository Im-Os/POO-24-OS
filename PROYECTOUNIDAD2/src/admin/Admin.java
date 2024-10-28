package admin;

import utils.Rol;

import javax.management.relation.Role;

public class Admin {

    private String id;
    private String name;
    private String apellidos;
    private String fechaNacimiento;
    private String telefono;
    private String sueldo;
    private String password;
    private Rol rol;


    public Admin(String id, String name, String apellidos, String fechaNacimiento, String telefono, String sueldo, String password) {
        this.id = id;
        this.name = name;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.sueldo = sueldo;
        this.password = password;
        this.rol = Rol.ADMINISTRATOR;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getSueldo() {
        return sueldo;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
