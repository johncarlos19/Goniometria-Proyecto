package logico.goniometriaClass;

public class Paciente extends Persona {
    private String idPaciente;
    private String correo;


    public Paciente(String cedula, String ID_Cuenta, String nombre, String apellido, String sexo, String fecha_nacimiento, String telefono, String ID_Direccion, String correo) {
        super(cedula, ID_Cuenta, nombre, apellido, sexo, fecha_nacimiento, telefono, ID_Direccion);
        this.correo = correo;

    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public boolean register_user() {
        return false;
    }
}
