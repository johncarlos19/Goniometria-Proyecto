package logico.goniometriaClass;

public class PreMedida {
    private String idAngulo;
    private String articulaciones;
    private String lugar;
    private String rom;


    public PreMedida(String idAngulo, String articulaciones, String lugar, String rom) {
        this.idAngulo = idAngulo;
        this.articulaciones = articulaciones;
        this.lugar = lugar;
        this.rom = rom;
    }

    public String getIdAngulo() {
        return idAngulo;
    }

    public void setIdAngulo(String idAngulo) {
        this.idAngulo = idAngulo;
    }

    public String getArticulaciones() {
        return articulaciones;
    }

    public void setArticulaciones(String articulaciones) {
        this.articulaciones = articulaciones;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }
}
