package consultas;

import consultorios.Consultorio;
import medicos.Medico;
import paciente.Paciente;

public class Consulta {
    public int id;

    public String fechaHora;

    public Paciente paciente;

    public Medico medico;

    public Consultorio consultorio;

    public Consulta(int id, String fechaHora, Paciente paciente, Medico medico, Consultorio consultorio) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.medico = medico;
        this.consultorio = consultorio;
    }

    public int getId() {
        return id;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }
}
