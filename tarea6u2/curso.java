import java.util.ArrayList;
import java.util.List;

class Curso {
    private String nombreCurso;
    private String codigoCurso;
    private String instructor;

    public Curso(String nombreCurso, String codigoCurso, String instructor) {
        this.nombreCurso = nombreCurso;
        this.codigoCurso = codigoCurso;
        this.instructor = instructor;
    }

    public void mostrarInfoCurso() {
        System.out.println("Nombre del curso: " + nombreCurso);
        System.out.println("Código del curso: " + codigoCurso);
        System.out.println("Instructor: " + instructor);
    }

    // Getters y setters (opcional)
    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
