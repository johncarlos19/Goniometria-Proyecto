package logico.goniometriaClass;

public class FormularioMenor {
    private String ID_fmenor;
    private String Nombre_padre;
    private String Apellido_padre;
    private String Relacion_paciente;
    private String FechaNac_padre;
    private String Seguro_padre;
    private String id_padre;

    public FormularioMenor(String ID_fmenor, String nombre_padre, String apellido_padre, String relacion_paciente, String fechaNac_padre, String seguro_padre, String id_padre) {
        this.ID_fmenor = ID_fmenor;
        Nombre_padre = nombre_padre;
        Apellido_padre = apellido_padre;
        Relacion_paciente = relacion_paciente;
        FechaNac_padre = fechaNac_padre;
        Seguro_padre = seguro_padre;
        this.id_padre = id_padre;
    }

    public String getID_fmenor() {
        return ID_fmenor;
    }

    public void setID_fmenor(String ID_fmenor) {
        this.ID_fmenor = ID_fmenor;
    }

    public String getNombre_padre() {
        return Nombre_padre;
    }

    public void setNombre_padre(String nombre_padre) {
        Nombre_padre = nombre_padre;
    }

    public String getApellido_padre() {
        return Apellido_padre;
    }

    public void setApellido_padre(String apellido_padre) {
        Apellido_padre = apellido_padre;
    }

    public String getRelacion_paciente() {
        return Relacion_paciente;
    }

    public void setRelacion_paciente(String relacion_paciente) {
        Relacion_paciente = relacion_paciente;
    }

    public String getFechaNac_padre() {
        return FechaNac_padre;
    }

    public void setFechaNac_padre(String fechaNac_padre) {
        FechaNac_padre = fechaNac_padre;
    }

    public String getSeguro_padre() {
        return Seguro_padre;
    }

    public void setSeguro_padre(String seguro_padre) {
        Seguro_padre = seguro_padre;
    }

    public String getId_padre() {
        return id_padre;
    }

    public void setId_padre(String id_padre) {
        this.id_padre = id_padre;
    }
}
