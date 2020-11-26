package logico.servicios;

import logico.goniometriaClass.Documento;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentoServicios {

    private static DocumentoServicios instancia;

    public static DocumentoServicios getInstance() {
        if (instancia == null)
            instancia = new DocumentoServicios();
        return instancia;
    }

    public boolean addDocumento(Documento documento){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "insert into goniometria.Documentos(id_documento, Base64, Nombre, MIMETYPE, ID_paciente) VALUES (?,?,?,?,?)";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,documento.getIdDocumento());
            prepareStatement.setString(2,documento.getBase64());
            prepareStatement.setString(3,documento.getNombre());
            prepareStatement.setString(4,documento.getMimeType());
            prepareStatement.setString(5,documento.getIdPaciente());
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

    public Documento getDocumentoById(String idPaciente){
        Connection con = null;
        Documento aux = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "select id_documento, Base64, Nombre, MIMETYPE, ID_paciente from goniometria.Documentos where id_documento = ?";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,idPaciente);

            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                aux = new Documento(rs.getString(2),rs.getString(4),rs.getString(3),rs.getString(5));
                aux.setIdDocumento(rs.getString(1));
            }


        } catch (SQLException | UnsupportedEncodingException ex) {
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

    public Documento getDocumento(String idPaciente){
        Connection con = null;
        Documento aux = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            String query = "select id_documento, Base64, Nombre, MIMETYPE, ID_paciente from goniometria.Documentos where ID_paciente = ?";
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1,idPaciente);

            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                aux = new Documento(rs.getString(2),rs.getString(4),rs.getString(3),rs.getString(5));
                aux.setIdDocumento(rs.getString(1));
            }


        } catch (SQLException | UnsupportedEncodingException ex) {
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


}
