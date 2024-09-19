import java.util.Scanner;
import hospital.Hospital;
import paciente.Paciente;
import medicos.Medico;
import consultas.Consulta;
import consultorios.Consultorio;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();

        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n*** BIENVENIDO AL SISTEMA DEL HOSPITAL ***");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Registrar médico");
            System.out.println("3. Registrar consultorio");
            System.out.println("4. Registrar consulta");
            System.out.println("5. Mostrar pacientes");
            System.out.println("6. Mostrar médicos");
            System.out.println("7. Mostrar consultas");
            System.out.println("8. Salir");

            System.out.println("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nSeleccionaste la opción para registrar un paciente");

                    System.out.println("Ingresa el nombre del paciente");
                    String nombrePaciente = scanner.nextLine();

                    System.out.println("Ingresa la edad del paciente");
                    int edad = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingresa el género del paciente");
                    String genero = scanner.nextLine();

                    Paciente paciente = new Paciente(nombrePaciente, edad, genero);
                    hospital.registrarPaciente(paciente);

                    System.out.println("\nPaciente registrado correctamente");
                    break;
                case 2:
                    System.out.println("\nSeleccionaste la opción para registrar un médico");

                    System.out.println("Ingresa el nombre del médico");
                    String nombreMedico = scanner.nextLine();

                    System.out.println("Ingresa la especialidad del médico");
                    String especialidad = scanner.nextLine();

                    Medico medico = new Medico(nombreMedico, especialidad);
                    hospital.registrarMedico(medico);

                    System.out.println("\nMédico registrado correctamente");
                    break;
                case 3:
                    System.out.println("\nSeleccionaste la opción para registrar un consultorio");

                    System.out.println("Ingresa el ID del consultorio");
                    int idConsultorio = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingresa el piso del consultorio");
                    int piso = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingresa el número del consultorio");
                    int numeroConsultorio = scanner.nextInt();
                    scanner.nextLine();

                    Consultorio consultorio = new Consultorio(idConsultorio, piso, numeroConsultorio);
                    hospital.registrarConsultorio(consultorio);

                    System.out.println("\nConsultorio registrado correctamente");
                    break;
                case 4:
                    System.out.println("\nSeleccionaste la opción para registrar una consulta");

                    System.out.println("Ingresa el ID de la consulta");
                    int idConsulta = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingresa la fecha y hora de la consulta (dd/mm/yyyy hh:mm)");
                    String fechaHora = scanner.nextLine();

                    System.out.println("Ingresa el ID del paciente");
                    int idPaciente = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingresa el ID del médico");
                    int idMedico = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingresa el ID del consultorio");
                    int idConsultorioConsulta = scanner.nextInt();
                    scanner.nextLine();

                    Paciente pacienteConsulta = hospital.listaPacientes.stream()
                            .filter(p -> p.getId() == idPaciente)
                            .findFirst()
                            .orElse(null);

                    Medico medicoConsulta = hospital.listaMedicos.stream()
                            .filter(m -> m.getId() == idMedico)
                            .findFirst()
                            .orElse(null);

                    Consultorio consultorioConsulta = hospital.listaConsultorios.stream()
                            .filter(c -> c.getId() == idConsultorioConsulta)
                            .findFirst()
                            .orElse(null);

                    if (pacienteConsulta == null || medicoConsulta == null || consultorioConsulta == null) {
                        System.out.println("Paciente, médico o consultorio no encontrado.");
                        break;
                    }

                    Consulta consulta = new Consulta(idConsulta, fechaHora, pacienteConsulta, medicoConsulta, consultorioConsulta);
                    hospital.registrarConsulta(consulta);

                    System.out.println("\nConsulta registrada correctamente");
                    break;
                case 5:
                    hospital.mostrarPacientes();
                    break;
                case 6:
                    hospital.mostrarMedicos();
                    break;
                case 7:
                    hospital.mostrarConsultas();
                    break;
                case 8:
                    System.out.println("Hasta luego");
                    return;
            }
        }
    }
}
