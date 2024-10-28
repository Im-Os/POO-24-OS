package menu;

import cliente.Cliente;
import gestionsistema.gestionSistema;
import pelicula.Pelicula;
import sala.Sala;
import horario.Horario;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class menuCliente {
    private static Scanner sc = new Scanner(System.in);
    private static gestionSistema sistema;

    public static void mostrarMenuCliente(Cliente cliente, gestionSistema sistemaActual) {
        sistema = sistemaActual;
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Ver cartelera");
            System.out.println("2. Crear reservación");
            System.out.println("3. Ver mis boletos");
            System.out.println("4. Realizar compra");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    verCartelera();
                    break;
                case 2:
                    crearReservacion(cliente);
                    break;
                case 3:
                    verMisBoletos(cliente);
                    break;
                case 4:
                    realizarCompra(cliente);
                    break;
                case 5:
                    salir = true;
                    menuLogin.mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void verCartelera() {
        System.out.println("\n=== CARTELERA ACTUAL ===");
        sistema.mostrarCartelera();
    }

    private static void crearReservacion(Cliente cliente) {
        System.out.println("\n=== CREAR RESERVACIÓN ===");
        
        sistema.mostrarPeliculasDisponibles();
        
        System.out.print("Seleccione el ID de la película: ");
        int idPelicula = sc.nextInt();
        
        Pelicula pelicula = sistema.obtenerPeliculaPorId(idPelicula);
        if (pelicula != null && sistema.mostrarHorariosDisponibles(idPelicula)) {
            System.out.print("Seleccione el ID del horario: ");
            int idHorario = sc.nextInt();
            Horario horario = sistema.obtenerHorarioPorId(pelicula, idHorario);
            
            if (horario != null && sistema.mostrarSalasDisponibles(idPelicula, idHorario)) {
                System.out.print("Seleccione el número de sala: ");
                int numeroSala = sc.nextInt();
                Sala sala = sistema.obtenerSalaPorNumero(numeroSala);
                
                if (sala != null) {
                    sistema.mostrarMatrizAsientos(numeroSala);
                    System.out.print("¿Cuántos asientos desea reservar? ");
                    int cantidadAsientos = sc.nextInt();
                    sc.nextLine();
                    
                    List<String> asientosSeleccionados = new ArrayList<>();
                    for (int i = 0; i < cantidadAsientos; i++) {
                        boolean asientoValido = false;
                        while (!asientoValido) {
                            System.out.print("Ingrese la posición del asiento " + (i+1) + " (ejemplo: A1): ");
                            String posicionAsiento = sc.nextLine().toUpperCase();
                            
                            // Verificar si el asiento está disponible
                            if (sistema.verificarDisponibilidadAsiento(sala, posicionAsiento)) {
                                asientosSeleccionados.add(posicionAsiento);
                                asientoValido = true;
                            } else {
                                System.out.println("El asiento " + posicionAsiento + " no está disponible. Por favor, seleccione otro.");
                                sala.mostrarMatrizAsientos(); // Mostrar matriz actualizada
                            }
                        }
                    }
                    
                    if (sistema.crearReservacion(cliente, pelicula, sala, horario, asientosSeleccionados)) {
                        System.out.println("\nReservación creada exitosamente.");
                        System.out.print("¿Desea realizar otra reservación? (S/N): ");
                        if (sc.nextLine().toUpperCase().equals("S")) {
                            crearReservacion(cliente);
                        } else {
                            realizarCompra(cliente);
                        }
                    }
                }
            }
        }
    }

    private static void verMisBoletos(Cliente cliente) {
        System.out.println("\n=== MIS BOLETOS ===");
        sistema.mostrarBoletosCliente(cliente);
    }

    private static void realizarCompra(Cliente cliente) {
        System.out.println("\n=== REALIZAR COMPRA ===");

        if (!sistema.tieneReservacionPendiente(cliente)) {
            System.out.println("No tiene reservaciones pendientes. Debe crear una reservación primero.");
            return;
        }

        sistema.mostrarResumenReservacion(cliente);

        System.out.print("¿Desea agregar productos de dulcería? (S/N): ");
        if (sc.nextLine().toUpperCase().equals("S")) {
            agregarProductosDulceria(cliente);
        }

        procesarPago(cliente);
    }

    private static void agregarProductosDulceria(Cliente cliente) {
        boolean seguirComprando = true;

        while (seguirComprando) {
            sistema.mostrarProductosDulceria();

            System.out.print("Seleccione el ID del producto (0 para terminar): ");
            int idProducto = sc.nextInt();

            if (idProducto == 0) {
                seguirComprando = false;
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            sistema.agregarProductoACompra(cliente, idProducto, cantidad);

            System.out.print("¿Desea agregar más productos? (S/N): ");
            seguirComprando = sc.nextLine().toUpperCase().equals("S");
        }
    }

    private static void procesarPago(Cliente cliente) {
        double total = sistema.calcularTotalCompra(cliente);
        System.out.println("\nTotal a pagar: $" + String.format("%.2f", total));

        System.out.println("\nMétodos de pago disponibles:");
        System.out.println("1. Tarjeta de crédito");
        System.out.println("2. Tarjeta de débito");
        System.out.println("3. Efectivo");
        System.out.print("Seleccione el método de pago: ");

        int metodoPago = sc.nextInt();
        sc.nextLine();

        if (sistema.procesarPago(cliente, metodoPago, total)) {
            System.out.println("¡Compra realizada exitosamente!");
            sistema.generarBoletos(cliente);
        } else {
            System.out.println("Error al procesar el pago. Por favor intente nuevamente.");
        }
    }
}