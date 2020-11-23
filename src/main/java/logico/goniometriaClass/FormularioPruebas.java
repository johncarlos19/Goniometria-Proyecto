package logico.goniometriaClass;

public class FormularioPruebas {
    private String idformulario_pruebas = null;
    private String tipo_prueba = null;
    private String fecha_prueba = null;
    private String hospital_prueba = null;


    public FormularioPruebas() {
    }

    public FormularioPruebas(String idformulario_pruebas, String tipo_prueba, String fecha_prueba, String hospital_prueba) {
        this.idformulario_pruebas = idformulario_pruebas;
        this.tipo_prueba = tipo_prueba;
        this.fecha_prueba = fecha_prueba;
        this.hospital_prueba = hospital_prueba;
    }

    public String getIdformulario_pruebas() {
        return idformulario_pruebas;
    }

    public void setIdformulario_pruebas(String idformulario_pruebas) {
        this.idformulario_pruebas = idformulario_pruebas;
    }

    public String getTipo_prueba() {
        return tipo_prueba;
    }

    public void setTipo_prueba(String tipo_prueba) {
        this.tipo_prueba = tipo_prueba;
    }

    public String getFecha_prueba() {
        return fecha_prueba;
    }

    public void setFecha_prueba(String fecha_prueba) {
        this.fecha_prueba = fecha_prueba;
    }

    public String getHospital_prueba() {
        return hospital_prueba;
    }

    public void setHospital_prueba(String hospital_prueba) {
        this.hospital_prueba = hospital_prueba;
    }
}
