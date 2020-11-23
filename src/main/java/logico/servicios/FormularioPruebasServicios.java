package logico.servicios;

import logico.goniometriaClass.FormularioCirugia;
import logico.goniometriaClass.FormularioPruebas;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
