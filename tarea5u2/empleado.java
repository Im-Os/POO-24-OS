public class Empleado {
    private double sueldoBase;
    private double bonificacion;
    private int horasExtras;

    // Constructor
    public Empleado(double sueldoBase, double bonificacion, int horasExtras) {
        this.sueldoBase = sueldoBase;
        this.bonificacion = bonificacion;
        this.horasExtras = horasExtras;
    }

    // Método para calcular el salario (solo sueldoBase)
    public double calcularSalario(double sueldoBase) {
        return sueldoBase;
    }

    // Método para calcular el salario (sueldoBase y bonificación)
    public double calcularSalario(double sueldoBase, double bonificacion) {
        return sueldoBase + bonificacion;
    }

    // Método para calcular el salario (sueldoBase, bonificación y horasExtras)
    public double calcularSalario(double sueldoBase, double bonificacion, int horasExtras) {
        return sueldoBase + bonificacion + (horasExtras * 20);
    }
}
