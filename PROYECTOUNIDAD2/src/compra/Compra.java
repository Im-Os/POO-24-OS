package compra;

import boleto.Boleto;
import dulceria.Dulceria;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Compra {
    private int id;
    private double precioTotal;
    private Date fechaCompra;
    private String tipoPago;
    private List<Boleto> boletos;
    private Map<Dulceria, Integer> productosDulceria;
    private static final ReentrantLock lock = new ReentrantLock();

    public Compra(int id, double precioTotal, Date fechaCompra, String tipoPago,
                  List<Boleto> boletos, List<String> productosAdicionales) {
        this.id = id;
        this.precioTotal = precioTotal;
        this.fechaCompra = fechaCompra;
        this.tipoPago = tipoPago;
        this.boletos = boletos != null ? boletos : new ArrayList<>();
        this.productosDulceria = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }

    public Map<Dulceria, Integer> getProductosDulceria() {
        return productosDulceria;
    }

    public void agregarProductoDulceria(Dulceria producto, int cantidad) {
        productosDulceria.put(producto, productosDulceria.getOrDefault(producto, 0) + cantidad);
        actualizarPrecioTotal();
    }

    private void actualizarPrecioTotal() {
        double totalBoletos = boletos.stream()
                .mapToDouble(Boleto::getPrecio)
                .sum();

        double totalDulceria = productosDulceria.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrecio() * entry.getValue())
                .sum();

        this.precioTotal = totalBoletos + totalDulceria;
    }

    public boolean realizarCompra() {
        if (boletos.isEmpty()) {
            System.out.println("No se puede realizar una compra sin boletos.");
            return false;
        }

        lock.lock();
        try {
            for (Boleto boleto : boletos) {
                if (!boleto.getAsiento().isDisponible()) {
                    System.out.println("El asiento " + boleto.getAsiento().getNumero() + " no está disponible.");
                    return false;
                }
            }

            // Verifica stock de productos de dulcería
            for (Map.Entry<Dulceria, Integer> entry : productosDulceria.entrySet()) {
                if (!entry.getKey().vender(entry.getValue())) {
                    System.out.println("No hay suficiente stock de " + entry.getKey().getNombre());
                    return false;
                }
            }

            actualizarPrecioTotal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Compra ID: ").append(id)
                .append("\nFecha: ").append(fechaCompra)
                .append("\nTipo de pago: ").append(tipoPago)
                .append("\nBoletos: ").append(boletos.size())
                .append("\nProductos de dulcería:");

        for (Map.Entry<Dulceria, Integer> entry : productosDulceria.entrySet()) {

            info.append("\n  - ").append(entry.getKey().getNombre())
                    .append(": ").append(entry.getValue())
                    .append(" x $").append(entry.getKey().getPrecio())
                    .append(" = $").append(entry.getKey().getPrecio() * entry.getValue());
        }

        info.append("\nTotal: $").append(String.format("%.2f", precioTotal));
        return info.toString();
    }

    public void agregarBoleto(Boleto boleto) {
        if (boleto != null) {
            this.boletos.add(boleto);
            actualizarPrecioTotal();
        }
    }

    public void removerBoleto(Boleto boleto) {
        if (this.boletos.remove(boleto)) {
            actualizarPrecioTotal();
        }
    }

    public void removerProductoDulceria(Dulceria producto) {
        if (productosDulceria.remove(producto) != null) {
            actualizarPrecioTotal();
        }
    }

    public void limpiarCarrito() {
        boletos.clear();
        productosDulceria.clear();
        actualizarPrecioTotal();
    }

    public boolean tieneProductos() {
        return !boletos.isEmpty() || !productosDulceria.isEmpty();
    }
}