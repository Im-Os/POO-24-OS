public class Main {
    public static void main(String[] args) {
        // Crear cursos
        Curso curso1 = new Curso("Matemáticas", "MAT101", "Dr. Pérez");
        Curso curso2 = new Curso("Historia", "HIS202", "Prof. Gómez");
        Curso curso3 = new Curso("Biología", "BIO303", "Dr. Martínez");

        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante("Juan", "E001");
        Estudiante estudiante2 = new Estudiante("María", "E002");

        // Asignar cursos a los estudiantes
        estudiante1.agregarCurso(curso1);
        estudiante1.agregarCurso(curso2);

        estudiante2.agregarCurso(curso2);
        estudiante2.agregarCurso(curso3);

        // Mostrar información de los estudiantes
        estudiante1.mostrarInformacion();
        System.out.println("-------------------");
        estudiante2.mostrarInformacion();
    }
}