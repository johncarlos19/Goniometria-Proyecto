package logico.servicios;

public class FormularioPruebasServicios {

    private static FormularioPruebasServicios instancia;

    public static FormularioPruebasServicios getInstance() {
        if (instancia == null)
            instancia = new FormularioPruebasServicios();
        return instancia;
    }
}
