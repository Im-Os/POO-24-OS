package consultorios;

public class Consultorio {
    public String id;
    public int piso;
    public int numeroConsultorio;




    public Consultorio(String id, int piso, int numeroConsultorio) {
        this.id = id;
        this.piso = piso;
        this.numeroConsultorio = numeroConsultorio;
    }



    public String getId() {
        return id;
    }

    public int getPiso() {
        return piso;
    }

    public int getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public void setNumeroConsultorio(int numeroConsultorio) {
        this.numeroConsultorio = numeroConsultorio;
    }

    public String mostrarDatos(){
        String datos = String.format("ID: %s, Numero de Piso: %d, Numero de Consultorio: %d ", id, piso, numeroConsultorio);
        return datos;
    }
}
