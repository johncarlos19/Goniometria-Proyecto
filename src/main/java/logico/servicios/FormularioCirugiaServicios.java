package logico.servicios;

import logico.goniometriaClass.Evolucion;
import logico.goniometriaClass.FormularioCirugia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormularioCirugiaServicios {

    private static FormularioCirugiaServicios instancia;

    public static FormularioCirugiaServicios getInstance() {
        if (instancia == null)
            instancia = new FormularioCirugiaServicios();
        return instancia;
    }


    public boolean getFormularioCirugia(FormularioCirugia formularioCirugia){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into formulario_cirugia(idformulario_cirugia, tipo_cirugia, fecha_cirugia, hospital_cirugia, formulario_cirugiacol) VALUES (?,?,?,?,?);";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,formularioCirugia.getIdformulario_cirugia());
            prepareStatement.setString(2,formularioCirugia.getTipo_cirugia());
            prepareStatement.setString(3,formularioCirugia.getFecha_cirugia());
            prepareStatement.setString(4,formularioCirugia.getHospital_cirugia());
            prepareStatement.setString(5,formularioCirugia.getFormulario_cirugiaco());


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

    public boolean addFormularioCirugia(FormularioCirugia formularioCirugia){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into formulario_cirugia(idformulario_cirugia, tipo_cirugia, fecha_cirugia, hospital_cirugia, formulario_cirugiacol) VALUES (?,?,?,?,?);";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,formularioCirugia.getIdformulario_cirugia());
            prepareStatement.setString(2,formularioCirugia.getTipo_cirugia());
            prepareStatement.setString(3,formularioCirugia.getFecha_cirugia());
            prepareStatement.setString(4,formularioCirugia.getHospital_cirugia());
            prepareStatement.setString(5,formularioCirugia.getFormulario_cirugiaco());


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
