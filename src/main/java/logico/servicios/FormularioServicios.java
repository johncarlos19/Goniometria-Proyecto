package logico.servicios;

public class FormularioServicios {

    private static FormularioServicios instancia;

    public static FormularioServicios getInstance() {
        if (instancia == null)
            instancia = new FormularioServicios();
        return instancia;
    }
}
