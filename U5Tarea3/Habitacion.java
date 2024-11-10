
class HabitacionNoDisponibleException extends Exception {
    public HabitacionNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}

class NumeroDeNochesInvalidoException extends Exception {
    public NumeroDeNochesInvalidoException(String mensaje) {
        super(mensaje);
    }
}

public class Habitacion {
    private String tipo;
    private double precioPorNoche;
    private boolean disponible;

    public Habitacion(String tipo, double precioPorNoche) {
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.disponible = true;
    }

    public void reservar(int noches) throws HabitacionNoDisponibleException, NumeroDeNochesInvalidoException {
        if (!disponible) {
            throw new HabitacionNoDisponibleException("La habitación no está disponible.");
        }
        if (noches < 1) {
            throw new NumeroDeNochesInvalidoException("El número de noches debe ser mayor o igual a 1.");
        }
        disponible = false;
        System.out.println("Reserva exitosa para " + noches + " noches.");
        System.out.println("Costo total de la reserva: " + (precioPorNoche * noches));
    }

    public void liberar() {
        disponible = true;
        System.out.println("La habitación ha sido liberada y está disponible nuevamente.");
    }

    public void mostrarDetalles() {
        System.out.println("Tipo de habitación: " + tipo);
        System.out.println("Precio por noche: $" + precioPorNoche);
        System.out.println("Disponibilidad: " + (disponible ? "Disponible" : "Ocupada"));
    }

    public boolean isDisponible() {
        return disponible;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }
}
