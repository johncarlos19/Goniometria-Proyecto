package logico.servicios;

public class PreDiagnosticoServicios {

    private static PreDiagnosticoServicios instancia;

    public static PreDiagnosticoServicios getInstance() {
        if (instancia == null)
            instancia = new PreDiagnosticoServicios();
        return instancia;
    }
}
