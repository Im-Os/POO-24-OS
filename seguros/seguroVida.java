// SeguroVida.java
public class SeguroVida extends Seguro {
    private int edadTitular;

    public SeguroVida(String nombreTitular, double valorAsegurado, int edadTitular) {
        super(nombreTitular, valorAsegurado);
        this.edadTitular = edadTitular;
    }

    @Override
    public double calcularPrima() {
        double primaBase = 400;
        double primaVariable = 0.015 * valorAsegurado;
        double primaTotal = primaBase + primaVariable;

        // Aplica recargo si el titular tiene más de 60 años
        if (edadTitular > 60) {
            primaTotal += 0.25 * primaTotal; // Recargo del 25%
        }

        return primaTotal;
    }

    @Override
    public String detallesSeguro() {
        return super.detallesSeguro() + "\nTipo: Seguro de Vida\nEdad del Titular: " + edadTitular;
    }
}