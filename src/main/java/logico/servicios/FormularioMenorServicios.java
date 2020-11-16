package logico.servicios;

public class FormularioMenorServicios {

    private static FormularioMenorServicios instancia;

    public static FormularioMenorServicios getInstance() {
        if (instancia == null)
            instancia = new FormularioMenorServicios();
        return instancia;
    }
}
