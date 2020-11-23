package logico.goniometriaClass;

import logico.servicios.PacienteServicios;
import logico.servicios.PersonaServicios;

public class Paciente extends Persona {
    private String idPaciente;
    private String correo;
    private String comentario;


    public Paciente(String cedula, String ID_Cuenta, String nombre, String apellido, String sexo, String fecha_nacimiento, String telefono, String ID_Direccion, String correo, String comentario) {
        super(cedula, ID_Cuenta, nombre, apellido, sexo, fecha_nacimiento, telefono, ID_Direccion);
        this.correo = correo;

    }

    public Direccion getDireccion() {
        return PacienteServicios.getInstance().getDireccion(ID_Direccion);
    }


    public String getIdPaciente() {
        return idPaciente;
    }
    public String return_id_generate (){
        return Goniometria.getInstance().return_ID_Generate("select count(*) from paciente","PAC-",20);
    }
    public String getCorreo() {
        return correo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public boolean register_user() {
        return false;
    }
}
