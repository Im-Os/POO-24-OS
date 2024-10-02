package hospital;

import consultas.Consulta;

import java.util.ArrayList;

public class ValidadorHospital {
    boolean validarDisponibilidadFechaConsulta(String fechaDeseada, int numeroConsultorio, ArrayList<Consulta> listaConsultas) {
        for (Consulta consulta : listaConsultas) {
            if (consulta.getFechaHora().equals(fechaDeseada) && numeroConsultorio == consulta.getConsultorio().getNumeroConsultorio()) {
                return false;
            }
        }
        return true;
    }

    boolean validarDisponibilidadMedico(String fechaDeseada, int idMedico, ArrayList<Consulta> listaConsultas) {
        for (Consulta consulta : listaConsultas) {
            if (consulta.getFechaHora().equals(fechaDeseada) && consulta.getMedico().getId() == idMedico) {
                return false;
            }
        }
        return true;
    }
}
