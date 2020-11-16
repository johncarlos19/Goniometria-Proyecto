package logico.servicios;

public class FormularioCirugiaServicios {

    private static FormularioCirugiaServicios instancia;

    public static FormularioCirugiaServicios getInstance() {
        if (instancia == null)
            instancia = new FormularioCirugiaServicios();
        return instancia;
    }

}
