package logico.goniometriaClass;

public class Especialista extends Persona {
    private String Tipo_especialista;
    private Cuenta cuenta;

    public Especialista(String cedula, String ID_Cuenta, String nombre, String apellido, String sexo, String fecha_nacimiento, String telefono, String ID_Direccion, String tipo_especialista, Cuenta cuenta) {
        super(cedula, ID_Cuenta, nombre, apellido, sexo, fecha_nacimiento, telefono, ID_Direccion);
        this.Tipo_especialista = tipo_especialista;
        this.cuenta = cuenta;
    }


    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getTipo_especialista() {
        return Tipo_especialista;
    }

    public void setTipo_especialista(String tipo_especialista) {
        Tipo_especialista = tipo_especialista;
    }

    @Override
    public boolean register_user() {
        if (cuenta.isAvailable()==true){
            cuenta.Send_Information();
            String query = "INSERT INTO `goniometria`.`persona`\n" +

                    "VALUES\n" +
                    "('"+cedula+"',\n" +
                    "'"+ID_Cuenta+"',\n" +
                    "'"+Nombre+"',\n" +
                    "'"+Apellido+"',\n" +
                    "'"+Sexo+"',\n" +
                    "'"+Fecha_nacimiento+"',\n" +
                    "'"+Telefono+"',\n" +
                    "'"+ID_Direccion+"');";
            Goniometria.getInstance().Execute_insert(query);
            String query_Especialista = "INSERT INTO `goniometria`.`especialista`\n" +
                    "(`ID_especialista`,\n" +
                    "`Cedula`,\n" +
                    "`Tipo_especialista`)\n" +
                    "VALUES\n" +
                    "('"+ID_Cuenta+"',\n" +
                    "'"+cedula+"',\n" +
                    "'"+Tipo_especialista+"');\n";
            Goniometria.getInstance().Execute_insert(query_Especialista);
            return true;

        }else{
            return false;
        }
    }
}
