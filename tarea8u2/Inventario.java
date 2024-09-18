class Inventario {
    private List<Producto> productos;
    private List<Categoria> categorias;

    public Inventario() {
        productos = new ArrayList<>();
        categorias = new ArrayList<>();
        // Agregar algunas categorías de ejemplo
        categorias.add(new Categoria(1, "Electrónica"));
        categorias.add(new Categoria(2, "Ropa"));
    }

    public void registrarProducto(Producto producto) {
        productos.add(producto);
        producto.getCategoria().agregarProducto(producto);
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
            System.out.println("ID: " + i + ", Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Descripción: " + producto.getDescripcion() + ", Categoría: " + producto.getCategoria().getNombre() + ", Stock: " + producto.getStock());
        }
    }

    public void listarCategorias() {
        for (Categoria categoria : categorias) {
            System.out.println("ID: " + categoria.getId() + ", Nombre: " + categoria.getNombre());
        }
    }

    public void listarCategoriasConProductos() {
        for (Categoria categoria : categorias) {
            System.out.println("ID: " + categoria.getId() + ", Nombre: " + categoria.getNombre());
            for (Producto producto : categoria.getProductos()) {
                System.out.println("  Producto: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Descripción: " + producto.getDescripcion() + ", Stock: " + producto.getStock());
            }
        }
    }

    public Categoria obtenerCategoriaPorId(int id) {
        for (Categoria categoria : categorias) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        return null;
    }
}