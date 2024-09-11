// Clase Coche
public class Coche {
    private String marca;
    private String modelo;
    private int año;

    // Constructor
    public Coche(String marca, String modelo, int año) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
    }

    // Método para mostrar la información del coche
    public void mostrarInformacion() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Año: " + año);
    }

    // Método para calcular la edad del coche
    public int calcularEdadDelCoche(int añoActual) {
        return añoActual - año;
    }

    public static void main(String[] args) {
        // Crear objetos de tipo Coche
        Coche coche1 = new Coche("Toyota", "Corolla", 2015);
        Coche coche2 = new Coche("Honda", "Civic", 2018);
        Coche coche3 = new Coche("Ford", "Focus", 2020);

        // Mostrar información de cada coche
        coche1.mostrarInformacion();
        System.out.println("Edad del coche: " + coche1.calcularEdadDelCoche(2024) + " años\n");

        coche2.mostrarInformacion();
        System.out.println("Edad del coche: " + coche2.calcularEdadDelCoche(2024) + " años\n");

        coche3.mostrarInformacion();
        System.out.println("Edad del coche: " + coche3.calcularEdadDelCoche(2024) + " años\n");
    }
}
