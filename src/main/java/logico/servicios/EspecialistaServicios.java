package logico.servicios;

import logico.goniometriaClass.Cuenta;
import logico.goniometriaClass.Direccion;
import logico.goniometriaClass.Especialista;
import logico.goniometriaClass.Paciente;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EspecialistaServicios {

    private static EspecialistaServicios instancia;

    public static EspecialistaServicios getInstance( ) {
        if (instancia == null)
            instancia = new EspecialistaServicios();
        return instancia;
    }

    public Especialista getEspecialista(String idEspecialista){
        Especialista aux = null;
        Connection con = null; //objeto conexion.
        try {
            //
            String query = "select persona.Cedula, persona.ID_cuenta, Nombre, Apellido, Sexo, Fecha_nacimiento, Telefono, ID_direccion, e.Tipo_especialista, c.Correo_electronico, c.Tipo, c.Password\n" +
                    "from persona inner JOIN especialista e on persona.Cedula = e.Cedula inner join cuenta c on persona.ID_cuenta = c.ID_cuenta where persona.ID_cuenta = ?;";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1, idEspecialista);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Date date = rs.getDate(6);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd ");
                String strDate = dateFormat.format(date);
                Cuenta cuenta = new Cuenta(rs.getString(2),rs.getString(10),rs.getString(12),rs.getString(11));
                aux = new Especialista(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), strDate, rs.getString(7), rs.getString(8), rs.getString(9), cuenta);
                aux.setDireccion(getDireccion(aux.getID_Direccion()));

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
}
