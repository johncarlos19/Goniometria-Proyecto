package logico.servicios;

import logico.goniometriaClass.FormularioPruebas;
import logico.goniometriaClass.PreDiagnostico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreDiagnosticoServicios {

    private static PreDiagnosticoServicios instancia;

    public static PreDiagnosticoServicios getInstance() {
        if (instancia == null)
            instancia = new PreDiagnosticoServicios();
        return instancia;
    }


    public boolean addPreDiagnostico(PreDiagnostico preDiagnostico){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into pre_diagnostico(idpre_diagnostico, molestias_zona, tipo_lesion, valoracion_dolor, sintomas_asociados, idformulario_cirugia, idformulario_pruebas, fecha_diagnostico, hospital_pre, tipo_prueba) VALUES (?,?,?,?,?,?,?,?,?,?);";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,preDiagnostico.getIdpre_diagnostico());
            prepareStatement.setString(2,preDiagnostico.getMolestias_zona());
            prepareStatement.setString(3,preDiagnostico.getTipo_lesion());
            prepareStatement.setString(4,preDiagnostico.getValoracion_dolor());
            prepareStatement.setString(5,preDiagnostico.getSintomas_asociados());
            prepareStatement.setString(6,preDiagnostico.getIdformulario_cirugia());
            prepareStatement.setString(7,preDiagnostico.getIdformulario_pruebas());
            prepareStatement.setString(8,preDiagnostico.getFecha_diagnostico());
            prepareStatement.setString(9,preDiagnostico.getHospital_pre());
            prepareStatement.setString(10,preDiagnostico.getTipo_prueba());




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
