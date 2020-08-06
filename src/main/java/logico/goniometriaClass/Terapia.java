package logico.goniometriaClass;

public class Terapia {
    private String idTerapia;
    private Medida medida;
    private String idEvolucion;
    private String idDiagnostico;
    private String idPaciente;

    public Terapia(String idTerapia, Medida medida, String idEvolucion, String idDiagnostico, String idPaciente) {
        this.idTerapia = idTerapia;
        this.medida = medida;
        this.idEvolucion = idEvolucion;
        this.idDiagnostico = idDiagnostico;
        this.idPaciente = idPaciente;
    }
}
