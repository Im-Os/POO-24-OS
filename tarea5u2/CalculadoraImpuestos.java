public class CalculadoraImpuestos {
    // Método para calcular impuestos (solo ingresos)
    public double calcularImpuestos(int ingresos) {
        return ingresos * 0.15;
    }

    // Método para calcular impuestos (ingresos y porcentajeImpuestos)
    public double calcularImpuestos(int ingresos, double porcentajeImpuestos) {
        return ingresos * (porcentajeImpuestos / 100);
    }

    // Método para calcular impuestos (dividendos, porcentajeImpuestos y exención)
    public double calcularImpuestos(double dividendos, double porcentajeImpuestos, double exencion) {
        double impuestos = dividendos * (porcentajeImpuestos / 100);
        if (impuestos > exencion) {
            return impuestos - exencion;
        } else {
            return 0;
        }
    }
}
