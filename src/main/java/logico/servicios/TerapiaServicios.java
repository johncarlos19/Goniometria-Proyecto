package logico.servicios;

import logico.goniometriaClass.Medida;
import logico.goniometriaClass.Terapia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TerapiaServicios {
    private static TerapiaServicios instancia;

    public static TerapiaServicios getInstance( ) {
        if (instancia == null)
            instancia = new TerapiaServicios();
        return instancia;
    }

    public ArrayList<Terapia> listaTerapia(String id_paciente){
        ArrayList<Terapia> list = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {
            //
            String query = "select idterapia, ID_medida, ID_evolucion, ID_diagnostico, ID_paciente, m.Fecha_realizacion,  CASE WHEN a.ID_pulgar is not null THEN 'pulgar' WHEN a.ID_cuatrodedos is not null THEN '4dedos' ELSE null END) AS dedo " +
                    "from terapia inner join medidas m on terapia.ID_medida = m.ID_medida " +
                    "inner join angulos a on m.ID_medida = a.ID_medida" +
                    " where ID_paciente = ? order by m.Fecha_realizacion desc ;  ";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,id_paciente);

            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Medida medid = new MedidaServicios().getInstance().getMedida(rs.getString(1),rs.getString(7));

                Terapia terapia = new Terapia(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5), medid);



                list.add(terapia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TerapiaServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TerapiaServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }





    public ArrayList<Medida> listaMedida(String id_paciente){
        ArrayList<Medida> list = new ArrayList<>();
        Connection con = null; //objeto conexion.
        try {
            //
            String query = "select t.ID_medida, t.idterapia, m.Fecha_realizacion, (\n" +
                    "    CASE\n" +
                    "        WHEN a.ID_pulgar is not null THEN 'pulgar'\n" +
                    "        WHEN a.ID_cuatrodedos is not null THEN '4dedos'\n" +
                    "        ELSE null\n" +
                    "    END) AS dedo from medidas m inner join terapia t on t.ID_medida = m.ID_medida inner join angulos a on m.ID_medida = a.ID_medida\n" +
                    "where t.ID_paciente = ? order by Fecha_realizacion desc ;";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,id_paciente);

            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){

                Medida medid = new MedidaServicios().getInstance().getMedida(rs.getString(1),rs.getString(4));

                list.add(medid);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TerapiaServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TerapiaServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }


}
