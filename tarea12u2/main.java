import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import hospital.Hospital;
import paciente.Paciente;
import medicos.Medico;
import consultorios.Consultorio;
import consultas.Consulta;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();

        int opcion = 0;

        while (opcion != 12) {
            System.out.println("\n*** BIENVENIDO AL SISTEMA DEL HOSPITAL ***");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Registrar médico");
            System.out.println("3. Registrar consultorio");
            System.out.println("4. Registrar consulta");
            System.out.println("5. Mostrar pacientes");
            System.out.println("6. Mostrar médicos");
            System.out.println("7. Mostrar consultorios");
            System.out.println("8. Mostrar consultas");
            System.out.println("9. Mostrar paciente por ID");
            System.out.println("10. Mostrar médico por ID");
            System.out.println("11. Mostrar consultorio por ID");
            System.out.println("12. Salir");

            System.out.print("Ingresa una opción para continuar: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    String idPaciente = hospital.generarIdPaciente();
                    System.out.println("ID del paciente: " + idPaciente);

                    System.out.print("Ingresa el Nombre del paciente: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingresa el Apellido del paciente: ");
                    String apellido = scanner.nextLine();

                    System.out.print("Ingresa el año de nacimiento del paciente: ");
                    int anio = Integer.parseInt(scanner.nextLine());

                    System.out.print("Ingresa el mes de nacimiento del paciente: ");
                    int mes = Integer.parseInt(scanner.nextLine());

                    System.out.print("Ingresa el día de nacimiento del paciente: ");
                    int dia = Integer.parseInt(scanner.nextLine());

                    LocalDate fechaNacimientoPaciente = LocalDate.of(anio, mes, dia);

                    System.out.print("Ingresa el tipo de sangre del paciente: ");
                    String tipoSangre = scanner.nextLine();

                    System.out.print("Ingresa el sexo del paciente: ");
                    String sexo = scanner.nextLine();

                    boolean telefonoUnico = false;
                    String telefonoPaciente;
                    while (!telefonoUnico) {
                        System.out.print("Ingresa el teléfono del paciente: ");
                        telefonoPaciente = scanner.nextLine();
                        telefonoUnico = hospital.validarTelefonoPaciente(telefonoPaciente);
                        if (!telefonoUnico) {
                            System.out.println("Ya existe un paciente con este número de teléfono. Ingrese un teléfono diferente.");
                        }
                    }

                    Paciente paciente = new Paciente(idPaciente, nombre, apellido, fechaNacimientoPaciente, tipoSangre, sexo, telefonoPaciente);
                    hospital.registrarPaciente(paciente);
                    System.out.println("Paciente registrado con éxito.");
                    break;

                case 2:
                    System.out.print("Ingrese el apellido del médico: ");
                    String apellidoMedico = scanner.nextLine();
                    System.out.print("Ingresa el año de nacimiento del médico: ");
                    int anio2 = Integer.parseInt(scanner.nextLine());

                    System.out.print("Ingresa el mes de nacimiento del médico: ");
                    int mes2 = Integer.parseInt(scanner.nextLine());

                    System.out.print("Ingresa el día de nacimiento del médico: ");
                    int dia2 = Integer.parseInt(scanner.nextLine());

                    LocalDate fechaNacimientoMedico = LocalDate.of(anio2, mes2, dia2);
                    String idMedico = hospital.generarIdMedico(apellidoMedico, fechaNacimientoMedico);

                    boolean telefonoUnicoMedico = false;
                    String telefonoMedico;
                    while (!telefonoUnicoMedico) {
                        System.out.print("Ingresa el teléfono del médico: ");
                        telefonoMedico = scanner.nextLine();
                        telefonoUnicoMedico = hospital.validarTelefonoMedico(telefonoMedico);
                        if (!telefonoUnicoMedico) {
                            System.out.println("Ya existe un médico con este número de teléfono. Ingrese un teléfono diferente.");
                        }
                    }

                    boolean rfcUnico = false;
                    String rfcMedico;
                    while (!rfcUnico) {
                        System.out.print("Ingresa el RFC del médico: ");
                        rfcMedico = scanner.nextLine();
                        rfcUnico = hospital.validarRfcMedico(rfcMedico);
                        if (!rfcUnico) {
                            System.out.println("Ya existe un médico con este RFC. Ingrese un RFC diferente.");
                        }
                    }

                    Medico medico = new Medico(idMedico, apellidoMedico, fechaNacimientoMedico, telefonoMedico, rfcMedico);
                    hospital.registrarMedico(medico);
                    System.out.println("Médico registrado con ID: " + idMedico);
                    break;

                case 3:
                    String idConsultorio = hospital.generarIdConsultorio();
                    Consultorio consultorio = new Consultorio(idConsultorio);
                    hospital.registrarConsultorio(consultorio);
                    System.out.println("Consultorio registrado con ID: " + idConsultorio);
                    break;

                case 4:
                    // Lógica para registrar una consulta
                    boolean fechaCorrecta = false;
                    LocalDateTime fechaConsulta = null;
                    while (!fechaCorrecta) {
                        System.out.print("Ingrese la fecha y hora de la consulta (AAAA-MM-DDTHH:MM): ");
                        String fechaIngresada = scanner.nextLine();
                        fechaConsulta = LocalDateTime.parse(fechaIngresada);

                        if (fechaConsulta.isBefore(LocalDateTime.now())) {
                            System.out.println("La fecha ingresada está en el pasado. Ingrese una fecha válida.");
                        } else {
                            fechaCorrecta = true;
                        }
                    }

                    // Validar la existencia del paciente
                    boolean pacienteExiste = false;
                    String idPacienteConsulta;
                    while (!pacienteExiste) {
                        System.out.print("Ingrese el ID del paciente: ");
                        idPacienteConsulta = scanner.nextLine();
                        pacienteExiste = hospital.mostrarPacientePorId(idPacienteConsulta);
                        if (!pacienteExiste) {
                            System.out.println("El paciente no existe. Ingrese un ID válido.");
                        }
                    }

                    // Validar la existencia del médico
                    boolean medicoExiste = false;
                    String idMedicoConsulta;
                    while (!medicoExiste) {
                        System.out.print("Ingrese el ID del médico: ");
                        idMedicoConsulta = scanner.nextLine();
                        medicoExiste = hospital.mostrarMedicoPorId(idMedicoConsulta);
                        if (!medicoExiste) {
                            System.out.println("El médico no existe. Ingrese un ID válido.");
                        }
                    }

                    // Validar la existencia del consultorio
                    boolean consultorioExiste = false;
                    String idConsultorioConsulta;
                    while (!consultorioExiste) {
                        System.out.print("Ingrese el ID del consultorio: ");
                        idConsultorioConsulta = scanner.nextLine();
                        consultorioExiste = hospital.mostrarConsultorioPorId(idConsultorioConsulta);
                        if (!consultorioExiste) {
                            System.out.println("El consultorio no existe. Ingrese un ID válido.");
                        }
                    }

                    // Crear la consulta (asumiendo que hay un constructor adecuado en la clase Consulta)
                    Consulta consulta = new Consulta(0, fechaConsulta.toString(), hospital.getPacienteById(idPacienteConsulta),
                            hospital.getMedicoById(idMedicoConsulta), hospital.getConsultorioById(idConsultorioConsulta));
                    hospital.registrarConsulta(consulta);
                    System.out.println("Consulta registrada con éxito.");
                    break;

                case 5:
                    hospital.mostrarPacientes();
                    break;

                case 6:
                    hospital.mostrarMedicos();
                    break;

                case 7:
                    hospital.mostrarConsultorios();
                    break;

                case 8:
                    hospital.mostrarConsultas();
                    break;

                case 9:
                    System.out.print("Ingrese el ID del paciente: ");
                    String idPacienteBuscar = scanner.nextLine();
                    hospital.mostrarPacientePorId(idPacienteBuscar);
                    break;

                case 10:
                    System.out.print("Ingrese el ID del médico: ");
                    String idMedicoBuscar = scanner.nextLine();
                    hospital.mostrarMedicoPorId(idMedicoBuscar);
                    break;

                case 11:
                    System.out.print("Ingrese el ID del consultorio: ");
                    String idConsultorioBuscar = scanner.nextLine();
                    hospital.mostrarConsultorioPorId(idConsultorioBuscar);
                    break;

                case 12:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
