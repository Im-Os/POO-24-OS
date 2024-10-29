// Seguro.java
public abstract class Seguro {
    protected String nombreTitular;
    protected double valorAsegurado;

    public Seguro(String nombreTitular, double valorAsegurado) {
        this.nombreTitular = nombreTitular;
        this.valorAsegurado = valorAsegurado;
    }

    // Método abstracto para calcular la prima
    public abstract double calcularPrima();

    // Método concreto para mostrar detalles del seguro
    public String detallesSeguro() {
        return "Titular: " + nombreTitular + 
               "\nValor Asegurado: $" + valorAsegurado + 
               "\nPrima Anual: $" + calcularPrima();
    }
}