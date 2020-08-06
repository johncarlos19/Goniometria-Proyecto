package logico.goniometriaClass;

public class Direccion {
    private String pais;
    private String ID_Direccion;
    private String ciudad;
    private String calle;
    private String sector;
    private String N_residencia;

    public Direccion(String pais, String ciudad, String calle, String sector, String n_residencia) {
        this.pais = pais;
        this.ID_Direccion = return_ID_Direction();
        this.ciudad = ciudad;
        this.calle = calle;
        this.sector = sector;
        N_residencia = n_residencia;
    }

    private String return_ID_Direction(){
        String direction = Goniometria.getInstance().return_ID_Generate("select count(*) from direccion","DIR-",13);
        return direction;
    }
    public boolean Send_Information(){
        return Goniometria.getInstance().Execute_insert("insert into direccion (ID_direccion, Municipio, Sector, Calle, N_residencia) values(" +
                "'"+ID_Direccion+"','"+ciudad+"','"+sector+"','"+calle+"','"+N_residencia+"');");
    }
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getID_Direccion() {
        return ID_Direccion;
    }

    public void setID_Direccion(String ID_Direccion) {
        this.ID_Direccion = ID_Direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getN_residencia() {
        return N_residencia;
    }

    public void setN_residencia(String n_residencia) {
        N_residencia = n_residencia;
    }
}
