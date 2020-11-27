package logico.servicios;

import logico.goniometriaClass.FormularioCirugia;
import logico.goniometriaClass.Permisos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PermisosServicios {
    private static PermisosServicios instancia;

    public static PermisosServicios getInstance( ) {
        if (instancia == null)
            instancia = new PermisosServicios();
        return instancia;
    }

    public int cantidadPermisos(String paciente, String especialista){
        int cant = 0;
        for (Permisos per: permisosList(especialista)
             ) {
            if (per.getId_permisos().equalsIgnoreCase(paciente)){
                cant+=1;
            }
        }
        return cant;
    }

    public List<Permisos> permisosList(String ID_cuenta){
        Connection con = null;
        List<Permisos> permisosListos = new ArrayList<Permisos>();
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "select ID_permiso, ID_especialista, ID_cuenta from goniometria.permisos where ID_cuenta = ?;";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1, ID_cuenta);



            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs  = prepareStatement.executeQuery();

            while(rs.next()){
                Permisos permisos = new Permisos();
                permisos.setId_permisos(rs.getString(1));
                permisos.setId_especialista(rs.getString(2));
                permisos.setId_cuenta(rs.getString(3));
                permisosListos.add(permisos);


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
        return permisosListos;
    }


    public Permisos getPermisosServicios(String ID_especialista, String ID_cuenta){
        Connection con = null;
        Permisos permisos = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "select ID_permiso, ID_especialista, ID_cuenta from goniometria.permisos where ID_especialista = ? and ID_cuenta = ?;";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,ID_especialista);
            prepareStatement.setString(2, ID_cuenta);



            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs  = prepareStatement.executeQuery();

            while(rs.next()){
                permisos.setId_permisos(rs.getString(1));
                permisos.setId_especialista(rs.getString(2));
                permisos.setId_cuenta(rs.getString(3));


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
        return permisos;
    }

    public boolean addPermisosServicios(Permisos permisos){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into permisos(ID_permiso, ID_especialista, ID_cuenta)  VALUES (?,?,?);";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,permisos.getId_permisos());
            prepareStatement.setString(2,permisos.getId_especialista());
            prepareStatement.setString(3,permisos.getId_cuenta());



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

    public boolean removePermisosServicios(Permisos permisos){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "delete from permisos where ID_permiso = ? and ID_especialista = ? and ID_cuenta  = ?;";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,permisos.getId_permisos());
            prepareStatement.setString(2,permisos.getId_especialista());
            prepareStatement.setString(3,permisos.getId_cuenta());



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
