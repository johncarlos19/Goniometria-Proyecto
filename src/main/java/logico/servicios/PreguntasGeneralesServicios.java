package logico.servicios;

public class PreguntasGeneralesServicios {

    private static PreguntasGeneralesServicios instancia;

    public static PreguntasGeneralesServicios getInstance() {
        if (instancia == null)
            instancia = new PreguntasGeneralesServicios();
        return instancia;
    }
}
