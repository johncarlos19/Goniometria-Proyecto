package logico.servicios;

import logico.goniometriaClass.Direccion;
import logico.goniometriaClass.Paciente;
import logico.goniometriaClass.Persona;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PacienteServicios {
    private static PacienteServicios instancia;

    public static PacienteServicios getInstance( ) {
        if (instancia == null)
            instancia = new PacienteServicios();
        return instancia;
    }


public ArrayList<Paciente> listaPaciente(String idEspecialista){
        ArrayList<Paciente> list = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {
            //
            String query = "SELECT persona.Cedula, persona.ID_cuenta, persona.Nombre, persona.Apellido, persona.Sexo, persona.Fecha_nacimiento, persona.Telefono, persona.ID_direccion, paciente.ID_paciente, paciente.Cedula, paciente.comentario, direccion.ID_direccion, direccion.Municipio, direccion.Sector, direccion.Calle, direccion.N_residencia, paciente.email, paciente.comentario  FROM goniometria.persona inner join goniometria.paciente on persona.Cedula = paciente.Cedula inner join goniometria.direccion on persona.ID_direccion = direccion.ID_direccion WHERE persona.ID_cuenta = ?;";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1, idEspecialista);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Date date = rs.getDate(6);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd ");
                String strDate = dateFormat.format(date);
                Paciente pac = new Paciente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), strDate, rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(18));
                pac.setIdPaciente(rs.getString(9));
                pac.setCorreo(rs.getString(17));

                list.add(pac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public Paciente getPaciente(String idPaciente){
        Paciente aux = null;
        Connection con = null; //objeto conexion.
        try {
            //
            String query = "SELECT persona.Cedula, persona.ID_cuenta, persona.Nombre, persona.Apellido, persona.Sexo, persona.Fecha_nacimiento, persona.Telefono, persona.ID_direccion, paciente.ID_paciente, paciente.Cedula, paciente.comentario, direccion.ID_direccion, direccion.Municipio, direccion.Sector, direccion.Calle, direccion.N_residencia, paciente.email, paciente.comentario  FROM goniometria.persona inner join goniometria.paciente on persona.Cedula = paciente.Cedula inner join goniometria.direccion on persona.ID_direccion = direccion.ID_direccion WHERE paciente.ID_paciente = ?;";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1, idPaciente);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Date date = rs.getDate(6);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd ");
                String strDate = dateFormat.format(date);
                aux = new Paciente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), strDate, rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(18));
                aux.setIdPaciente(rs.getString(9));
                aux.setCorreo(rs.getString(17));

            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return aux;
    }

    public Direccion getDireccion(String idDireccion){
        Direccion aux = null;
        Connection con = null; //objeto conexion.
        try {
            //
            String query = "select ID_direccion, Municipio, Sector, Calle, N_residencia FROM goniometria.direccion WHERE ID_direccion = ?;";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1, idDireccion);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                aux = new Direccion("Republica Dominicana",rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                aux.setID_Direccion(rs.getString(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return aux;
    }

    public boolean crearPaciente(Paciente pac, Direccion dir ){
        boolean sub = false;

        String subio =pac.return_id_generate();
        Persona per = pac;
        String cedula = PersonaServicios.getInstance().crearPersona(per,dir);
        Connection con = null;
        try {

            String query = " INSERT INTO paciente(ID_paciente, Cedula, comentario, email) VALUES (?,?,?,?);";

            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, subio);
            prepareStatement.setString(2, pac.getCedula());
            prepareStatement.setString(3, pac.getComentario());
            prepareStatement.setString(4, pac.getCorreo());


            //
            int fila = prepareStatement.executeUpdate();
            sub = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return sub;
    }

}

