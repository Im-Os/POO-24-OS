package menu;

import consultas.Consulta;
import consultorios.Consultorio;
import hospital.Hospital;
import medicos.Medico;
import paciente.Paciente;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Menu {
   private Scanner sc=new Scanner(System.in);
   private Hospital hospital = new Hospital();

   private static final String USUARIO_PACIENTE = "Juan";
   private final String CONTRASENA_PACIENTE = "12345*";

   private final String USUARIO_MEDICO = "Juana";
   private final String CONTRASENA_MEDICO = "54321*";

    private static final String USUARIO_ADMIN = "admin";
    private final String CONTRASENA_ADMIN = "admin123";


   public void login(){
       int intentosMaximos = 5, intentosUsuario =0;

       while (intentosUsuario < intentosMaximos) {
           System.out.println("****Bienvenido****");
           System.out.println("Inicia sesión para continuar: ");
           System.out.println("Ingrese su usuario");
           String usuario = sc.nextLine();

           System.out.println("Ingresa tu contraseña");
           String contrasena = sc.nextLine();

           if (usuario.equals(this.USUARIO_PACIENTE)) {
               if (contrasena.equals(CONTRASENA_PACIENTE)) {
                   this.mostrarMenuPaciente();
                   intentosUsuario = 0;
               } else {
                   intentosUsuario = this.mostrarErrorInicioSesion(intentosUsuario);
               }
           } else if (usuario.equals(this.USUARIO_MEDICO)) {
               if (contrasena.equals(CONTRASENA_MEDICO)) {
                   this.mostrarMenuMedico();
                   intentosUsuario = 0;
               } else {
                   intentosUsuario = this.mostrarErrorInicioSesion(intentosUsuario);
               }
           } else if (usuario.equals(this.USUARIO_ADMIN)) {
               if (contrasena.equals(CONTRASENA_ADMIN)) {
                   this.mostrarMenuAdmin();
                   intentosUsuario = 0;
               } else {
                   intentosUsuario = this.mostrarErrorInicioSesion(intentosUsuario);
               }
           } else {
               System.out.println("Usuario incorrecto");
           }
       }

       System.out.println("Intentos máximos permitidos alcanzados.");
   }


   private int mostrarErrorInicioSesion(int intentosUsuario){
       System.out.println("\nUsuario o contraseña incorrectos, intenta de nuevo");
       return intentosUsuario +1 ;
   }


   private void mostrarMenuPaciente(){

       System.out.println("\n***SISTEMA HOSPITAL***");
       System.out.println("1. Ver mis consultas");
       System.out.println("2. Salir");

       System.out.println("Seleccione una opcion: ");
       int opcion = sc.nextInt();
       sc.nextLine();

       switch (opcion) {
           case 1:

             String nombrePaciente = USUARIO_PACIENTE;
             hospital.mostrarConsultasPaciente(nombrePaciente);


               break;
           case 2:
               System.out.println("Saliendo...");
               break;
           default:
               System.out.println("Opción no válida");
               break;
       }


   }

    private void mostrarMenuMedico(){
        System.out.println("\n***SISTEMA HOSPITAL***");
        System.out.println("1. Ver mis consultas");
        System.out.println("2. Ver mis pacientes");
        System.out.println("3. Consultar paciente");
        System.out.println("4. Salir");

        System.out.println("Seleccione una opcion: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:


                String nombreMedico = USUARIO_MEDICO;
                hospital.mostrarConsultasMedico(nombreMedico);

                break;
            case 2:

               String nMedico= USUARIO_MEDICO;
               hospital.mostrarConsultasMedico(nMedico);

                break;
            case 3:

                System.out.println("Consultando paciente...");
                break;
            case 4:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

    }




    private void mostrarMenuAdmin(){
        int opcion = 0;

        while(opcion != 9) {
            System.out.println("Bienvenido al IMSS ");
            System.out.println("Introduzca la opción que desee:  ");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Registrar medico");
            System.out.println("3. Registrar consultorio");
            System.out.println("4. Registrar consulta");
            System.out.println("5. Mostrar pacientes");
            System.out.println("6. Mostrar médicos");
            System.out.println("7. Mostrar consultorios");
            System.out.println("8. Mostrar consultas");
            System.out.println("9. Mostrar paciente por ID");
            System.out.println("10. Mostrar Medico por ID");
            System.out.println("11. Mostrar Consultorio por ID");
            System.out.println("12. Salir");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    //P--{año actual}---{mes actual}{Longuitud pacientes =1}{1,2000}
                    System.out.println("A elegido la opcion REGISTRAR PACIENTE");
                    String id = hospital.generarIDPaciente();

                    System.out.println("Ingrese el nombre del paciente: ");
                    String nombre = sc.nextLine();


                    System.out.println("Ingrese el apellidos del paciente: ");
                    String apellidos = sc.nextLine();


                    System.out.println("Ingresa año de nacimiento: ");
                    int anio = Integer.parseInt(sc.nextLine());
                    System.out.println("Ingrese el mes de nacimiento: ");
                    int mes = Integer.parseInt(sc.nextLine());
                    System.out.println("Ingresa sia de nacimiento:");
                    int dia = Integer.parseInt(sc.nextLine());

                    LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);

                    System.out.println("Ingrese el tipo de sangre del paciente: ");
                    String tipoSangre = sc.nextLine();


                    System.out.println("Ingrese el sexo del paciente: ");
                    char sexo = sc.nextLine().charAt(0);


                    //tarea 12
                    String telefono = null;
                    while (telefono == null) {
                        System.out.println("Ingrese el teléfono del paciente: ");
                        telefono = sc.nextLine();
                        if (hospital.existeNumeroTelefonoPaciente(telefono)) {
                            System.out.println("El numero de telefono ya esta registrado, intente de nuevo.");
                            telefono = null;
                        }
                    }
                    Paciente paciente = new Paciente(id, nombre, apellidos, fechaNacimiento, tipoSangre, sexo, telefono);
                    hospital.registrarPaciente(paciente);

                    System.out.println("Paciente registrado exitosamente");
                    break;

                case 2:
                    System.out.println("A elegido la opcion REGISTRAR MEDICO");

                    System.out.println("Ingrese el nombre del medico: ");
                    String nombreMedico = sc.nextLine();
                    System.out.println("Ingrese el apellido del medico: ");
                    String apellidoMedico = sc.nextLine();
                    System.out.println("Infresa año de nacimiento: ");
                    int anioMedico = Integer.parseInt(sc.nextLine());
                    System.out.println("Ingrese el mes de nacimiento: ");
                    int mesMedico = Integer.parseInt(sc.nextLine());
                    System.out.println("Ingresa dia de nacimiento:");
                    int diaMedico = Integer.parseInt(sc.nextLine());
                    LocalDate fechaNacimientoMedico = LocalDate.of(anioMedico, mesMedico, diaMedico);

                    //tarea 12
                    String telefonoMedico = null;

                    while (telefonoMedico == null) {
                        System.out.println("Ingrese el telefono del medico: ");
                        telefonoMedico = sc.nextLine();
                        if (hospital.existeNumeroTelefonoMedico(telefonoMedico)) {
                            System.out.println("El numero de telefono ya esta registrado, intente de nuevo.");
                            telefonoMedico = null;
                        }
                    }

                    String rfcMedico = null;
                    while (rfcMedico == null) {
                        System.out.println("Ingrese el RFC del medico: ");
                        rfcMedico = sc.nextLine();
                        if (hospital.existeRFCMedico(rfcMedico)) {
                            System.out.println("El RFC del medico ya esta registrado, intente de nuevo.");
                            rfcMedico = null;
                        }
                    }

                    String idMedico = hospital.generarIDMedico(fechaNacimientoMedico.getYear(), apellidoMedico);
                    Medico medico = new Medico(idMedico, nombreMedico, apellidoMedico, fechaNacimientoMedico, telefonoMedico, rfcMedico);

                    hospital.registrarMedico(medico);
                    break;

                case 3:
                    System.out.println("A elegido la opcion REGISTRAR CONSULTORIO");

                    String idConsultorio = hospital.generarIDConsultorio();

                    System.out.println("Ingrese el piso del consultorio: ");
                    int piso = Integer.parseInt(sc.nextLine());
                    sc.nextLine();

                    System.out.println("Ingrese el numero del consultorio: ");
                    int numero = Integer.parseInt(sc.nextLine());
                    sc.nextLine();

                    Consultorio consultorio = new Consultorio(idConsultorio, piso, numero);
                    hospital.registrarConsultorios(consultorio);
                    break;
                case 4:
                    System.out.println("\nSeleccionaste la opcion de para registrar consulta");
                    // int id = 1;

                    String idConsulta = hospital.generarIConsulta();

                    LocalDateTime fechaConsulta = null;

                    while (fechaConsulta == null) {

                        System.out.println("Ingresa el dia de la consulta deseada");
                        int diaConsulta = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Ingresa el mes de la consulta deseada");
                        int mesConsulta = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Ingresa el anio de la consulta deseada");
                        int anioConsulta = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Ingresa la hora de la consulta deseada");
                        int horaConsulta = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Ingresa los minutos de la consulta deseada");
                        int minutosConsulta = sc.nextInt();
                        sc.nextLine();

                        fechaConsulta = LocalDateTime.of(anioConsulta, mesConsulta, diaConsulta, horaConsulta, minutosConsulta);
                        //tarea12
                        if (!hospital.validarFechaConsulta(fechaConsulta)) {
                            System.out.println("La fecha no puede estar en el pasado");
                            fechaConsulta = null;
                        }
                    }


                    Paciente pacienteConsulta = null;
                    while (pacienteConsulta == null) {
                        System.out.println("Ingresa el id del paciente: ");
                        String pacienteId = sc.nextLine();
                        pacienteConsulta = hospital.obtenerPacientePorId(pacienteId);
                        if (pacienteConsulta == null) {
                            System.out.println("\nEl id del paciente no existe, intenta de nuevo\n");
                        }
                    }

                    Medico medicoConsulta = null;
                    while (medicoConsulta == null) {
                        System.out.println("Ingresa el id del medico:");
                        String medicoId = sc.nextLine();
                        medicoConsulta = hospital.obtenerMedicoPorId(medicoId);
                        if (medicoConsulta == null) {
                            System.out.println("\nEl id del medico no existe, intenta de nuevo\n");
                        }
                    }

                    Consultorio consultorioConsulta = null;
                    while (consultorioConsulta == null) {
                        System.out.println("Ingresa el id del consultorio: ");
                        String consultorioId = sc.nextLine();
                        consultorioConsulta = hospital.obtenerConsultorioPorId(consultorioId);
                        if (consultorioConsulta == null) {
                            System.out.println("\nEl id del consultorio no existe, intenta de nuevo\n");
                        }
                    }
                    Consulta consulta = new Consulta(idConsulta, fechaConsulta, pacienteConsulta, medicoConsulta, consultorioConsulta);
                    hospital.registrarConsulta(consulta);
                    System.out.println(hospital.listaConsultas.get(0));


                    break;


                case 5:
                    System.out.println("A elegido la opcion MOSTRAR PACIENTES");
                    System.out.println("Los pacientes son los siguientes: ");
                    hospital.mostrarPacientes();
                    break;
                case 6:
                    System.out.println("A elegido la opcion MOSTRAR MEDICOS");
                    System.out.println("Los medicos son los siguientes: ");
                    hospital.mostrarMedicos();
                    break;
                case 7:
                    System.out.println("A elegido la opcion MOSTRAR CONSULTORIOS");
                    System.out.println("Los consultorios son los siguientes: ");
                    hospital.mostrarConsultorios();
                    break;
                case 8:
                    System.out.println("A elegido la opcion MOSTRAR CONSULTAS");
                    System.out.println("Las consultas son lss siguientes: ");
                    hospital.mostrarConsultas();
                    break;
                case 9:
                    System.out.println("\nA elegido la opcion MOSTRAR PACIENTE POR ID");
                    sc.nextLine();
                    System.out.println("Ingresa el ID del paciente que deseas buscar");
                    String idPaciente = sc.nextLine();

                    hospital.mostrarPacientePorId(idPaciente);
                    break;

                case 10:
                    System.out.println("\nA elegido la opcion MOSTRAR MEDICO POR ID");
                    sc.nextLine();
                    System.out.println("Ingresa el ID del medico que deseas buscar");
                    idMedico = sc.nextLine();
                    hospital.mostrarMedicoPorID(idMedico);
                    break;

                case 11:
                    System.out.println("\nA elegido la opcion MOSTRAR CONSULTORIO POR ID");
                    sc.nextLine();
                    System.out.println("Ingrese el ID del consultorio que deseas buscar");
                    idConsultorio = sc.nextLine();
                    hospital.mostrarConsultorioPorID(idConsultorio);
                    break;

                case 12:
                    System.out.println("Hasta Luego");
                    return;
            }
        }
    }

}
