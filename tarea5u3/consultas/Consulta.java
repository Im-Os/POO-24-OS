package consultas;

import consultorios.Consultorio;
import medicos.Medico;
import paciente.Paciente;

import java.time.LocalDateTime;

public class Consulta {
    public String id;

    public LocalDateTime fechaHora;

    public Paciente paciente;

    public Medico medico;

    public Consultorio consultorio;

    public Consulta(String id, LocalDateTime fechaHora, Paciente paciente, Medico medico, Consultorio consultorio) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.medico = medico;
        this.consultorio = consultorio;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
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
    public String mostrarConsulta() {
        return String.format("Fecha: %s, Paciente: %s, Medico: %s, Num.Consultorio: %d", fechaHora, paciente, medico, consultorio);
    }

    public String mostrarInformacion() {
        String info = String.format("\nId: %s, Fecha: %s, IdPaciente: %s, Nombre del Paciente: %s, IdMedico: %s, Nombre del Medico: %s,Piso Consultorio: %d, Numero Consultorio; %d", id, fechaHora, paciente.getId(), paciente.getNombre(), medico.getId(), medico.getNombre(), consultorio.getPiso(), consultorio.getNumeroConsultorio() );
        return info;
    }
}