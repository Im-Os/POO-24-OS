// GestorSeguros.java
public class GestorSeguros {
    public static void main(String[] args) {
        Seguro seguroAuto = new SeguroAuto("Juan Pérez", 20000, 24);
        Seguro seguroHogar = new SeguroHogar("Ana Gómez", 300000, true);
        Seguro seguroVida = new SeguroVida("Carlos Ruiz", 100000, 65);

        System.out.println(seguroAuto.detallesSeguro());
        System.out.println();
        System.out.println(seguroHogar.detallesSeguro());
        System.out.println();
        System.out.println(seguroVida.detallesSeguro());
    }
}