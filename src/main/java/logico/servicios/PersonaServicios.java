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

public class PersonaServicios {
    private static PersonaServicios instancia;

    public static PersonaServicios getInstance() {
        if (instancia == null)
            instancia = new PersonaServicios();
        return instancia;
    }

    public String crearPersona(Persona per, Direccion direccion){
        String subio = per.getCedula();

        Connection con = null;
        direccion.Send_Information();
        per.setID_Direccion(direccion.getID_Direccion());

        try {

            String query = " insert into persona(Cedula, ID_cuenta, Nombre, Apellido, Sexo, Fecha_nacimiento, Telefono, ID_direccion) VALUES (?,?,?,?,?,?,?,?);";

            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, per.getCedula());
            prepareStatement.setString(2, per.getID_Cuenta());
            prepareStatement.setString(3, per.getNombre());
            prepareStatement.setString(4, per.getApellido());
            prepareStatement.setString(5, per.getSexo());
            prepareStatement.setString(6, per.getFecha_nacimiento());
            prepareStatement.setString(7, per.getTelefono());

            prepareStatement.setString(8, per.getID_Direccion());


            //
            int fila = prepareStatement.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(PersonaServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return subio;
    }

}