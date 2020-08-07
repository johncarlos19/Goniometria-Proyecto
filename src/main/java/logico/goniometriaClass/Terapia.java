package logico.goniometriaClass;

import java.util.ArrayList;

public class Terapia {
    private String idTerapia;
    private Medida medida;
    private String idEvolucion;
    private String idDiagnostico;
    private String idPaciente;

    public Terapia(String idTerapia, String idEvolucion, String idDiagnostico, String idPaciente, Medida medida) {
        this.idTerapia = idTerapia;
        this.medida = medida;
        this.idEvolucion = idEvolucion;
        this.idDiagnostico = idDiagnostico;
        this.idPaciente = idPaciente;
    }

    public String getIdTerapia() {
        return idTerapia;
    }

    public void setIdTerapia(String idTerapia) {
        this.idTerapia = idTerapia;
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    public String getIdEvolucion() {
        return idEvolucion;
    }

    public void setIdEvolucion(String idEvolucion) {
        this.idEvolucion = idEvolucion;
    }

    public String getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(String idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
}
