package logico.servicios;

import logico.goniometriaClass.EstadoSalud;
import logico.goniometriaClass.Medicamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
