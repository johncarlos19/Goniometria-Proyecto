package logico.goniometriaClass;

public class EstadoSalud {
    private String idestado_salud = null;
    private String condicion = null;
    private String ejercicios = null;
    private String dificultad = null;
    private String molestia_dolor = null;
    private String enfermedades = null;
    private String dieta_pre = null;
    private String habitos_toxicos = null;
    private String estres_nivel = null;

    public EstadoSalud() {
    }

    public EstadoSalud(String idestado_salud, String condicion, String ejercicios, String dificultad, String molestia_dolor, String enfermedades, String dieta_pre, String habitos_toxicos, String estres_nivel) {
        this.idestado_salud = idestado_salud;
        this.condicion = condicion;
        this.ejercicios = ejercicios;
        this.dificultad = dificultad;
        this.molestia_dolor = molestia_dolor;
        this.enfermedades = enfermedades;
        this.dieta_pre = dieta_pre;
        this.habitos_toxicos = habitos_toxicos;
        this.estres_nivel = estres_nivel;
    }

    public String getIdestado_salud() {
        return idestado_salud;
    }

    public void setIdestado_salud(String idestado_salud) {
        this.idestado_salud = idestado_salud;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(String ejercicios) {
        this.ejercicios = ejercicios;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getMolestia_dolor() {
        return molestia_dolor;
    }

    public void setMolestia_dolor(String molestia_dolor) {
        this.molestia_dolor = molestia_dolor;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public String getDieta_pre() {
        return dieta_pre;
    }

    public void setDieta_pre(String dieta_pre) {
        this.dieta_pre = dieta_pre;
    }

    public String getHabitos_toxicos() {
        return habitos_toxicos;
    }

    public void setHabitos_toxicos(String habitos_toxicos) {
        this.habitos_toxicos = habitos_toxicos;
    }

    public String getEstres_nivel() {
        return estres_nivel;
    }

    public void setEstres_nivel(String estres_nivel) {
        this.estres_nivel = estres_nivel;
    }
}
