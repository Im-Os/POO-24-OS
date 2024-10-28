package horario;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Horario {
    private Date inicio;
    private Date fin;
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public Horario(Date inicio, Date fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String mostrarInfoHorario() {
        return "Inicio: " + timeFormat.format(inicio) + " - Fin: " + timeFormat.format(fin);
    }
}
