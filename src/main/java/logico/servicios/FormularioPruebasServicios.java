package logico.servicios;

import logico.goniometriaClass.FormularioCirugia;
import logico.goniometriaClass.FormularioPruebas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormularioPruebasServicios {

    private static FormularioPruebasServicios instancia;

    public static FormularioPruebasServicios getInstance() {
        if (instancia == null)
            instancia = new FormularioPruebasServicios();
        return instancia;
    }

    public FormularioPruebas getFormularioPruebas(String id){
        Connection con = null;
        FormularioPruebas formularioPruebas = new FormularioPruebas();
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "select idformulario_pruebas, tipo_prueba, fecha_prueba, hospital_prueba from goniometria.formulario_pruebas where idformulario_pruebas = ?;";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,id);



            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs  = prepareStatement.executeQuery();

            while(rs.next()){
                formularioPruebas.setIdformulario_pruebas(rs.getString(1));
                formularioPruebas.setTipo_prueba(rs.getString(2));
                formularioPruebas.setFecha_prueba(rs.getString(3));
                formularioPruebas.setHospital_prueba(rs.getString(4));

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
        return formularioPruebas;
    }

    public boolean addFormularioPruebas(FormularioPruebas formularioPruebas){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into formulario_pruebas(idformulario_pruebas, tipo_prueba, fecha_prueba, hospital_prueba) VALUES (?,?,?,?)";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,formularioPruebas.getIdformulario_pruebas());
            prepareStatement.setString(2,formularioPruebas.getTipo_prueba());
            prepareStatement.setString(3,formularioPruebas.getFecha_prueba());
            prepareStatement.setString(4,formularioPruebas.getHospital_prueba());



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
