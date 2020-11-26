package logico.servicios;

import logico.goniometriaClass.FormularioPruebas;
import logico.goniometriaClass.Medicamentos;
import logico.goniometriaClass.PreDiagnostico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicamentosServicios {

    private static MedicamentosServicios instancia;

    public static MedicamentosServicios getInstance() {
        if (instancia == null)
            instancia = new MedicamentosServicios();
        return instancia;
    }

    public Medicamentos getMedicamentos(String id){
        Connection con = null;
        Medicamentos medicamentos = new Medicamentos();
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "select idmedicamentos, tratamiento, dosis, efectos, tiempo, revision_medico, medicamento_pre from goniometria.medicamentos where idmedicamentos = ?;";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,id);



            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs  = prepareStatement.executeQuery();

            while(rs.next()){
                medicamentos.setIdmedicamentos(rs.getString(1));
                medicamentos.setTratamiento(rs.getString(2));
                medicamentos.setDosis(rs.getString(3));
                medicamentos.setEfectos(rs.getString(4));
                medicamentos.setTiempo(rs.getString(5));
                medicamentos.setRevision_medico(rs.getString(6));
                medicamentos.setMedicamento_pre(rs.getString(7));

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
        return medicamentos;
    }

    public boolean addMedicamentos(Medicamentos medicamentos){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into medicamentos(idmedicamentos, tratamiento, dosis, efectos, tiempo, revision_medico, medicamento_pre) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,medicamentos.getIdmedicamentos());
            prepareStatement.setString(2,medicamentos.getTratamiento());
            prepareStatement.setString(3,medicamentos.getDosis());
            prepareStatement.setString(4,medicamentos.getEfectos());
            prepareStatement.setString(5,medicamentos.getTiempo());
            prepareStatement.setString(6,medicamentos.getRevision_medico());
            prepareStatement.setString(7,medicamentos.getMedicamento_pre());





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
