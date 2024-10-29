// SeguroHogar.java
public class SeguroHogar extends Seguro {
    private boolean enZonaDeRiesgo;

    public SeguroHogar(String nombreTitular, double valorAsegurado, boolean enZonaDeRiesgo) {
        super(nombreTitular, valorAsegurado);
        this.enZonaDeRiesgo = enZonaDeRiesgo;
    }

    @Override
    public double calcularPrima() {
        double primaBase = 500;
        double primaVariable = 0.02 * valorAsegurado;
        double primaTotal = primaBase + primaVariable;

        // Aplica recargo si está en zona de riesgo
        if (enZonaDeRiesgo) {
            primaTotal += 0.20 * primaTotal; // Recargo del 20%
        }

        return primaTotal;
    }

    @Override
    public String detallesSeguro() {
        return super.detallesSeguro() + "\nTipo: Seguro de Hogar\nEn Zona de Riesgo: " + enZonaDeRiesgo;
    }
}