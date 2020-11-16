package logico.goniometriaClass;

public class Medicamentos {
    private String idmedicamentos;
    private String tratamiento;
    private String dosis;
    private String efectos;
    private String tiempo;
    private String revision_medico;
    private String medicamento_pre;

    public Medicamentos(String idmedicamentos, String tratamiento, String dosis, String efectos, String tiempo, String revision_medico, String medicamento_pre) {
        this.idmedicamentos = idmedicamentos;
        this.tratamiento = tratamiento;
        this.dosis = dosis;
        this.efectos = efectos;
        this.tiempo = tiempo;
        this.revision_medico = revision_medico;
        this.medicamento_pre = medicamento_pre;
    }

    public String getIdmedicamentos() {
        return idmedicamentos;
    }

    public void setIdmedicamentos(String idmedicamentos) {
        this.idmedicamentos = idmedicamentos;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getEfectos() {
        return efectos;
    }

    public void setEfectos(String efectos) {
        this.efectos = efectos;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getRevision_medico() {
        return revision_medico;
    }

    public void setRevision_medico(String revision_medico) {
        this.revision_medico = revision_medico;
    }

    public String getMedicamento_pre() {
        return medicamento_pre;
    }

    public void setMedicamento_pre(String medicamento_pre) {
        this.medicamento_pre = medicamento_pre;
    }
}
