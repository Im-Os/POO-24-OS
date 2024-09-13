import java.util.ArrayList;
import java.util.List;

class Estudiante {
    private String nombre;
    private String idEstudiante;
    private List<Curso> cursos;

    public Estudiante(String nombre, String idEstudiante) {
        this.nombre = nombre;
        this.idEstudiante = idEstudiante;
        this.cursos = new ArrayList<>();
    }

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }

    public void mostrarInformacion() {
        System.out.println("Nombre del estudiante: " + nombre);
        System.out.println("ID del estudiante: " + idEstudiante);
        System.out.println("Cursos inscritos:");
        for (Curso curso : cursos) {
            curso.mostrarInfoCurso();
            System.out.println();
        }
    }

    // Getters y setters (opcional)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}

