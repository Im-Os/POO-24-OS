// SeguroAuto.java
public class SeguroAuto extends Seguro {
    private int edadTitular;

    public SeguroAuto(String nombreTitular, double valorAsegurado, int edadTitular) {
        super(nombreTitular, valorAsegurado);
        this.edadTitular = edadTitular;
    }

    @Override
    public double calcularPrima() {
        double primaBase = 300;
        double primaVariable = 0.05 * valorAsegurado;
        double primaTotal = primaBase + primaVariable;

        // Aplica recargo si el titular tiene menos de 25 años
        if (edadTitular < 25) {
            primaTotal += 0.15 * primaTotal; // Recargo del 15%
        }

        return primaTotal;
    }

    @Override
    public String detallesSeguro() {
        return super.detallesSeguro() + "\nTipo: Seguro de Auto\nEdad del Titular: " + edadTitular;
    }
}