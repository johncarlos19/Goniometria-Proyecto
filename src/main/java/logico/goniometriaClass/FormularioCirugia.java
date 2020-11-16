package logico.goniometriaClass;

public class FormularioCirugia {
    private String idformulario_cirugia;
    private String tipo_cirugia;
    private String fecha_cirugia;
    private String hospital_cirugia;
    private String formulario_cirugiaco;

    public FormularioCirugia(String idformulario_cirugia, String tipo_cirugia, String fecha_cirugia, String hospital_cirugia, String formulario_cirugiaco) {
        this.idformulario_cirugia = idformulario_cirugia;
        this.tipo_cirugia = tipo_cirugia;
        this.fecha_cirugia = fecha_cirugia;
        this.hospital_cirugia = hospital_cirugia;
        this.formulario_cirugiaco = formulario_cirugiaco;
    }

    public String getIdformulario_cirugia() {
        return idformulario_cirugia;
    }

    public void setIdformulario_cirugia(String idformulario_cirugia) {
        this.idformulario_cirugia = idformulario_cirugia;
    }

    public String getTipo_cirugia() {
        return tipo_cirugia;
    }

    public void setTipo_cirugia(String tipo_cirugia) {
        this.tipo_cirugia = tipo_cirugia;
    }

    public String getFecha_cirugia() {
        return fecha_cirugia;
    }

    public void setFecha_cirugia(String fecha_cirugia) {
        this.fecha_cirugia = fecha_cirugia;
    }

    public String getHospital_cirugia() {
        return hospital_cirugia;
    }

    public void setHospital_cirugia(String hospital_cirugia) {
        this.hospital_cirugia = hospital_cirugia;
    }

    public String getFormulario_cirugiaco() {
        return formulario_cirugiaco;
    }

    public void setFormulario_cirugiaco(String formulario_cirugiaco) {
        this.formulario_cirugiaco = formulario_cirugiaco;
    }
}
