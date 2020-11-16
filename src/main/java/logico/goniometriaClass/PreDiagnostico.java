package logico.goniometriaClass;

public class PreDiagnostico {
    private String idpre_diagnostico;
    private String idformulario_cirugia;
    private String idformulario_pruebas;
    private String molestias_zona;
    private String tipo_lesion;
    private String valoracion_dolor;
    private String sintomas_asociados;
    private String fecha_diagnostico;
    private String hospital_pre;
    private String tipo_prueba;

    public PreDiagnostico(String idpre_diagnostico, String idformulario_cirugia, String idformulario_pruebas, String molestias_zona, String tipo_lesion, String valoracion_dolor, String sintomas_asociados, String fecha_diagnostico, String hospital_pre, String tipo_prueba) {
        this.idpre_diagnostico = idpre_diagnostico;
        this.idformulario_cirugia = idformulario_cirugia;
        this.idformulario_pruebas = idformulario_pruebas;
        this.molestias_zona = molestias_zona;
        this.tipo_lesion = tipo_lesion;
        this.valoracion_dolor = valoracion_dolor;
        this.sintomas_asociados = sintomas_asociados;
        this.fecha_diagnostico = fecha_diagnostico;
        this.hospital_pre = hospital_pre;
        this.tipo_prueba = tipo_prueba;
    }

    public String getIdpre_diagnostico() {
        return idpre_diagnostico;
    }

    public void setIdpre_diagnostico(String idpre_diagnostico) {
        this.idpre_diagnostico = idpre_diagnostico;
    }

    public String getIdformulario_cirugia() {
        return idformulario_cirugia;
    }

    public void setIdformulario_cirugia(String idformulario_cirugia) {
        this.idformulario_cirugia = idformulario_cirugia;
    }

    public String getIdformulario_pruebas() {
        return idformulario_pruebas;
    }

    public void setIdformulario_pruebas(String idformulario_pruebas) {
        this.idformulario_pruebas = idformulario_pruebas;
    }

    public String getMolestias_zona() {
        return molestias_zona;
    }

    public void setMolestias_zona(String molestias_zona) {
        this.molestias_zona = molestias_zona;
    }

    public String getTipo_lesion() {
        return tipo_lesion;
    }

    public void setTipo_lesion(String tipo_lesion) {
        this.tipo_lesion = tipo_lesion;
    }

    public String getValoracion_dolor() {
        return valoracion_dolor;
    }

    public void setValoracion_dolor(String valoracion_dolor) {
        this.valoracion_dolor = valoracion_dolor;
    }

    public String getSintomas_asociados() {
        return sintomas_asociados;
    }

    public void setSintomas_asociados(String sintomas_asociados) {
        this.sintomas_asociados = sintomas_asociados;
    }

    public String getFecha_diagnostico() {
        return fecha_diagnostico;
    }

    public void setFecha_diagnostico(String fecha_diagnostico) {
        this.fecha_diagnostico = fecha_diagnostico;
    }

    public String getHospital_pre() {
        return hospital_pre;
    }

    public void setHospital_pre(String hospital_pre) {
        this.hospital_pre = hospital_pre;
    }

    public String getTipo_prueba() {
        return tipo_prueba;
    }

    public void setTipo_prueba(String tipo_prueba) {
        this.tipo_prueba = tipo_prueba;
    }
}
