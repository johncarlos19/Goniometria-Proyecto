package logico.servicios;

public class MedicamentosServicios {

    private static MedicamentosServicios instancia;

    public static MedicamentosServicios getInstance() {
        if (instancia == null)
            instancia = new MedicamentosServicios();
        return instancia;
    }
}
