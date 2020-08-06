package logico.goniometriaClass;

public class Medida {
    private String id_medida;
    private String id_especialista;
    private String fecha_realizacion;
    private String medida;
    private String lugar;
    private String id_angulo;
    private String id_articulaciones;
    private String id_cuatrodedos;
    private String rom;
    private String id_rom;
    private double rom_value;

    public Medida() {
    }

    public Medida(String id_medida, String id_especialista, String fecha_realizacion, String medida, String lugar, String id_angulo, String id_articulaciones, String rom, String id_rom, double rom_value) {
        this.id_medida = id_medida;
        this.id_especialista = id_especialista;
        this.fecha_realizacion = fecha_realizacion;
        this.medida = medida;
        this.lugar = lugar;
        this.id_angulo = id_angulo;
        this.id_articulaciones = id_articulaciones;
        this.rom = rom;
        this.id_rom = id_rom;
        this.rom_value = rom_value;
    }

    public String getId_medida() {
        return id_medida;
    }

    public void setId_medida(String id_medida) {
        this.id_medida = id_medida;
    }

    public String getId_especialista() {
        return id_especialista;
    }

    public void setId_especialista(String id_especialista) {
        this.id_especialista = id_especialista;
    }

    public String getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(String fecha_realizacion) {
        this.fecha_realizacion = fecha_realizacion;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getId_angulo() {
        return id_angulo;
    }

    public void setId_angulo(String id_angulo) {
        this.id_angulo = id_angulo;
    }

    public String getId_articulaciones() {
        return id_articulaciones;
    }

    public void setId_articulaciones(String id_articulaciones) {
        this.id_articulaciones = id_articulaciones;
    }

    public String getId_cuatrodedos() {
        return id_cuatrodedos;
    }

    public void setId_cuatrodedos(String id_cuatrodedos) {
        this.id_cuatrodedos = id_cuatrodedos;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getId_rom() {
        return id_rom;
    }

    public void setId_rom(String id_rom) {
        this.id_rom = id_rom;
    }

    public double getRom_value() {
        return rom_value;
    }

    public void setRom_value(double rom_value) {
        this.rom_value = rom_value;
    }
}
