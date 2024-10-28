package dulceria;

public class Dulceria {
    private int id;
    private String nombre;
    private double precio;
    private String tipo; 
    private int cantidad;
    private boolean disponible;

    public Dulceria(int id, String nombre, double precio, String tipo, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.disponible = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void actualizarDisponibilidad() {
        this.disponible = this.cantidad > 0;
    }

    public boolean vender(int cantidad) {
        if (this.cantidad >= cantidad && this.disponible) {
            this.cantidad -= cantidad;
            actualizarDisponibilidad();
            return true;
        }
        return false;
    }

    public void agregarStock(int cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
            actualizarDisponibilidad();
        }
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nombre: " + nombre +
                " | Precio: $" + precio +
                " | Tipo: " + tipo +
                " | Cantidad: " + cantidad +
                " | Disponible: " + (disponible ? "Sí" : "No");
    }
}