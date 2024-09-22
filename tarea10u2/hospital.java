package hospital;

import consultas.Consulta;
import consultorios.Consultorio;
import medicos.Medico;
import paciente.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Hospital {

    public ArrayList<Paciente> listaPacientes = new ArrayList<>();
    public ArrayList<Medico> listaMedicos = new ArrayList<>();
    public ArrayList<Consulta> listaConsultas = new ArrayList<>();
    public ArrayList<Consultorio> listaConsultorios = new ArrayList<>();

    private ValidadorHospital validador = new ValidadorHospital();

    public void registrarPaciente(Paciente paciente) {
        this.listaPacientes.add(paciente);
    }

    public void registrarMedico(Medico medico) {
        this.listaMedicos.add(medico);
    }

    public void registrarConsultorio(Consultorio consultorio) {
        this.listaConsultorios.add(consultorio);
    }

    public void mostrarPacientes() {
        System.out.println("Lista de pacientes: ");
        for (Paciente paciente : this.listaPacientes) {
            System.out.println(paciente.mostrarDatos());
        }
    }

    public void registrarConsulta(Consulta consulta) {
        if (!validador.validarDisponibilidadFechaConsulta(consulta.getFechaHora(), consulta.getConsultorio().getNumeroConsultorio(), this.listaConsultas)) {
            System.out.println("Ya existe una consulta registrada para esa fecha");
            return;
        }

        if (!validador.validarDisponibilidadMedico(consulta.getFechaHora(), consulta.getMedico().getId(), this.listaConsultas)) {
            System.out.println("El médico no tiene disponibilidad para esa fecha");
            return;
        }

        this.listaConsultas.add(consulta);
    }

    public String generarIdPaciente() {
        Random random = new Random();
        LocalDate fecha = LocalDate.now();
        int anoActual = fecha.getYear();
        int mesActual = fecha.getMonthValue();
        int longitudPacientesMasUno = this.listaPacientes.size() + 1;
        int numeroAleatorio = random.nextInt(1, 100000);

        return String.format("P%d%d%d%d", anoActual, mesActual, longitudPacientesMasUno, numeroAleatorio);
    }

    public void mostrarPacientePorId(String id) {
        Optional<Paciente> pacienteEncontrado = this.listaPacientes.stream().filter(paciente -> paciente.getId()).findFirst();

        if (pacienteEncontrado.isPresent()) {
            System.out.println(pacienteEncontrado.get().mostrarDatos());
        } else {
            System.out.println("Paciente no encontrado");
        }
    }

    public String generarIdMedico(String apellido, int anoNacimiento) {
        Random random = new Random();
        LocalDate fecha = LocalDate.now();
        int anoActual = fecha.getYear();
        int ultimoDigitoAnoNacimiento = anoNacimiento % 10;
        int numeroAleatorio = random.nextInt(700000 - 50) + 50;
        int longitudMedicosMasUno = this.listaMedicos.size() + 1;

        return String.format("M-%s-%d-%d-%d-%d", apellido.substring(0, 2).toUpperCase(), ultimoDigitoAnoNacimiento, anoActual, numeroAleatorio, longitudMedicosMasUno);
    }

    public String generarIdConsultorio() {
        Random random = new Random();
        LocalDate fecha = LocalDate.now();
        int diaActual = fecha.getDayOfMonth();
        int anoActual = fecha.getYear();
        int numeroAleatorio = random.nextInt(500000 - 1) + 1;
        int longitudConsultoriosMasUno = this.listaConsultorios.size() + 1;

        return String.format("C-%d-%d-%d-%d", longitudConsultoriosMasUno, diaActual, anoActual, numeroAleatorio);
    }

    public void mostrarMedicos() {
        System.out.println("Lista de médicos: ");
        for (Medico medico : this.listaMedicos) {
            System.out.println(medico.mostrarDatos());
        }
    }

    public void mostrarConsultorios() {
        System.out.println("Lista de consultorios: ");
        for (Consultorio consultorio : this.listaConsultorios) {
            System.out.println(consultorio.mostrarDatos());
        }
    }

    public void mostrarMedicoPorId(String id) {
        Optional<Medico> medicoEncontrado = this.listaMedicos.stream().filter(medico -> medico.getId()).findFirst();

        if (medicoEncontrado.isPresent()) {
            System.out.println(medicoEncontrado.get().mostrarDatos());
        } else {
            System.out.println("Médico no encontrado");
        }
    }

    public void mostrarConsultorioPorId(String id) {
        Optional<Consultorio> consultorioEncontrado = this.listaConsultorios.stream().filter(consultorio -> consultorio.getId()).findFirst();

        if (consultorioEncontrado.isPresent()) {
            System.out.println(consultorioEncontrado.get().mostrarDatos());
        } else {
            System.out.println("Consultorio no encontrado");
        }
    }
}
