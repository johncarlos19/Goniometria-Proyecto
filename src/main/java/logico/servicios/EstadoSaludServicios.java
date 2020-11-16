package logico.servicios;

public class EstadoSaludServicios {

    private static EstadoSaludServicios instancia;

    public static EstadoSaludServicios getInstance() {
        if (instancia == null)
            instancia = new EstadoSaludServicios();
        return instancia;
    }
}
