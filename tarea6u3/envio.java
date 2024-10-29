public abstract class Envio {
    public double costo;
    public double peso;
 
     public Envio(double costo, double peso) {
         this.costo = costo;
         this.peso = peso;
     }
 
     public double getCosto() {
         return costo;
     }
 
     public double getPeso() {
         return peso;
     }
 
     public abstract String calcularTiempoYCostoEntrega();
 
     public void validarPeso(){
         if(peso<=0){
             System.out.println("Peso invalido");
         }
         else{
             System.out.println("Peso valido: " + peso);
         }
     }
 }