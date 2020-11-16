package logico.goniometriaClass;

public class PreguntasGenerales {
    private String idpreguntas_generales;
    private String medicamento_prescrito;
    private String enfermedades_reciente;
    private String bulto_piel;
    private String habitos_toxicos;
    private String dieta_prescrita;
    private String posicion;
    private String actividades;
    private String accidentes;
    private String idmedicamentos;
    private String idestado_salud;
    private String idpre_diagnostico;

    public PreguntasGenerales(String idpreguntas_generales, String medicamento_prescrito, String enfermedades_reciente, String bulto_piel, String habitos_toxicos, String dieta_prescrita, String posicion, String actividades, String accidentes, String idmedicamentos, String idestado_salud, String idpre_diagnostico) {
        this.idpreguntas_generales = idpreguntas_generales;
        this.medicamento_prescrito = medicamento_prescrito;
        this.enfermedades_reciente = enfermedades_reciente;
        this.bulto_piel = bulto_piel;
        this.habitos_toxicos = habitos_toxicos;
        this.dieta_prescrita = dieta_prescrita;
        this.posicion = posicion;
        this.actividades = actividades;
        this.accidentes = accidentes;
        this.idmedicamentos = idmedicamentos;
        this.idestado_salud = idestado_salud;
        this.idpre_diagnostico = idpre_diagnostico;
    }

    public String getIdpreguntas_generales() {
        return idpreguntas_generales;
    }

    public void setIdpreguntas_generales(String idpreguntas_generales) {
        this.idpreguntas_generales = idpreguntas_generales;
    }

    public String getMedicamento_prescrito() {
        return medicamento_prescrito;
    }

    public void setMedicamento_prescrito(String medicamento_prescrito) {
        this.medicamento_prescrito = medicamento_prescrito;
    }

    public String getEnfermedades_reciente() {
        return enfermedades_reciente;
    }

    public void setEnfermedades_reciente(String enfermedades_reciente) {
        this.enfermedades_reciente = enfermedades_reciente;
    }

    public String getBulto_piel() {
        return bulto_piel;
    }

    public void setBulto_piel(String bulto_piel) {
        this.bulto_piel = bulto_piel;
    }

    public String getHabitos_toxicos() {
        return habitos_toxicos;
    }

    public void setHabitos_toxicos(String habitos_toxicos) {
        this.habitos_toxicos = habitos_toxicos;
    }

    public String getDieta_prescrita() {
        return dieta_prescrita;
    }

    public void setDieta_prescrita(String dieta_prescrita) {
        this.dieta_prescrita = dieta_prescrita;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(String accidentes) {
        this.accidentes = accidentes;
    }

    public String getIdmedicamentos() {
        return idmedicamentos;
    }

    public void setIdmedicamentos(String idmedicamentos) {
        this.idmedicamentos = idmedicamentos;
    }

    public String getIdestado_salud() {
        return idestado_salud;
    }

    public void setIdestado_salud(String idestado_salud) {
        this.idestado_salud = idestado_salud;
    }

    public String getIdpre_diagnostico() {
        return idpre_diagnostico;
    }

    public void setIdpre_diagnostico(String idpre_diagnostico) {
        this.idpre_diagnostico = idpre_diagnostico;
    }
}
