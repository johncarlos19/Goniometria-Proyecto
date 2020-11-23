package logico.servicios;

import logico.goniometriaClass.FormularioMenor;
import logico.goniometriaClass.PreguntasGenerales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormularioMenorServicios {

    private static FormularioMenorServicios instancia;

    public static FormularioMenorServicios getInstance() {
        if (instancia == null)
            instancia = new FormularioMenorServicios();
        return instancia;
    }



    public boolean addFormularioMenor(FormularioMenor formularioMenor){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into formulario_menor(ID_fmenor, Nombre_padre, Apellido_padre, Relacion_paciente, FechaNac_padre, Seguro_padre, id_padre) VALUES (?,?,?,?,?,?,?);";


            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,formularioMenor.getID_fmenor());
            prepareStatement.setString(2,formularioMenor.getNombre_padre());
            prepareStatement.setString(3,formularioMenor.getApellido_padre());
            prepareStatement.setString(4,formularioMenor.getRelacion_paciente());
            prepareStatement.setString(5,formularioMenor.getFechaNac_padre());
            prepareStatement.setString(6,formularioMenor.getSeguro_padre());
            prepareStatement.setString(7,formularioMenor.getId_padre());
//            prepareStatement.setString(8,preguntasGenerales.getIdestado_salud());
//            prepareStatement.setString(9,preguntasGenerales.getIdpre_diagnostico());
//            prepareStatement.setString(10,preguntasGenerales.getPosicion());
//            prepareStatement.setString(11,preguntasGenerales.getActividades());
//            prepareStatement.setString(12,preguntasGenerales.getAccidentes());






            //Antes de ejecutar seteo los parametros.


            //
            int fila = prepareStatement.executeUpdate();
//            for (String aux: evolucion.getTerapiaIdList()
//            ) {
//                query = "update terapia set ID_evolucion = ? where idterapia = ?;";
//                prepareStatement = con.prepareStatement(query);
//                prepareStatement.setString(1,evolucion.getIdEvolucion());
//                prepareStatement.setString(2,aux);
//                fila = prepareStatement.executeUpdate();
//            }


        } catch (SQLException ex) {
            Logger.getLogger(PersonaServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }


}
