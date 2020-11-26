package logico.servicios;

import logico.goniometriaClass.Documento;
import logico.goniometriaClass.Evolucion;
import logico.goniometriaClass.TerapiaEvolucion;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvolucionServicio {


    private static EvolucionServicio instancia;

    public static EvolucionServicio getInstance() {
        if (instancia == null)
            instancia = new EvolucionServicio();
        return instancia;
    }


    public ArrayList<TerapiaEvolucion> terapiaEvolucionArrayList(String idPaciente){
        ArrayList<TerapiaEvolucion> list = new ArrayList<TerapiaEvolucion>();
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "select t.ID_evolucion, ef.id_documento, ef.fecha from terapia t inner JOIN evolucion_fisioteraputica ef on ef.idEvolucion_fisioteraputica = t.ID_evolucion where t.ID_paciente = ? and t.ID_evolucion is not null group by t.ID_evolucion, ef.id_documento, ef.fecha;";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,idPaciente);

            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Timestamp date = rs.getTimestamp(3);
                TerapiaEvolucion
                aux = new TerapiaEvolucion(rs.getString(1),rs.getString(2),date.toString());
                list.add(aux);

            }


        } catch (SQLException ex) {
            Logger.getLogger(PersonaServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;


    }


    public Evolucion getEvolucion(String idEvolucion){
        Connection con = null;
        Evolucion aux = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "select ef.idEvolucion_fisioteraputica, ef.respuesta_tratamiento, ef.colaborador, ef.profesion, ef.actividad, ef.objetivo, ef.conclusion, ef.actividadEspecificar, ef.retraso, ef.pulso, ef.temperatura, ef.presion, ef.id_documento, ef.fecha from evolucion_fisioteraputica ef where ef.idEvolucion_fisioteraputica = ?;";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,idEvolucion);

            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Timestamp date = rs.getTimestamp(14);


                aux = new Evolucion(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
                        ,rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),null);
                aux.setFecha(date.toString());

            }


        } catch (SQLException ex) {
            Logger.getLogger(PersonaServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonaServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return aux;

    }


    public boolean updateEvolucion(Evolucion evolucion){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "update evolucion_fisioteraputica set respuesta_tratamiento = ?, colaborador = ?, profesion = ?, actividad = ?, objetivo = ?, conclusion = ?, actividadEspecificar = ?, retraso = ?, pulso = ?, temperatura = ?, presion = ?, id_documento = ? where idEvolucion_fisioteraputica = ? ;";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            prepareStatement.setString(1,evolucion.getRespuesta_tratamiento());
            prepareStatement.setString(2,evolucion.getColaborador());
            prepareStatement.setString(3,evolucion.getProfesion());
            prepareStatement.setString(4,evolucion.getActividad());
            prepareStatement.setString(5,evolucion.getObjetivo());
            prepareStatement.setString(6,evolucion.getConclusion());
            prepareStatement.setString(7,evolucion.getActividadEspecificar());
            prepareStatement.setString(8,evolucion.getRetraso());
            prepareStatement.setString(9,evolucion.getPulso());
            prepareStatement.setString(10,evolucion.getTemperatura());
            prepareStatement.setString(11,evolucion.getPresion());
            prepareStatement.setString(12,evolucion.getId_documento());
            prepareStatement.setString(13,evolucion.getIdEvolucion());


            //Antes de ejecutar seteo los parametros.


            //
            int fila = prepareStatement.executeUpdate();



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

    public boolean addEvolucion(Evolucion evolucion){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into evolucion_fisioteraputica(idEvolucion_fisioteraputica, respuesta_tratamiento, colaborador, profesion, actividad, objetivo, conclusion, actividadEspecificar, retraso, pulso, temperatura, presion, id_documento, fecha) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,null, DATE_ADD(current_timestamp , INTERVAL -4 HOUR));";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,evolucion.getIdEvolucion());
            prepareStatement.setString(2,evolucion.getRespuesta_tratamiento());
            prepareStatement.setString(3,evolucion.getColaborador());
            prepareStatement.setString(4,evolucion.getProfesion());
            prepareStatement.setString(5,evolucion.getActividad());
            prepareStatement.setString(6,evolucion.getObjetivo());
            prepareStatement.setString(7,evolucion.getConclusion());
            prepareStatement.setString(8,evolucion.getActividadEspecificar());
            prepareStatement.setString(9,evolucion.getRetraso());
            prepareStatement.setString(10,evolucion.getPulso());
            prepareStatement.setString(11,evolucion.getTemperatura());
            prepareStatement.setString(12,evolucion.getPresion());

            //Antes de ejecutar seteo los parametros.


            //
            int fila = prepareStatement.executeUpdate();
            for (String aux: evolucion.getTerapiaIdList()
                 ) {
                query = "update terapia set ID_evolucion = ? where idterapia = ?;";
                prepareStatement = con.prepareStatement(query);
                prepareStatement.setString(1,evolucion.getIdEvolucion());
                prepareStatement.setString(2,aux);
                fila = prepareStatement.executeUpdate();
            }


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
