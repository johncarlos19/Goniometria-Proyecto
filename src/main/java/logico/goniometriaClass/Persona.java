package logico.goniometriaClass;

public abstract class Persona {

    protected String cedula;
    protected String ID_Cuenta;
    protected String Nombre;
    protected String Apellido;
    protected String Sexo;
    protected String Fecha_nacimiento;
    protected String Telefono;
    protected String ID_Direccion;

    public Persona(String cedula, String ID_Cuenta, String nombre, String apellido, String sexo, String fecha_nacimiento, String telefono, String ID_Direccion) {
        this.cedula = cedula;
        this.ID_Cuenta = ID_Cuenta;
        Nombre = nombre;
        Apellido = apellido;
        Sexo = sexo;
        Fecha_nacimiento = fecha_nacimiento;
        Telefono = telefono;
        this.ID_Direccion = ID_Direccion;
    }

    public boolean isAvailable(){
        int cant = Goniometria.getInstance().cant("select count(*) from persona where "+"persona.Cedula = '"+cedula+"'");
        System.out.println(cant);
        if (cant == 0){
            return true;
        }
        return false;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getID_Cuenta() {
        return ID_Cuenta;
    }

    public void setID_Cuenta(String ID_Cuenta) {
        this.ID_Cuenta = ID_Cuenta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getFecha_nacimiento() {
        return Fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        Fecha_nacimiento = fecha_nacimiento;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getID_Direccion() {
        return ID_Direccion;
    }
    public abstract boolean register_user();

    public void setID_Direccion(String ID_Direccion) {
        this.ID_Direccion = ID_Direccion;
    }
}
