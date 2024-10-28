package pago;

public class Pago {
    private double monto;
    private String metodoPago;

    public Pago(double monto, String metodoPago) {
        this.monto = monto;
        this.metodoPago = metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public boolean procesarPago() {
        // AQUI SE PUEDE SIMULAR EL POCESO DEL PAGO
        // PARA FINES PRACTICOS, EL PAGO SIEMPRE VA A SER EXITOSO
        return true;
    }

    public String mostrarInfoPago() {
        return "Monto: " + getMonto() + " - Método de Pago: " + getMetodoPago() + " - Pago Procesado: " + (procesarPago() ? "Sí" : "No");
    }
}
