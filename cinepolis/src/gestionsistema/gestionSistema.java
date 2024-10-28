package gestionsistema;

import asiento.Asiento;
import boleto.Boleto;
import cine.Cine;
import cliente.Cliente;
import compra.Compra;
import dulceria.Dulceria;
import horario.Horario;
import pelicula.Pelicula;
import reservacion.Reservacion;
import sala.Sala;
import admin.Admin;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

public class gestionSistema {

    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    private ArrayList<Sala> listaSalas = new ArrayList<>();
    private ArrayList<Horario> listaHorarios = new ArrayList<>();
    private ArrayList<Reservacion> listaReservaciones = new ArrayList<>();
    private ArrayList<Compra> listaCompras = new ArrayList<>();
    private ArrayList<Cine> listaCines = new ArrayList<>();
    private ArrayList<Boleto> listaBoletos = new ArrayList<>();
    private ArrayList<Asiento> listaAsientos = new ArrayList<>();
    private ArrayList<Admin> listaAdmins = new ArrayList<>();
    private ArrayList<Dulceria> listaDulceria = new ArrayList<>();

    private int contadorIdDulceria = 1;
    private int contadorIdPelicula = 1;
    private int contadorIdBoleto = 1;
    private int contadorIdCompra = 1;

    public void registrarCliente(Cliente cliente) {
        this.listaClientes.add(cliente);
    }

    public void registrarPelicula(Pelicula pelicula) {
        this.listaPeliculas.add(pelicula);
    }

    public void registrarSala(Sala sala) {
        this.listaSalas.add(sala);
    }

    public void registrarHorario(Horario horario) {
        this.listaHorarios.add(horario);
    }

    public void registrarReservacion(Reservacion reservacion) {
        this.listaReservaciones.add(reservacion);
    }

    public void registrarCompra(Compra compra) {
        this.listaCompras.add(compra);
    }

    public void registrarCine(Cine cine) {
        this.listaCines.add(cine);
    }

    public void registrarBoleto(Boleto boleto) {
        this.listaBoletos.add(boleto);
    }

    public void registrarAsiento(Asiento asiento) {
        this.listaAsientos.add(asiento);
    }

    public void registrarAdmin(Admin admin) {
        this.listaAdmins.add(admin);
    }

    public void registrarProductoDulceria(Dulceria producto) {
        this.listaDulceria.add(producto);
    }

    public void verPeliculas() {
        if (listaPeliculas.isEmpty()) {
            System.out.println("No hay películas registradas.");
            return;
        }
        System.out.println("\n=== PELÍCULAS REGISTRADAS ===");
        for (Pelicula pelicula : listaPeliculas) {
            System.out.println(pelicula.mostrarDatos());
            System.out.println("------------------------");
        }
    }

    public void agregarPelicula(Scanner scanner) {
        System.out.println("\n=== AGREGAR NUEVA PELÍCULA ===");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Género: ");
        String genero = scanner.nextLine();

        System.out.print("Clasificación: ");
        String clasificacion = scanner.nextLine();

        System.out.print("Duración (minutos): ");
        int duracion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Sinopsis: ");
        String sinopsis = scanner.nextLine();

        Pelicula pelicula = new Pelicula(contadorIdPelicula++, genero, titulo, clasificacion, duracion, sinopsis);

        // Aqui se grega horarios
        while (true) {
            System.out.println("\n¿Desea agregar un horario? (S/N)");
            if (scanner.nextLine().toUpperCase().equals("N")) break;

            try {
                System.out.print("Hora de inicio (HH:mm): ");
                String horaInicio = scanner.nextLine();
                Date inicio = new SimpleDateFormat("HH:mm").parse(horaInicio);

                //Aqui se hace el calculo del fin de la pelicula en base a la hora que se puso de inicio
                Calendar cal = Calendar.getInstance();
                cal.setTime(inicio);
                cal.add(Calendar.MINUTE, duracion);
                Date fin = cal.getTime();

                Horario horario = new Horario(inicio, fin);
                pelicula.agregarHorario(horario);
                registrarHorario(horario);

                System.out.println("Horario agregado exitosamente.");
            } catch (Exception e) {
                System.out.println("Error al ingresar el horario. Formato correcto: HH:mm");
            }
        }

        registrarPelicula(pelicula);
        System.out.println("Película agregada exitosamente.");
    }

    public void modificarPelicula(Scanner scanner) {
        System.out.println("\n=== MODIFICAR PELÍCULA ===");
        verPeliculas();

        System.out.print("Ingrese el ID de la película a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Pelicula pelicula = obtenerPeliculaPorId(id);
        if (pelicula == null) {
            System.out.println("Película no encontrada.");
            return;
        }

        System.out.println("\nModificando película: " + pelicula.getTitulo());
        System.out.println("1. Título");
        System.out.println("2. Género");
        System.out.println("3. Clasificación");
        System.out.println("4. Duración");
        System.out.println("5. Sinopsis");
        System.out.println("6. Horarios");
        System.out.print("Seleccione el campo a modificar: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nuevo título: ");
                pelicula.setTitulo(scanner.nextLine());
                break;
            case 2:
                System.out.print("Nuevo género: ");
                pelicula.setGenero(scanner.nextLine());
                break;
            case 3:
                System.out.print("Nueva clasificación: ");
                pelicula.setClasificacion(scanner.nextLine());
                break;
            case 4:
                System.out.print("Nueva duración (minutos): ");
                pelicula.setDuracion(scanner.nextInt());
                break;
            case 5:
                System.out.print("Nueva sinopsis: ");
                pelicula.setSinopsis(scanner.nextLine());
                break;
            case 6:
                modificarHorariosPelicula(scanner, pelicula);
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        System.out.println("Película modificada exitosamente.");
    }

    private void modificarHorariosPelicula(Scanner scanner, Pelicula pelicula) {
        while (true) {
            System.out.println("\nHorarios actuales:");
            List<Horario> horarios = pelicula.getHorarios();
            for (int i = 0; i < horarios.size(); i++) {
                System.out.println(i + ". " + horarios.get(i).mostrarInfoHorario());
            }

            System.out.println("\n1. Agregar horario");
            System.out.println("2. Eliminar horario");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Hora de inicio (HH:mm): ");
                        String horaInicio = scanner.nextLine();
                        Date inicio = new SimpleDateFormat("HH:mm").parse(horaInicio);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(inicio);
                        cal.add(Calendar.MINUTE, pelicula.getDuracion());
                        Date fin = cal.getTime();

                        Horario horario = new Horario(inicio, fin);
                        pelicula.agregarHorario(horario);
                        registrarHorario(horario);
                        System.out.println("Horario agregado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("Error al ingresar el horario.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el número del horario a eliminar: ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index < horarios.size()) {
                        Horario horarioEliminado = horarios.remove(index);
                        listaHorarios.remove(horarioEliminado);
                        System.out.println("Horario eliminado exitosamente.");
                    } else {
                        System.out.println("Índice no válido.");
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public Pelicula obtenerPeliculaPorId(int id) {
        for (Pelicula pelicula : listaPeliculas) {
            if (pelicula.getId() == id) {
                return pelicula;
            }
        }
        return null;
    }
    // Métodos para gestión de salas
    public void verSalas() {
        if (listaSalas.isEmpty()) {
            System.out.println("No hay salas registradas.");
            return;
        }
        System.out.println("\n=== SALAS REGISTRADAS ===");
        for (Sala sala : listaSalas) {
            System.out.println(sala.mostrarInfoSala());
            System.out.println("------------------------");
        }
    }

    public void agregarSala(Scanner scanner) {
        try {
            System.out.println("\n=== AGREGAR NUEVA SALA ===");
            System.out.print("Número de sala: ");
            int numeroSala = scanner.nextInt();

            // Validor que la sala no existe
            for (Sala s : listaSalas) {
                if (s.getNumeroSala() == numeroSala) {
                    System.out.println("Ya existe una sala con ese número.");
                    return;
                }
            }

            System.out.print("Número de filas: ");
            int filas = scanner.nextInt();

            System.out.print("Número de columnas: ");
            int columnas = scanner.nextInt();
            scanner.nextLine();

            int capacidadTotal = filas * columnas;

            //PREGUNTAR CUANTOS TIPOS DE ASIENTOS DE CADA TIPO EXISTRN
            List<Asiento> asientos = new ArrayList<>(capacidadTotal);
            int asientosVIP = 0;
            int asientosPremium = 0;

            for (int i = 0; i < filas; i++) {
                System.out.println("Fila " + (char)('A' + i) + " - Seleccione tipo (V: VIP, P: Premium, R: Regular):");
                String tipo = scanner.nextLine().toUpperCase();

                for (int j = 0; j < columnas; j++) {
                    String tipoAsiento;
                    switch (tipo) {
                        case "V":
                            tipoAsiento = "VIP";
                            asientosVIP++;
                            break;
                        case "P":
                            tipoAsiento = "PREMIUM";
                            asientosPremium++;
                            break;
                        default:
                            tipoAsiento = "REGULAR";
                    }
                    asientos.add(new Asiento((i * columnas) + j + 1, tipoAsiento));
                }
            }

            Sala sala = new Sala(numeroSala, capacidadTotal, asientosVIP, asientosPremium,
                    asientos, new ArrayList<>(), new ArrayList<>());
            sala.setFilas(filas);
            sala.setColumnas(columnas);

            registrarSala(sala);
            System.out.println("\nMatriz de asientos:");
            sala.mostrarMatrizAsientos();
            System.out.println("\nSala agregada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar la sala: " + e.getMessage());
            scanner.nextLine(); 
        }
    }

    public void mostrarMatrizAsientos(Sala sala, int filas, int columnas) {
        System.out.println("\nMatriz de asientos:");
        System.out.println("PANTALLA");

        for (int i = 0; i < columnas * 4; i++) {
            System.out.print("-");
        }
        System.out.println();

        List<Asiento> asientos = sala.getAsientos();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Asiento asiento = asientos.get(i * columnas + j);
                String marca;
                switch (asiento.getTipo()) {
                    case "VIP":
                        marca = asiento.isDisponible() ? "V" : "X";
                        break;
                    case "PREMIUM":
                        marca = asiento.isDisponible() ? "P" : "X";
                        break;
                    default:
                        marca = asiento.isDisponible() ? "R" : "X";
                        break;
                }
                System.out.printf("%c%d%s ", (char)('A' + i), j + 1, marca);
            }
            System.out.println();
        }
    }

    public void mostrarMatrizAsientos(int numeroSala) {
        Sala sala = obtenerSalaPorNumero(numeroSala);
        if (sala != null) {
            System.out.println("\nSala " + numeroSala);
            sala.mostrarMatrizAsientos();
        } else {
            System.out.println("Sala no encontrada.");
        }
    }
    public void eliminarSala(Scanner scanner) {
        System.out.println("\n=== ELIMINAR SALA ===");
        verSalas();

        System.out.print("Ingrese el número de la sala a eliminar: ");
        int numeroSala = scanner.nextInt();

        Sala salaAEliminar = null;
        for (Sala sala : listaSalas) {
            if (sala.getNumeroSala() == numeroSala) {
                salaAEliminar = sala;
                break;
            }
        }

        if (salaAEliminar != null) {
            listaAsientos.removeAll(salaAEliminar.getAsientos());
            listaSalas.remove(salaAEliminar);
            System.out.println("Sala eliminada exitosamente.");
        } else {
            System.out.println("Sala no encontrada.");
        }
    }

    public void verProductosDulceria() {
        if (listaDulceria.isEmpty()) {
            System.out.println("No hay productos registrados en la dulcería.");
            return;
        }
        System.out.println("\n=== PRODUCTOS DE DULCERÍA ===");
        for (Dulceria producto : listaDulceria) {
            System.out.println(producto.toString());
            System.out.println("------------------------");
        }
    }

    public void agregarProductoDulceria(Scanner scanner) {
        System.out.println("\n=== AGREGAR NUEVO PRODUCTO ===");

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Tipo (Palomitas/Bebidas/Snacks/Otros): ");
        String tipo = scanner.nextLine();

        System.out.print("Cantidad inicial en stock: ");
        int cantidad = scanner.nextInt();

        Dulceria producto = new Dulceria(contadorIdDulceria++, nombre, precio, tipo, cantidad);
        listaDulceria.add(producto);

        System.out.println("Producto agregado exitosamente.");
    }

    public void modificarProductoDulceria(Scanner scanner) {
        System.out.println("\n=== MODIFICAR PRODUCTO ===");
        verProductosDulceria();

        System.out.print("Ingrese el ID del producto a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Dulceria producto = obtenerProductoDulceriaPorId(id);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("\nModificando producto: " + producto.getNombre());
        System.out.println("1. Nombre");
        System.out.println("2. Precio");
        System.out.println("3. Tipo");
        System.out.println("4. Cantidad en stock");
        System.out.print("Seleccione el campo a modificar: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nuevo nombre: ");
                producto.setNombre(scanner.nextLine());
                break;
            case 2:
                System.out.print("Nuevo precio: ");
                producto.setPrecio(scanner.nextDouble());
                break;
            case 3:
                System.out.print("Nuevo tipo: ");
                producto.setTipo(scanner.nextLine());
                break;
            case 4:
                System.out.print("Nueva cantidad en stock: ");
                producto.setCantidad(scanner.nextInt());
                producto.actualizarDisponibilidad();
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        System.out.println("Producto modificado exitosamente.");
    }

    public void eliminarProductoDulceria(Scanner scanner) {
        System.out.println("\n=== ELIMINAR PRODUCTO ===");
        verProductosDulceria();

        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();

        Dulceria productoAEliminar = obtenerProductoDulceriaPorId(id);
        if (productoAEliminar != null) {
            listaDulceria.remove(productoAEliminar);
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private Dulceria obtenerProductoDulceriaPorId(int id) {
        for (Dulceria producto : listaDulceria) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public void mostrarCartelera() {
        System.out.println("\n=== CARTELERA ACTUAL ===");
        for (Pelicula pelicula : listaPeliculas) {
            System.out.println("\nPelícula: " + pelicula.getTitulo());
            System.out.println("Clasificación: " + pelicula.getClasificacion());
            System.out.println("Duración: " + pelicula.getDuracion() + " minutos");
            System.out.println("Horarios disponibles:");

            for (Horario horario : pelicula.getHorarios()) {
                System.out.println("  " + horario.mostrarInfoHorario());
            }

            System.out.println("------------------------");
        }
    }

    public boolean mostrarHorariosDisponibles(int idPelicula) {
        Pelicula pelicula = obtenerPeliculaPorId(idPelicula);
        if (pelicula == null || pelicula.getHorarios().isEmpty()) {
            System.out.println("No hay horarios disponibles para esta película.");
            return false;
        }

        System.out.println("\nHorarios para " + pelicula.getTitulo() + ":");
        List<Horario> horarios = pelicula.getHorarios();
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println(i + ". " + horarios.get(i).mostrarInfoHorario());
        }
        return true;
    }

    public boolean mostrarSalasDisponibles(int idPelicula, int idHorario) {
        Pelicula pelicula = obtenerPeliculaPorId(idPelicula);
        if (pelicula == null) return false;

        Horario horario = pelicula.getHorarios().get(idHorario);
        boolean haySalasDisponibles = false;

        System.out.println("\nSalas disponibles:");
        for (Sala sala : listaSalas) {
            if (sala.getPeliculas().contains(pelicula) && sala.getHorarios().contains(horario)) {
                System.out.println("\nSala " + sala.getNumeroSala());
                sala.mostrarMatrizAsientos();
                haySalasDisponibles = true;
            }
        }

        return haySalasDisponibles;
    }

    private int calcularFilas(Sala sala) {
        return (int) Math.sqrt(sala.getCapacidad());
    }

    private int calcularColumnas(Sala sala) {
        return sala.getCapacidad() / calcularFilas(sala);
    }
    public void reservarAsiento(int numeroSala, String posicionAsiento, Cliente cliente) {
        Sala sala = obtenerSalaPorNumero(numeroSala);
        if (sala == null) {
            System.out.println("Sala no encontrada.");
            return;
        }

        char fila = posicionAsiento.toUpperCase().charAt(0);
        int columna = Integer.parseInt(posicionAsiento.substring(1));
        int filas = calcularFilas(sala);
        int columnas = calcularColumnas(sala);

        int indice = ((fila - 'A') * columnas) + (columna - 1);
        List<Asiento> asientos = sala.getAsientos();

        if (indice >= 0 && indice < asientos.size()) {
            Asiento asiento = asientos.get(indice);
            if (asiento.isDisponible()) {
                asiento.setDisponible(false);
                Reservacion reservacion = new Reservacion(cliente, null, Arrays.asList(asiento));
                listaReservaciones.add(reservacion);
                System.out.println("Asiento reservado exitosamente.");
            } else {
                System.out.println("El asiento no está disponible.");
            }
        } else {
            System.out.println("Posición de asiento inválida.");
        }
    }

    public void mostrarBoletosCliente(Cliente cliente) {
        boolean tieneBoletos = false;
        for (Boleto boleto : listaBoletos) {
            if (boleto.getCliente().getId().equals(cliente.getId())) {
                System.out.println(boleto.mostrarInfoBoleto());
                System.out.println("------------------------");
                tieneBoletos = true;
            }
        }
        if (!tieneBoletos) {
            System.out.println("No tiene boletos registrados.");
        }
    }

    public boolean tieneReservacionPendiente(Cliente cliente) {
        for (Reservacion reservacion : listaReservaciones) {
            if (reservacion.getCliente().getId().equals(cliente.getId()) && !reservacion.isConfirmada()) {
                return true;
            }
        }
        return false;
    }



    public void agregarProductoACompra(Cliente cliente, int idProducto, int cantidad) {
        Dulceria producto = obtenerProductoDulceriaPorId(idProducto);
        if (producto != null && producto.vender(cantidad)) {
            // Aquí se podra agregar el producto a un carrito temporal del cliente
            System.out.println("Producto agregado al carrito.");
        } else {
            System.out.println("No hay suficiente stock disponible.");
        }
    }

    public double calcularTotalCompra(Cliente cliente) {
        double total = 0;

        for (Reservacion reservacion : listaReservaciones) {
            if (reservacion.getCliente().getId().equals(cliente.getId()) && !reservacion.isConfirmada()) {
                for (Asiento asiento : reservacion.getAsientosReservados()) {
                    total += asiento.getPrecio();
                }
            }
        }
        return total;
    }

    public boolean aplicaDescuentoCumpleanos(Cliente cliente) {
        LocalDate fechaNacimiento = cliente.getFechaNacimiento();
        LocalDate hoy = LocalDate.now();

        return fechaNacimiento.getMonth() == hoy.getMonth();
    }

    public double aplicarDescuentoCumpleanos(Cliente cliente, double total) {
        // AQUI SE CALCULASI HAY UN DESCUENTO DEL CUMPLEAÑERO AL TIPO DE ASIENTO SELECCIONADO
        for (Reservacion reservacion : listaReservaciones) {
            if (reservacion.getCliente().getId().equals(cliente.getId()) && !reservacion.isConfirmada()) {
                for (Asiento asiento : reservacion.getAsientosReservados()) {
                    if (asiento.getTipo().equals("PREMIUM")) {
                        total -= asiento.getPrecio() * 0.60; // 60% DE DESCUENTO
                    } else if (asiento.getTipo().equals("VIP")) {
                        total -= asiento.getPrecio() * 0.35; // 35% DE DESCUENTO
                    }
                }
            }
        }
        return total;
    }

    public boolean procesarPago(Cliente cliente, int metodoPago, double total) {
        String tipoPago;
        switch (metodoPago) {
            case 1:
                tipoPago = "Tarjeta de crédito";
                break;
            case 2:
                tipoPago = "Tarjeta de débito";
                break;
            case 3:
                tipoPago = "Efectivo";
                break;
            default:
                tipoPago = "Desconocido";
                break;
        }

        Compra compra = new Compra(
                contadorIdCompra++,
                total,
                new Date(),
                tipoPago,
                new ArrayList<>(),
                new ArrayList<>()
        );

        // SE CONFIRMA LA COMPRA Y GENERA TUS BOLETOS
        for (Reservacion reservacion : listaReservaciones) {
            if (reservacion.getCliente().getId().equals(cliente.getId()) && !reservacion.isConfirmada()) {
                reservacion.confirmarReservacion();
                for (Asiento asiento : reservacion.getAsientosReservados()) {
                    Boleto boleto = new Boleto(
                            contadorIdBoleto++,
                            reservacion.getPelicula(),
                            obtenerSalaDeAsiento(asiento),
                            asiento.getPrecio(),
                            asiento,
                            cliente,
                            aplicaDescuentoCumpleanos(cliente)
                    );
                    listaBoletos.add(boleto);
                    compra.getBoletos().add(boleto);
                }
            }
        }

        listaCompras.add(compra);
        return true;
    }

    private Sala obtenerSalaDeAsiento(Asiento asiento) {
        for (Sala sala : listaSalas) {
            if (sala.getAsientos().contains(asiento)) {
                return sala;
            }
        }
        return null;
    }

    public Sala obtenerSalaPorNumero(int numeroSala) {
        for (Sala sala : listaSalas) {
            if (sala.getNumeroSala() == numeroSala) {
                return sala;
            }
        }
        return null;
    }


    public void modificarSala(Scanner scanner) {
        System.out.println("\n=== MODIFICAR SALA ===");
        verSalas();

        System.out.print("Ingrese el número de la sala a modificar: ");
        int numeroSala = scanner.nextInt();

        Sala sala = null;
        for (Sala s : listaSalas) {
            if (s.getNumeroSala() == numeroSala) {
                sala = s;
                break;
            }
        }

        if (sala == null) {
            System.out.println("Sala no encontrada.");
            return;
        }

        System.out.println("\n1. Modificar capacidad");
        System.out.println("2. Modificar películas y horarios");
        System.out.println("3. Modificar distribución de asientos");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nueva capacidad: ");
                int nuevaCapacidad = scanner.nextInt();
                sala.setCapacidad(nuevaCapacidad);
                break;
            case 2:
                modificarPeliculasHorariosSala(scanner, sala);
                break;
            case 3:
                modificarDistribucionAsientos(scanner, sala);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public void asignarPeliculaASala(Scanner scanner) {
        System.out.println("\n=== ASIGNAR PELÍCULA A SALA ===");
        verSalas();

        System.out.print("Ingrese el número de sala: ");
        int numeroSala = scanner.nextInt();
        scanner.nextLine();

        Sala sala = null;
        for (Sala s : listaSalas) {
            if (s.getNumeroSala() == numeroSala) {
                sala = s;
                break;
            }
        }

        if (sala == null) {
            System.out.println("Sala no encontrada.");
            return;
        }

        // MOSTRAR CARTELERA
        System.out.println("\nPelículas disponibles:");
        for (Pelicula pelicula : listaPeliculas) {
            System.out.println(pelicula.getId() + ". " + pelicula.getTitulo());
        }

        System.out.print("Ingrese el ID de la película a asignar: ");
        int idPelicula = scanner.nextInt();
        scanner.nextLine();

        Pelicula pelicula = obtenerPeliculaPorId(idPelicula);
        if (pelicula == null) {
            System.out.println("Película no encontrada.");
            return;
        }

        sala.getPeliculas().add(pelicula);

        // MOSTRAR HORARIOS
        System.out.println("\nHorarios disponibles para " + pelicula.getTitulo() + ":");
        List<Horario> horariosDisponibles = pelicula.getHorarios();
        for (int i = 0; i < horariosDisponibles.size(); i++) {
            System.out.println(i + ". " + horariosDisponibles.get(i).mostrarInfoHorario());
        }

        System.out.println("Seleccione los horarios a asignar (separados por coma):");
        String[] seleccionHorarios = scanner.nextLine().split(",");

        List<Horario> horariosSeleccionados = new ArrayList<>();
        for (String indice : seleccionHorarios) {
            try {
                int idx = Integer.parseInt(indice.trim());
                if (idx >= 0 && idx < horariosDisponibles.size()) {
                    horariosSeleccionados.add(horariosDisponibles.get(idx));
                }
            } catch (NumberFormatException e) {
                System.out.println("Índice inválido: " + indice);
            }
        }

        sala.getHorarios().addAll(horariosSeleccionados);

        System.out.println("Película y horarios asignados exitosamente a la sala.");
    }

    private void modificarPeliculasHorariosSala(Scanner scanner, Sala sala) {
        while (true) {
            System.out.println("\n1. Agregar película");
            System.out.println("2. Eliminar película");
            System.out.println("3. Modificar horarios");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nPelículas disponibles:");
                    for (Pelicula p : listaPeliculas) {
                        if (!sala.getPeliculas().contains(p)) {
                            System.out.println(p.getId() + ". " + p.getTitulo());
                        }
                    }
                    System.out.print("Ingrese el ID de la película a agregar: ");
                    int idPelicula = scanner.nextInt();
                    Pelicula pelicula = obtenerPeliculaPorId(idPelicula);
                    if (pelicula != null) {
                        sala.agregarPelicula(pelicula);
                        System.out.println("Película agregada exitosamente.");
                    }
                    break;

                case 2:
                    System.out.println("\nPelículas en la sala:");
                    List<Pelicula> peliculasSala = sala.getPeliculas();
                    for (int i = 0; i < peliculasSala.size(); i++) {
                        System.out.println(i + ". " + peliculasSala.get(i).getTitulo());
                    }
                    System.out.print("Ingrese el índice de la película a eliminar: ");
                    int indice = scanner.nextInt();
                    if (indice >= 0 && indice < peliculasSala.size()) {
                        sala.eliminarPelicula(peliculasSala.get(indice));
                        System.out.println("Película eliminada exitosamente.");
                    }
                    break;

                case 3:
                    modificarHorariosSala(scanner, sala);
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void modificarDistribucionAsientos(Scanner scanner, Sala sala) {
        System.out.print("Número de filas: ");
        int filas = scanner.nextInt();

        System.out.print("Número de columnas: ");
        int columnas = scanner.nextInt();
        scanner.nextLine();

        if (filas * columnas != sala.getCapacidad()) {
            System.out.println("Error: La distribución no coincide con la capacidad total.");
            return;
        }

        sala.setFilas(filas);
        sala.setColumnas(columnas);

        List<Asiento> nuevosAsientos = new ArrayList<>();
        int asientosVIP = 0;
        int asientosPremium = 0;

        for (int i = 0; i < filas; i++) {
            System.out.println("Fila " + (char)('A' + i) + " - Tipo (V: VIP, P: Premium, R: Regular):");
            String tipo = scanner.nextLine().toUpperCase();

            for (int j = 0; j < columnas; j++) {
                String tipoAsiento;
                switch (tipo) {
                    case "V":
                        tipoAsiento = "VIP";
                        asientosVIP++;
                        break;
                    case "P":
                        tipoAsiento = "PREMIUM";
                        asientosPremium++;
                        break;
                    default:
                        tipoAsiento = "REGULAR";
                }
                nuevosAsientos.add(new Asiento((i * columnas) + j + 1, tipoAsiento));
            }
        }

        sala.setAsientos(nuevosAsientos);
        sala.setCantidadAsientosVip(asientosVIP);
        sala.setCantidadAsientosPremium(asientosPremium);

        System.out.println("Distribución de asientos actualizada exitosamente.");
    }

    private void modificarHorariosSala(Scanner scanner, Sala sala) {
        while (true) {
            System.out.println("\nHorarios actuales:");
            List<Horario> horarios = sala.getHorarios();
            for (int i = 0; i < horarios.size(); i++) {
                System.out.println(i + ". " + horarios.get(i).mostrarInfoHorario());
            }

            System.out.println("\n1. Agregar horario");
            System.out.println("2. Eliminar horario");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Hora de inicio (HH:mm): ");
                        String horaInicio = scanner.nextLine();
                        Date inicio = new SimpleDateFormat("HH:mm").parse(horaInicio);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(inicio);
                        cal.add(Calendar.MINUTE, 120);
                        Date fin = cal.getTime();

                        Horario horario = new Horario(inicio, fin);
                        sala.getHorarios().add(horario);
                        System.out.println("Horario agregado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("Error al ingresar el horario.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el número del horario a eliminar: ");
                    int indice = scanner.nextInt();
                    if (indice >= 0 && indice < horarios.size()) {
                        horarios.remove(indice);
                        System.out.println("Horario eliminado exitosamente.");
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }


    public void mostrarProductosDulceria() {
        if (listaDulceria.isEmpty()) {
            System.out.println("No hay productos disponibles en la dulcería.");
            return;
        }

        System.out.println("\n=== PRODUCTOS DE DULCERÍA ===");
        for (Dulceria producto : listaDulceria) {
            if (producto.isDisponible()) {
                System.out.println("\nID: " + producto.getId());
                System.out.println("Producto: " + producto.getNombre());
                System.out.println("Precio: $" + producto.getPrecio());
                System.out.println("Tipo: " + producto.getTipo());
                System.out.println("Stock disponible: " + producto.getCantidad());
                System.out.println("------------------------");
            }
        }
    }

    public void generarBoletos(Cliente cliente) {
        // BUSCA LAS RESERVACIONES DEL CLIENTE
        for (Reservacion reservacion : listaReservaciones) {
            if (reservacion.getCliente().getId().equals(cliente.getId()) && !reservacion.isConfirmada()) {
                // GENERA BOLERTOS PARA UN ASIENTO RESERVADO
                for (Asiento asiento : reservacion.getAsientosReservados()) {
                    double precio = asiento.getPrecio();

                    // APLICA EL DESCUENTO CUMPLAÑERO
                    if (aplicaDescuentoCumpleanos(cliente)) {
                        if (asiento.getTipo().equals("PREMIUM")) {
                            precio *= 0.4; // 60% descuento
                        } else if (asiento.getTipo().equals("VIP")) {
                            precio *= 0.65; // 35% descuento
                        }
                    }

                    Boleto boleto = new Boleto(
                            contadorIdBoleto++,
                            reservacion.getPelicula(),
                            reservacion.getSala(),
                            precio,
                            asiento,
                            cliente,
                            aplicaDescuentoCumpleanos(cliente)
                    );

                    listaBoletos.add(boleto);
                    System.out.println("\nBoleto generado:");
                    System.out.println(boleto.mostrarInfoBoleto());
                }

                // Marcar la reservación como confirmada
                reservacion.confirmarReservacion();
            }
        }
    }

    public void mostrarPeliculasDisponibles() {
        if (listaPeliculas.isEmpty()) {
            System.out.println("No hay películas disponibles en cartelera.");
            return;
        }

        System.out.println("\n=== PELÍCULAS DISPONIBLES ===");
        for (Pelicula pelicula : listaPeliculas) {
            System.out.println("\nID: " + pelicula.getId());
            System.out.println("Título: " + pelicula.getTitulo());
            System.out.println("Género: " + pelicula.getGenero());
            System.out.println("Clasificación: " + pelicula.getClasificacion());
            System.out.println("Duración: " + pelicula.getDuracion() + " minutos");
            System.out.println("Horarios disponibles:");
            for (Horario horario : pelicula.getHorarios()) {
                System.out.println("  " + horario.mostrarInfoHorario());
            }
            System.out.println("------------------------");
        }
    }


    public Cliente buscarCliente(String nombre, String contraseña) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNombre().equals(nombre) && cliente.getContraseña().equals(contraseña)) {
                return cliente;
            }
        }
        return null;
    }

    public String generarIDCliente() {
        Random random = new Random();
        LocalDate fecha = LocalDate.now();
        int anoActual = fecha.getYear();
        int mesActual = fecha.getMonthValue();
        int numeroAleatorio = random.nextInt(100000) + 1;
        return String.format("C%d%d%05d", anoActual, mesActual, numeroAleatorio);
    }

    public Admin buscarAdmin(String nombre, String contraseña) {
        for (Admin admin : listaAdmins) {
            if (admin.getName().equals(nombre) && admin.getPassword().equals(contraseña)) {
                return admin;
            }
        }
        return null;
    }

    public String generarIDAdmin() {
        Random random = new Random();
        LocalDate fecha = LocalDate.now();
        int anoActual = fecha.getYear();
        int mesActual = fecha.getMonthValue();
        int numeroAleatorio = random.nextInt(10000) + 1;
        return String.format("A%d%d%04d", anoActual, mesActual, numeroAleatorio);
    }

    public boolean crearReservacion(Cliente cliente, Pelicula pelicula, Sala sala, 
                              Horario horario, List<String> posicionesAsientos) {
    List<Asiento> asientosReservados = new ArrayList<>();
    double totalReservacion = 0.0;
    
    // Primera verificación de disponibilidad
    for (String posicion : posicionesAsientos) {
        char fila = posicion.charAt(0);
        int columna = Integer.parseInt(posicion.substring(1));
        Asiento asiento = sala.obtenerAsiento(fila, columna);
        
        if (asiento == null || !asiento.isDisponible()) {
            System.out.println("El asiento " + posicion + " ya no está disponible.");
            // Liberar los asientos que ya se habían reservado
            for (Asiento asientoReservado : asientosReservados) {
                asientoReservado.setDisponible(true);
            }
            return false;
        }
        asientosReservados.add(asiento);
    }
    
    // Si todos los asientos están disponibles, proceder con la reservación
    for (Asiento asiento : asientosReservados) {
        asiento.setDisponible(false);
        double precioAsiento = asiento.getPrecio();
        if (esmesDeCumpleanos(cliente)) {
            if (asiento.getTipo().equals("PREMIUM")) {
                precioAsiento *= 0.4; // 60% descuento
            } else if (asiento.getTipo().equals("VIP")) {
                precioAsiento *= 0.65; // 35% descuento
            }
        }
        totalReservacion += precioAsiento;
    }
    
    Reservacion reservacion = new Reservacion(cliente, pelicula, asientosReservados);
    reservacion.setSala(sala);
    reservacion.setHorario(horario);
    listaReservaciones.add(reservacion);
    
    return true;
}

    private boolean esmesDeCumpleanos(Cliente cliente) {
        return cliente.getFechaNacimiento().getMonth() == LocalDate.now().getMonth();
    }

    private Asiento obtenerAsientoPorPosicion(Sala sala, String posicion) {
        char fila = posicion.toUpperCase().charAt(0);
        int columna = Integer.parseInt(posicion.substring(1));
        return sala.obtenerAsiento(fila, columna);
    }

    public void mostrarResumenReservacion(Cliente cliente) {
        System.out.println("\n=== RESUMEN DE RESERVACIONES ===");
        double totalGeneral = 0.0;

        for (Reservacion reservacion : listaReservaciones) {
            if (reservacion.getCliente().getId().equals(cliente.getId()) && !reservacion.isConfirmada()) {
                System.out.println("\nPelícula: " + reservacion.getPelicula().getTitulo());
                System.out.println("Sala: " + reservacion.getSala().getNumeroSala());
                System.out.println("Horario: " + reservacion.getHorario().mostrarInfoHorario());
                System.out.println("Asientos reservados:");

                double subtotal = 0.0;
                for (Asiento asiento : reservacion.getAsientosReservados()) {
                    double precioAsiento = asiento.getPrecio();
                    if (esmesDeCumpleanos(cliente)) {
                        if (asiento.getTipo().equals("PREMIUM")) {
                            precioAsiento *= 0.4; // 60% descuento
                            System.out.println("  " + asiento.getNumero() + " (PREMIUM - Descuento cumpleaños 60%)");
                        } else if (asiento.getTipo().equals("VIP")) {
                            precioAsiento *= 0.65; // 35% descuento
                            System.out.println("  " + asiento.getNumero() + " (VIP - Descuento cumpleaños 35%)");
                        } else {
                            System.out.println("  " + asiento.getNumero() + " (REGULAR)");
                        }
                    } else {
                        System.out.println("  " + asiento.getNumero() + " (" + asiento.getTipo() + ")");
                    }
                    subtotal += precioAsiento;
                }
                System.out.println("Subtotal: $" + String.format("%.2f", subtotal));
                totalGeneral += subtotal;
            }
        }
        System.out.println("\nTOTAL GENERAL: $" + String.format("%.2f", totalGeneral));
    }

    public Horario obtenerHorarioPorId(Pelicula pelicula, int idHorario) {
        if (pelicula != null && idHorario >= 0 && idHorario < pelicula.getHorarios().size()) {
            return pelicula.getHorarios().get(idHorario);
        }
        return null;
    }

    public boolean verificarDisponibilidadAsiento(Sala sala, String posicionAsiento) {
    try {
        char fila = posicionAsiento.charAt(0);
        int columna = Integer.parseInt(posicionAsiento.substring(1));
        Asiento asiento = sala.obtenerAsiento(fila, columna);
        return asiento != null && asiento.isDisponible();
    } catch (Exception e) {
        System.out.println("Formato de posición de asiento inválido.");
        return false;
    }
}
}