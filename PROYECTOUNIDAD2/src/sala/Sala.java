package sala;

import asiento.Asiento;
import horario.Horario;
import pelicula.Pelicula;
import java.util.List;
import java.util.ArrayList;

public class Sala {
    private int numeroSala;
    private int capacidad;
    private int cantidadAsientosVip;
    private int cantidadAsientosPremium;
    private List<Asiento> asientos;
    private List<Pelicula> peliculas;
    private List<Horario> horarios;
    private int filas;
    private int columnas;

    public Sala(int numeroSala, int capacidad, int cantidadAsientosVip, int cantidadAsientosPremium,
                List<Asiento> asientos, List<Pelicula> peliculas, List<Horario> horarios) {
        this.numeroSala = numeroSala;
        this.capacidad = capacidad;
        this.cantidadAsientosVip = cantidadAsientosVip;
        this.cantidadAsientosPremium = cantidadAsientosPremium;
        this.asientos = asientos != null ? asientos : new ArrayList<>();
        this.peliculas = peliculas != null ? peliculas : new ArrayList<>();
        this.horarios = horarios != null ? horarios : new ArrayList<>();
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCantidadAsientosVip() {
        return cantidadAsientosVip;
    }

    public void setCantidadAsientosVip(int cantidadAsientosVip) {
        this.cantidadAsientosVip = cantidadAsientosVip;
    }

    public int getCantidadAsientosPremium() {
        return cantidadAsientosPremium;
    }

    public void setCantidadAsientosPremium(int cantidadAsientosPremium) {
        this.cantidadAsientosPremium = cantidadAsientosPremium;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void seleccionarAsiento(Asiento asiento) {
        if (asiento.isDisponible()) {
            asiento.setDisponible(false);
        } else {
            System.out.println("El asiento " + asiento.getNumero() + " ya está reservado o vendido.");
        }
    }

    public void agregarPelicula(Pelicula pelicula) {
        this.peliculas.add(pelicula);
    }

    public void eliminarPelicula(Pelicula pelicula) {
        this.peliculas.remove(pelicula);
    }

    public String mostrarInfoSala() {
        StringBuilder info = new StringBuilder();
        info.append("Sala Nº: ").append(getNumeroSala()).append("\n")
                .append("Capacidad: ").append(getCapacidad()).append("\n")
                .append("Cantidad de Asientos VIP: ").append(getCantidadAsientosVip()).append("\n")
                .append("Cantidad de Asientos Premium: ").append(getCantidadAsientosPremium()).append("\n")
                .append("Distribución: ").append(filas).append(" filas x ").append(columnas).append(" columnas\n")
                .append("Películas y Horarios:\n");

        for (Pelicula pelicula : peliculas) {
            info.append(pelicula.toString()).append("\n");
            for (Horario horario : horarios) {
                if (pelicula.getHorarios().contains(horario)) {
                    info.append("\t").append(horario.mostrarInfoHorario()).append("\n");
                }
            }
        }

        return info.toString();
    }

    public Asiento obtenerAsiento(char fila, int columna) {
        int indice = ((fila - 'A') * this.columnas) + (columna - 1);
        if (indice >= 0 && indice < asientos.size()) {
            return asientos.get(indice);
        }
        return null;
    }

    public boolean esAsientoDisponible(char fila, int columna) {
        Asiento asiento = obtenerAsiento(fila, columna);
        return asiento != null && asiento.isDisponible();
    }

    public void mostrarMatrizAsientos() {
        System.out.println("PANTALLA");
        for (int i = 0; i < columnas * 4; i++) {
            System.out.print("-");
        }
        System.out.println();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int index = i * columnas + j;
                if (index < asientos.size()) {
                    Asiento asiento = asientos.get(index);
                    String marca;
                    if (asiento.getTipo().equals("VIP")) {
                        marca = "V";
                    } else if (asiento.getTipo().equals("PREMIUM")) {
                        marca = "P";
                    } else {
                        marca = "R";
                    }
                    String disponibilidad = asiento.isDisponible() ? " " : "X";
                    System.out.printf("%c%d%s%s ", (char)('A' + i), j + 1, marca, disponibilidad);
                }
            }
            System.out.println();
        }

        System.out.println("\nLeyenda:");
        System.out.println("V: VIP    P: Premium    R: Regular");
        System.out.println("X: Ocupado    [espacio]: Disponible");
    }
}