import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Categoria {
    private int id;
    private String nombre;
    private List<Producto> productos;

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
}