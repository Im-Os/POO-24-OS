public class Rectangulo {
    private double largo;
    private double ancho;

    // Constructor
    public Rectangulo(double largo, double ancho) {
        this.largo = largo;
        this.ancho = ancho;
    }

    // Método para calcular el área (enteros)
    public int calcularArea(int largo, int ancho) {
        return largo * ancho;
    }

    // Método para calcular el área (doubles)
    public double calcularArea(double largo, double ancho) {
        return largo * ancho;
    }

    // Método para calcular el perímetro (enteros)
    public int calcularPerimetro(int largo, int ancho) {
        return 2 * (largo + ancho);
    }

    // Método para calcular el perímetro (doubles)
    public double calcularPerimetro(double largo, double ancho) {
        return 2 * (largo + ancho);
    }
}
