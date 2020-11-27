package logico.goniometriaClass;

public class Permisos {
    private String id_permisos;
    private String id_cuenta;
    private String id_especialista;

    public Permisos(){

    }
    public Permisos(String id_permisos, String id_cuenta, String id_especialista) {
        this.id_permisos = id_permisos;
        this.id_cuenta = id_cuenta;
        this.id_especialista = id_especialista;
    }

    public String getId_permisos() {
        return id_permisos;
    }

    public void setId_permisos(String id_permisos) {
        this.id_permisos = id_permisos;
    }

    public String getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(String id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getId_especialista() {
        return id_especialista;
    }

    public void setId_especialista(String id_especialista) {
        this.id_especialista = id_especialista;
    }
}
