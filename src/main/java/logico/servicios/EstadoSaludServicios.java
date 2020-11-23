package logico.servicios;

import logico.goniometriaClass.EstadoSalud;
import logico.goniometriaClass.FormularioPruebas;
import logico.goniometriaClass.Medicamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadoSaludServicios {

    private static EstadoSaludServicios instancia;

    public static EstadoSaludServicios getInstance() {
        if (instancia == null)
            instancia = new EstadoSaludServicios();
        return instancia;
    }

    public EstadoSalud getEstadoSalud(String id){
        Connection con = null;
        EstadoSalud estadoSalud = new EstadoSalud();
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "select idestado_salud, condicion, ejercicios, dificultad, molestia_dolor, enfermedades, dieta_pre, habitos_toxicos, estres_nivel from goniometria.estado_salud where idestado_salud = ?;";

            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,id);



            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs  = prepareStatement.executeQuery();

            while(rs.next()){

                estadoSalud.setIdestado_salud(rs.getString(1));
                estadoSalud.setCondicion(rs.getString(2));
                estadoSalud.setEjercicios(rs.getString(3));
                estadoSalud.setDificultad(rs.getString(4));
                estadoSalud.setMolestia_dolor(rs.getString(5));
                estadoSalud.setEnfermedades(rs.getString(6));
                estadoSalud.setDieta_pre(rs.getString(7));
                estadoSalud.setHabitos_toxicos(rs.getString(8));
                estadoSalud.setEstres_nivel(rs.getString(9));

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
        return estadoSalud;
    }

    public boolean addEstadoSalud(EstadoSalud estadoSalud){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into estado_salud(idestado_salud, condicion, ejercicios, dificultad, molestia_dolor, enfermedades, dieta_pre, habitos_toxicos, estres_nivel) values (?,?,?,?,?,?,?,?,?);";
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,estadoSalud.getIdestado_salud());
            prepareStatement.setString(2,estadoSalud.getCondicion());
            prepareStatement.setString(3,estadoSalud.getEjercicios());
            prepareStatement.setString(4,estadoSalud.getDificultad());
            prepareStatement.setString(5,estadoSalud.getMolestia_dolor());
            prepareStatement.setString(6,estadoSalud.getEnfermedades());
            prepareStatement.setString(7,estadoSalud.getDieta_pre());
            prepareStatement.setString(8,estadoSalud.getHabitos_toxicos());
            prepareStatement.setString(9,estadoSalud.getEstres_nivel());





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
