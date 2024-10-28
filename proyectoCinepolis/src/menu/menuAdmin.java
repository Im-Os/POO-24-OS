package menu;

import admin.Admin;
import gestionsistema.gestionSistema;
import java.util.Scanner;

public class menuAdmin {
    private static Scanner sc = new Scanner(System.in);
    private static gestionSistema sistema;

    public static void mostrarMenuAdmin(Admin admin, gestionSistema sistemaActual) {
        sistema = sistemaActual;
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Gestionar Películas");
            System.out.println("2. Gestionar Salas");
            System.out.println("3. Gestionar Dulcería");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    gestionarPeliculas();
                    break;
                case 2:
                    gestionarSalas();
                    break;
                case 3:
                    gestionarDulceria();
                    break;
                case 4:
                    salir = true;
                    menuLogin.mostrarMenuPrincipal(); 
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void gestionarPeliculas() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n=== GESTIÓN DE PELÍCULAS ===");
            System.out.println("1. Ver películas");
            System.out.println("2. Agregar película");
            System.out.println("3. Modificar película");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    sistema.verPeliculas();
                    break;
                case 2:
                    sistema.agregarPelicula(sc);
                    break;
                case 3:
                    sistema.modificarPelicula(sc);
                    break;
                case 4:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void gestionarSalas() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n=== GESTIÓN DE SALAS ===");
            System.out.println("1. Ver salas");
            System.out.println("2. Agregar sala");
            System.out.println("3. Modificar sala");
            System.out.println("4. Eliminar sala");
            System.out.println("5. Asignar película a sala");
            System.out.println("6. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    sistema.verSalas();
                    break;
                case 2:
                    sistema.agregarSala(sc);
                    break;
                case 3:
                    sistema.modificarSala(sc);
                    break;
                case 4:
                    sistema.eliminarSala(sc);
                    break;
                case 5:
                    sistema.asignarPeliculaASala(sc);
                    break;
                case 6:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void gestionarDulceria() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n=== GESTIÓN DE DULCERÍA ===");
            System.out.println("1. Ver productos");
            System.out.println("2. Agregar producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    sistema.verProductosDulceria();
                    break;
                case 2:
                    sistema.agregarProductoDulceria(sc);
                    break;
                case 3:
                    sistema.modificarProductoDulceria(sc);
                    break;
                case 4:
                    sistema.eliminarProductoDulceria(sc);
                    break;
                case 5:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}