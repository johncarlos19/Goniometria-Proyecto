package logico.goniometriaClass;

public class Paciente extends Persona {
    private String idPaciente;


    public Paciente(String cedula, String ID_Cuenta, String nombre, String apellido, String sexo, String fecha_nacimiento, String telefono, String ID_Direccion) {
        super(cedula, ID_Cuenta, nombre, apellido, sexo, fecha_nacimiento, telefono, ID_Direccion);

    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public boolean register_user() {
        return false;
    }
}
