package reservacion;

import asiento.Asiento;
import cliente.Cliente;
import pelicula.Pelicula;
import sala.Sala;
import horario.Horario;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Reservacion {
    private Cliente cliente;
    private Pelicula pelicula;
    private Sala sala;
    private Horario horario;
    private List<Asiento> asientosReservados;
    private boolean confirmada;
    private double total;

    public Reservacion(Cliente cliente, Pelicula pelicula, List<Asiento> asientosReservados) {
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.asientosReservados = asientosReservados != null ? asientosReservados : new ArrayList<>();
        this.confirmada = false;
        this.calcularTotal();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public List<Asiento> getAsientosReservados() {
        return asientosReservados;
    }

    public void setAsientosReservados(List<Asiento> asientosReservados) {
        this.asientosReservados = asientosReservados;
        calcularTotal();
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void confirmarReservacion() {
        this.confirmada = true;
    }

    public void cancelarReservacion() {
        this.confirmada = false;
        for (Asiento asiento : asientosReservados) {
            asiento.setDisponible(true);
        }
        asientosReservados.clear();
        calcularTotal();
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public double getTotal() {
        return total;
    }



    public void agregarAsiento(Asiento asiento) {
        if (asiento != null && asiento.isDisponible()) {
            asientosReservados.add(asiento);
            asiento.setDisponible(false);
            calcularTotal();
        }
    }

    public void removerAsiento(Asiento asiento) {
        if (asientosReservados.remove(asiento)) {
            asiento.setDisponible(true);
            calcularTotal();
        }
    }

    private double calcularTotal() {
        this.total = 0.0;
        for (Asiento asiento : asientosReservados) {
            double precioAsiento = asiento.getPrecio();
            if (cliente != null && cliente.getFechaNacimiento().getMonth() == LocalDate.now().getMonth()) {
                if (asiento.getTipo().equals("PREMIUM")) {
                    precioAsiento *= 0.4;
                } else if (asiento.getTipo().equals("VIP")) {
                    precioAsiento *= 0.65;
                }
            }
            this.total += precioAsiento;
        }
        return this.total;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNombre() + " " + cliente.getApellidos() +
                "\nPelícula: " + (pelicula != null ? pelicula.getTitulo() : "No seleccionada") +
                "\nSala: " + (sala != null ? "Sala " + sala.getNumeroSala() : "No asignada") +
                "\nHorario: " + (horario != null ? horario.mostrarInfoHorario() : "No seleccionado") +
                "\nAsientos reservados: " + asientosReservados.size() +
                "\nTotal: $" + String.format("%.2f", calcularTotal()) +
                "\nEstado: " + (confirmada ? "Confirmada" : "Pendiente");
    }
}