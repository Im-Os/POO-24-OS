import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();

    public void registrarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(int id) {
        if (id >= 0 && id < productos.size()) {
            productos.remove(id);
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("ID de producto inválido.");
        }
    }

    public void mostrarProductos() {
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            System.out.println("ID: " + i + ", Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Descripción: " + producto.getDescripcion() + ", Categoría: " + producto.getCategoria() + ", Stock: " + producto.getStock());
        }
    }
}
