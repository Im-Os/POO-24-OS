import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();

        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\n*** BIENVENIDO ***");
            System.out.println("1. Registrar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Mostrar productos");
            System.out.println("4. Salir");

            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nSeleccionaste la opción para registrar un producto");

                    System.out.print("Ingresa el nombre del producto: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingresa el precio del producto: ");
                    double precio = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Ingresa la descripción del producto: ");
                    String descripcion = scanner.nextLine();

                    System.out.print("Ingresa la categoría del producto: ");
                    String categoria = scanner.nextLine();

                    System.out.print("Ingresa el stock del producto: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();

                    Producto producto = new Producto(nombre, precio);
                    producto.setDescripcion(descripcion);
                    producto.setCategoria(categoria);
                    producto.setStock(stock);

                    inventario.registrarProducto(producto);
                    System.out.println("\nProducto registrado correctamente");
                    break;
                case 2:
                    System.out.println("\nSeleccionaste la opción para eliminar un producto");

                    System.out.print("Ingresa el ID del producto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    inventario.eliminarProducto(id);
                    break;
                case 3:
                    inventario.mostrarProductos();
                    break;
                case 4:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
        scanner.close();
    }
}
