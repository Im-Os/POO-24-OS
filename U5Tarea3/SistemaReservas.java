import java.util.ArrayList;
import java.util.Scanner;

public class SistemaReservas {
    private ArrayList<Habitacion> habitaciones;

    public SistemaReservas() {
        habitaciones = new ArrayList<>();
        habitaciones.add(new Habitacion("Individual", 50.0));
        habitaciones.add(new Habitacion("Doble", 75.0));
        habitaciones.add(new Habitacion("Suite", 150.0));
    }

    public void mostrarHabitaciones() {
        for (Habitacion habitacion : habitaciones) {
            habitacion.mostrarDetalles();
            System.out.println();
        }
    }

    public void reservarHabitacion(int indice, int noches) {
        try {
            Habitacion habitacion = habitaciones.get(indice);
            habitacion.reservar(noches);
        } catch (HabitacionNoDisponibleException | NumeroDeNochesInvalidoException e) {
            System.out.println("Error al realizar la reserva: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Número de habitación no válido.");
        }
    }

    public void liberarHabitacion(int indice) {
        try {
            Habitacion habitacion = habitaciones.get(indice);
            if (habitacion.isDisponible()) {
                System.out.println("La habitación ya está disponible.");
            } else {
                habitacion.liberar();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Número de habitación no válido.");
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-- Sistema de Reservas de Habitaciones ---");
            System.out.println("1. Mostrar detalles de las habitaciones");
            System.out.println("2. Realizar una reserva");
            System.out.println("3. Liberar una habitación");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarHabitaciones();
                    break;
                case 2:
                    System.out.println("Ingrese el número de habitación (1: Individual, 2: Doble, 3: Suite): ");
                    int numeroHabitacion = scanner.nextInt() - 1;
                    System.out.print("Ingrese el número de noches a reservar: ");
                    int noches = scanner.nextInt();
                    reservarHabitacion(numeroHabitacion, noches);
                    break;
                case 3:
                    System.out.print("Ingrese el número de habitación para liberar (1: Individual, 2: Doble, 3: Suite): ");
                    int habitacionALiberar = scanner.nextInt() - 1;
                    liberarHabitacion(habitacionALiberar);
                    break;
                case 4:
                    System.out.println("Saliendo del sistema.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void main(String[] args) {
        SistemaReservas sistema = new SistemaReservas();
        sistema.menu();
    }
}
