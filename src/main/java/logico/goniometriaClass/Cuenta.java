package logico.goniometriaClass;

public class Cuenta {
    private String ID_cuenta;
    private String correo;
    private String contrasena;
    private String tipo;


    public Cuenta(String ID_cuenta, String correo, String contrasena, String tipo) {
        this.ID_cuenta = ID_cuenta;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public boolean Send_Information(){
        return Goniometria.getInstance().Execute_insert("INSERT INTO `goniometria`.`cuenta`\n" +

                " VALUES\n" +
                "('"+ID_cuenta+"',\n" +
                "'"+correo+"',\n" +
                "'"+contrasena+"',\n" +
                "'"+tipo+"', 0);");
    }
    public boolean isAvailable(){
        int cant = Goniometria.getInstance().cant("select count(*) from cuenta where "+"cuenta.ID_cuenta = '"+ID_cuenta+"'");
        System.out.println("cuenta "+cant);
        if (cant == 0){
            return true;
        }
        return false;
    }
    public String getID_cuenta() {
        return ID_cuenta;
    }

    public void setID_cuenta(String ID_cuenta) {
        this.ID_cuenta = ID_cuenta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
