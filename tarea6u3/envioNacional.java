public class envioNacional extends Envio {

    private double distancia;

    public envioNacional (double costoBase, double peso, double distancia) {
        super(costoBase, peso);
        this.distancia = distancia;
    }
    @Override
    public String calcularTiempoyCostoentrega(){
        double costoAdicional = 0;
        double costoTotal= 0;
        if (getPeso() > 5){
            costoAdicional = getCostoBase() * 0.05;
        }else {
            costoAdicional = 0;
        }
        costoTotal = costoAdicional+getCostoBase();
        int tiempoEntrega = 0;
        tiempoEntrega = (int) (distancia/200);

        return String.format("Costo base: $%.2f, \nCosto adicional: %.2f, \nCosto total: $%.2f\nPeso del paquete: %.2fKg. \nDistancia: %.2fKm Tiempo de entrega: %d dias"
                ,getCostoBase(),costoAdicional,costoTotal,getPeso(),distancia,tiempoEntrega);
    }
}