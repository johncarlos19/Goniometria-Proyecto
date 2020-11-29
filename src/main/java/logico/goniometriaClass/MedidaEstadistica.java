package logico.goniometriaClass;

import java.sql.Timestamp;

public class MedidaEstadistica {
    private String id_medida;
    private String id_especialista;
    private Timestamp fecha_realizacion;

    public MedidaEstadistica(String id_medida, String id_especialista, Timestamp fecha_realizacion) {
        this.id_medida = id_medida;
        this.id_especialista = id_especialista;
        this.fecha_realizacion = fecha_realizacion;
    }

    public String getId_medida() {
        return id_medida;
    }

    public void setId_medida(String id_medida) {
        this.id_medida = id_medida;
    }

    public String getId_especialista() {
        return id_especialista;
    }

    public void setId_especialista(String id_especialista) {
        this.id_especialista = id_especialista;
    }

    public Timestamp getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(Timestamp fecha_realizacion) {
        this.fecha_realizacion = fecha_realizacion;
    }
}
