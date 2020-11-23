package logico.goniometriaClass;

public class Formulario {
    private String ID_formulario = null;
    private String Seguro_social = null;
    private String Fecha_realizacion = null;
    private String Altura = null;
    private String Peso = null;
    private String Ocupacion = null;
    private String Precio_total = null;
    private String formulariocol = null;
    private String ID_paciente = null;
    private String ID_fmenor = null;
    private String idpreguntas_generales = null;

    public Formulario() {
    }

    public Formulario(String ID_formulario, String seguro_social, String fecha_realizacion, String altura, String peso, String ocupacion, String precio_total, String formulariocol, String ID_paciente, String ID_fmenor, String idpreguntas_generales) {
        this.ID_formulario = ID_formulario;
        Seguro_social = seguro_social;
        Fecha_realizacion = fecha_realizacion;
        Altura = altura;
        Peso = peso;
        Ocupacion = ocupacion;
        Precio_total = precio_total;
        this.formulariocol = formulariocol;
        this.ID_paciente = ID_paciente;
        this.ID_fmenor = ID_fmenor;
        this.idpreguntas_generales = idpreguntas_generales;
    }

    public String getID_formulario() {
        return ID_formulario;
    }

    public void setID_formulario(String ID_formulario) {
        this.ID_formulario = ID_formulario;
    }

    public String getSeguro_social() {
        return Seguro_social;
    }

    public void setSeguro_social(String seguro_social) {
        Seguro_social = seguro_social;
    }

    public String getFecha_realizacion() {
        return Fecha_realizacion;
    }

    public void setFecha_realizacion(String fecha_realizacion) {
        Fecha_realizacion = fecha_realizacion;
    }

    public String getAltura() {
        return Altura;
    }

    public void setAltura(String altura) {
        Altura = altura;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        Ocupacion = ocupacion;
    }

    public String getPrecio_total() {
        return Precio_total;
    }

    public void setPrecio_total(String precio_total) {
        Precio_total = precio_total;
    }

    public String getFormulariocol() {
        return formulariocol;
    }

    public void setFormulariocol(String formulariocol) {
        this.formulariocol = formulariocol;
    }

    public String getID_paciente() {
        return ID_paciente;
    }

    public void setID_paciente(String ID_paciente) {
        this.ID_paciente = ID_paciente;
    }

    public String getID_fmenor() {
        return ID_fmenor;
    }

    public void setID_fmenor(String ID_fmenor) {
        this.ID_fmenor = ID_fmenor;
    }

    public String getIdpreguntas_generales() {
        return idpreguntas_generales;
    }

    public void setIdpreguntas_generales(String idpreguntas_generales) {
        this.idpreguntas_generales = idpreguntas_generales;
    }
}
