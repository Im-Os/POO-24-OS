package hospital;

import consultas.Consulta;
import consultorios.Consultorio;
import medicos.Medico;
import paciente.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public void registrarConsulta(Consulta consulta) {
        // paciente no tenga consulta en esa fecha

        if (!validador.validarDispolnibilidadEnFechaConsulta(consulta.getFechaHora(), consulta.getConsultorio().getNumeroConsultorio(), this.listaConsultas)) {
            System.out.println("Ya existe una consulta registrada par esa fecha");
            return;
        }


        if (!validador.validarDisponibilidadMedico(consulta.getFechaHora(), consulta.getMedico().getId(), this.listaConsultas)) {
            System.out.println("El medico no tiene disponibilidad en esa fecha");
            return;
        }

        this.listaConsultas.add(consulta);
    }


    public void registrarConsultorios(Consultorio consultorio) {
        this.listaConsultorios.add(consultorio);
    }

    public void mostrarPacientes() {
        for (Paciente paciente : this.listaPacientes) {
            System.out.println(paciente.mostrarDatos());
        }
    }

    public void mostrarMedicos() {
        for (Medico medico : this.listaMedicos) {
            System.out.println(medico.mostrarDatos());
        }
    }

    public void mostrarConsultorios() {
        for (Consultorio consultorio : this.listaConsultorios) {
            System.out.println("ID: " + (consultorio.getId()));
            System.out.println("Piso: " + (consultorio.getPiso()));
            System.out.println("Numero de consultorio: " + (consultorio.getNumeroConsultorio()));
            System.out.println(" ");
        }
    }

    public String generarIDPaciente() {

        Random random = new Random();
        LocalDate fecha = LocalDate.now();
        int anoActual = fecha.getYear();
        int mesActual = fecha.getMonthValue();
        int longitudPacientesMasUno = this.listaPacientes.size() + 1;
        int numeroAleatorio = random.nextInt(100000)+1;

        String id = String.format("P%d%d%d%d", anoActual, mesActual, longitudPacientesMasUno, numeroAleatorio);
        return id;

    }

    public Paciente obtenerPacientePorId(String Paciente){
        return listaPacientes.stream().filter(p -> p.getId().equals(Paciente)).findFirst().orElse(null);
    }
    public void mostrarPacientePorId(String id) {

        for (Paciente paciente : this.listaPacientes) {
            if (paciente.getId().equals(id)) {
                System.out.println(paciente.mostrarDatos());
                return;
            }
        }
        System.out.println("Paciente no encontrado");

    }

    public String generarIDMedico(int anioMedico, String apellido) {
        //M-{Primeras 2 letras de su apellido} - {ultimo dígito de su año de nacimiento} - {año actual} - {numero aleatorio entre 50 y 700000} - {longitud de la lista de medicos + 1}
        int numeroAleatorio = new Random().nextInt(700000+50)+50;
        return String.format("M-%d-%d", anioMedico, numeroAleatorio);

    }
    public Medico obtenerMedicoPorId(String idMedico) {
        return listaMedicos.stream().filter(p -> p.getId().equals(idMedico)).findFirst().get();
    }

    public void mostrarMedicoPorID(String id) {
        for(Medico medico : this.listaMedicos){
            if (medico.getId().equals(id)) {
                System.out.println(medico.mostrarDatos());
                return;
            }
        }
        System.out.println("Medico no encontrado");
    }


    public String generarIDConsultorio(){
      int diaActual = LocalDate.now().getDayOfMonth();
      int anioActual = LocalDate.now().getDayOfMonth();
      int numeroAleatorio = new Random().nextInt(500000-1)+1;
      return String.format("C-%d-%d", listaConsultorios.size() + 1, anioActual, numeroAleatorio);

    }

    public Consultorio obtenerConsultorioPorId(String idConsultorio){
        return listaConsultorios.stream().filter(c -> c.getId().equals(idConsultorio)).findFirst().orElse(null);
    }

    public void mostrarConsultorioPorID(String id) {
        for (Consultorio consultorio : this.listaConsultorios) {
            if (consultorio.getId().equals(id)) {
                System.out.println(consultorio.mostrarDatos());
                return;
            }
        }
        System.out.println("Consultorio no encontrado");
    }

   public String generarIConsulta(){
        int diaActual = LocalDate.now().getDayOfMonth();
        int numeroAleatorio = new Random().nextInt(100000-50)+50;
        return String.format("CO-%d-%d-%d", listaConsultorios.size() + 1, diaActual, numeroAleatorio);
   }

   public boolean validarFechaConsulta (LocalDateTime fechaDeseada){
        return this.validador.validarFechaCorrecta(fechaDeseada);
   }

   public void mostrarConsultas(){
        if(this.listaConsultas.isEmpty()){
            System.out.println("No se cuenta con consultas registradas");
            return;
        }
       System.out.println("----Lista Consultas----");
        for (Consulta consulta : this.listaConsultas) {
            System.out.println(consulta.mostrarInformacion());

        }
   }
//tarea 12
    public boolean existeNumeroTelefonoPaciente(String telefono) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getTelefono().equals(telefono)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeNumeroTelefonoMedico(String telefono) {
        for (Medico medico : listaMedicos) {
            if (medico.getTelefono().equals(telefono)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeRFCMedico(String rfc) {
        for (Medico medico : listaMedicos) {
            if (medico.getRfc().equals(rfc)) {
                return true;
            }
        }
        return false;
    }

    public boolean mostrarConsultasPaciente(String paciente) {
        for(Consulta consulta : this.listaConsultas){
            if(consulta.getPaciente().getNombre().equals(paciente)){
                System.out.println(consulta.mostrarInformacion());
                return true;
            }

        }
        return false;
    }

    public boolean mostrarConsultasMedico(String medico) {
        for(Consulta consulta : this.listaConsultas){
            if(consulta.getMedico().getNombre().equals(medico)){
                System.out.println(consulta.mostrarInformacion());
                return true;
            }

        }
        return false;

    }


    public boolean mostrarPacientesMedico(String medico) {
        for (Consulta consulta : this.listaConsultas) {
            if (consulta.getMedico().getNombre().equals(medico)) {
                System.out.println(consulta.getPaciente().mostrarDatos());
                return true;
            }

        }
        return false;
    }
}