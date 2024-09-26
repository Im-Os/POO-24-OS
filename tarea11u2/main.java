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

        while (opcion != 10) {
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

                    System.out.print("Ingresa la fecha de nacimiento del paciente: ");
                    String fechaNacimiento = scanner.nextLine();

                    System.out.print("Ingresa el tipo de sangre del paciente: ");
                    String tipoSangre = scanner.nextLine();

                    System.out.print("Ingresa el sexo del paciente: ");
                    String sexo = scanner.nextLine();

                    System.out.print("Ingresa el telefono del paciente: ");
                    String telefono = scanner.nextLine();

                    Paciente paciente = new Paciente(idPaciente, nombre, apellido, fechaNacimiento, tipoSangre, sexo);
                    hospital.registrarPaciente(paciente);
                    System.out.println("Paciente registrado con éxito.");
                    break;
                case 2:
                    System.out.print("Ingrese el apellido del médico: ");
                    String apellidoMedico = scanner.nextLine();
                    System.out.print("Ingrese el año de nacimiento del médico: ");
                    int anoNacimiento = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    String idMedico = hospital.generarIdMedico(apellidoMedico, anoNacimiento);
                    Medico medico = new Medico(idMedico, apellidoMedico, anoNacimiento);
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
                    // Aquí puedes agregar la lógica para registrar una consulta
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
                    // Aquí puedes agregar la lógica para mostrar consultas
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
