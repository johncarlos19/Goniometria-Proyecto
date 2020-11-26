package logico.servicios;

import logico.goniometriaClass.EstadoSalud;
import logico.goniometriaClass.FormularioPruebas;
import logico.goniometriaClass.PreguntasGenerales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreguntasGeneralesServicios {

    private static PreguntasGeneralesServicios instancia;

    public static PreguntasGeneralesServicios getInstance() {
        if (instancia == null)
            instancia = new PreguntasGeneralesServicios();
        return instancia;
    }

    public PreguntasGenerales getPreguntasGenerales(String id){
        Connection con = null;
        PreguntasGenerales preguntasGenerales = new PreguntasGenerales();
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "select idpreguntas_generales, medicamento_prescrito, enfermedades_reciente, bulto_piel, habitos_toxicos, dieta_prescrita, idmedicamentos, idestado_salud, idpre_diagnostico, posicion, actividades, accidentes from goniometria.preguntas_generales where idpreguntas_generales = ?;";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,id);



            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs  = prepareStatement.executeQuery();

            while(rs.next()){

                preguntasGenerales.setIdpreguntas_generales(rs.getString(1));
                preguntasGenerales.setMedicamento_prescrito(rs.getString(2));
                preguntasGenerales.setEnfermedades_reciente(rs.getString(3));
                preguntasGenerales.setBulto_piel(rs.getString(4));
                preguntasGenerales.setHabitos_toxicos(rs.getString(5));
                preguntasGenerales.setDieta_prescrita(rs.getString(6));
                preguntasGenerales.setIdmedicamentos(rs.getString(7));
                preguntasGenerales.setIdestado_salud(rs.getString(8));
                preguntasGenerales.setIdpre_diagnostico(rs.getString(9));
                preguntasGenerales.setPosicion(rs.getString(10));
                preguntasGenerales.setActividades(rs.getString(11));
                preguntasGenerales.setAccidentes(rs.getString(12));

            }
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
        return preguntasGenerales;
    }



    public boolean addPreguntasGenerales(PreguntasGenerales preguntasGenerales){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into preguntas_generales(idpreguntas_generales, medicamento_prescrito, enfermedades_reciente, bulto_piel, habitos_toxicos, dieta_prescrita, idmedicamentos, idestado_salud, idpre_diagnostico, posicion, actividades, accidentes) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,preguntasGenerales.getIdpreguntas_generales());
            prepareStatement.setString(2,preguntasGenerales.getMedicamento_prescrito());
            prepareStatement.setString(3,preguntasGenerales.getEnfermedades_reciente());
            prepareStatement.setString(4,preguntasGenerales.getBulto_piel());
            prepareStatement.setString(5,preguntasGenerales.getHabitos_toxicos());
            prepareStatement.setString(6,preguntasGenerales.getDieta_prescrita());
            prepareStatement.setString(7,preguntasGenerales.getIdmedicamentos());
            prepareStatement.setString(8,preguntasGenerales.getIdestado_salud());
            prepareStatement.setString(9,preguntasGenerales.getIdpre_diagnostico());
            prepareStatement.setString(10,preguntasGenerales.getPosicion());
            prepareStatement.setString(11,preguntasGenerales.getActividades());
            prepareStatement.setString(12,preguntasGenerales.getAccidentes());






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
