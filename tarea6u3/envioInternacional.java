class EnvioInternacional extends Envio {
    private String destino;

    public EnvioInternacional(double costoBase, double peso, String destino) {
        super(costoBase, peso);
        this.destino = destino;
    }

    @Override
    public String calcularTiempoYCostoEntrega() {
        int tiempoEntrega = 10;

        if (peso > 10) {
            tiempoEntrega += 3;
        }

        double costoTotal = costo + (costo * 0.20);

        return "Tiempo de entrega: " + tiempoEntrega + " días\nCosto total: $" + costoTotal;
    }
}