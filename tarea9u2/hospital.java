package hospital;

import consultas.Consulta;
import consultorios.Consultorio;
import medicos.Medico;
import paciente.Paciente;

import java.util.ArrayList;

public class Hospital {

    public ArrayList<Paciente> listaPacientes = new ArrayList<>();

    public ArrayList<Medico> listaMedicos = new ArrayList<>();

    public ArrayList<Consulta> listaConsultas = new ArrayList<>();

    public ArrayList<Consultorio> listaConsultorios = new ArrayList<>();

    cpublic void registrarPaciente(Paciente paciente) {
        this.listaPacientes.add(paciente);

    }

    public void registrarMedico(Medico medico) {
        this.listaMedicos.add(medico);
    }

    public void registrarConsultorio(Consultorio consultorio) {
        this.listaConsultorios.add(consultorio);
    }

    public void registrarConsulta(Consulta consulta) {

        if (!validarDisponibilidadFechaConsulta(consulta.getFechaHora(), consulta.getConsultorio().getNumeroConsultorio())){
            System.out.println("Ya existe una consulta registrada para esa fecha");
            return;
        }

        this.listaConsultas.add(consulta);
    }

    private boolean validarDisponibilidadFechaConsulta(String fechaDeseada, int numeroConsultorio) {
        for (Consulta consulta : this.listaConsultas) {
            if (consulta.getFechaHora().equals(fechaDeseada) && numeroConsultorio == consulta.getConsultorio().getNumeroConsultorio()) {
                return false;
            }
        }
        return true;
    }

}
