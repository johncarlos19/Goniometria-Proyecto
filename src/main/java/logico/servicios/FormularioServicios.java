package logico.servicios;

import logico.goniometriaClass.Formulario;
import logico.goniometriaClass.FormularioMenor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormularioServicios {

    private static FormularioServicios instancia;

    public static FormularioServicios getInstance() {
        if (instancia == null)
            instancia = new FormularioServicios();
        return instancia;
    }



    public boolean addFormulario(Formulario formulario){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into formulario(ID_formulario, Seguro_social, Fecha_realizacion, Altura, Peso, Ocupacion, Precio_total, ID_paciente, ID_fmenor, idpreguntas_generales, formulariocol) VALUES (?,?,DATE_ADD(current_timestamp , INTERVAL -4 HOUR),?,?,?,?,?,?,?,?);";

            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,formulario.getID_formulario());
            prepareStatement.setString(2,formulario.getSeguro_social());
            prepareStatement.setString(3,formulario.getAltura());
            prepareStatement.setString(4,formulario.getPeso());
            prepareStatement.setString(5,formulario.getOcupacion());
            prepareStatement.setString(6,formulario.getPrecio_total());
            prepareStatement.setString(7,formulario.getID_paciente());
            prepareStatement.setString(8,formulario.getID_fmenor());
            prepareStatement.setString(9,formulario.getIdpreguntas_generales());
            prepareStatement.setString(10,formulario.getFormulariocol());






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
