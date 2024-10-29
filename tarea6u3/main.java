public class Main {
    public static void main(String[] args) {
        Envio envioNacional = new EnvioNacional(300, 6.0, 600);
        envioNacional.validarPeso();
        System.out.println(envioNacional.calcularTiempoYCostoEntrega());

        System.out.println("\n ********************* \n");

        Envio envioInternacional = new EnvioInternacional(500,12.0,"España");
        envioInternacional.validarPeso();
        System.out.println(envioInternacional.calcularTiempoYCostoEntrega());
    }
}