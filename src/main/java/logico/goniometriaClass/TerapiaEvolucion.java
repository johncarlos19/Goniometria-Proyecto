package logico.goniometriaClass;

public class TerapiaEvolucion {
    private String idEvolucion;
    private String id_documento;
    private String fecha;


    public TerapiaEvolucion(String idEvolucion, String id_documento, String fecha) {
        this.idEvolucion = idEvolucion;
        this.id_documento = id_documento;
        this.fecha = fecha;
    }
    public boolean isValidacion(){
        if (this.id_documento == null){
            return false;
        }else{
            return true;
        }
    }

    public String getIdEvolucion() {
        return idEvolucion;
    }

    public void setIdEvolucion(String idEvolucion) {
        this.idEvolucion = idEvolucion;
    }

    public String getId_documento() {
        return id_documento;
    }

    public void setId_documento(String id_documento) {
        this.id_documento = id_documento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
