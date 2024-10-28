package asiento;

public class Asiento {
    private int numero;
    private boolean disponible;
    private String tipo; // VIP, PREMIUM, REGULAR
    private double precio;

    public Asiento(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.disponible = true;
        switch (tipo.toUpperCase()) {
            case "VIP":
                this.precio = 400.00;
                break;
            case "PREMIUM":
                this.precio = 200.00;
                break;
            default:
                this.precio = 100.00;
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String mostrarInfoAsiento() {
        return "Asiento: " + getNumero() + " - Tipo: " + getTipo() + " - Precio: $" + getPrecio() + " - Disponible: " + (isDisponible() ? "Sí" : "No");
    }
}

