package logico.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase con patrón Singleton
 * Created by vacax on 27/05/16.
 */
public class DataBaseServices {

    private static DataBaseServices instancia;
    private String URL = "jdbc:mysql://goniometriaproyect.cm31gjdb0ov8.us-east-1.rds.amazonaws.com:3306/goniometria?characterEncoding=latin1"; //Modo Server...

    /**
     *Implementando el patron Singleton
     */
    private DataBaseServices(){
        registrarDriver();
    }

    /**
     * Retornando la instancia.
     * @return
     */
    public static DataBaseServices getInstancia(){
        if(instancia==null){
             instancia = new DataBaseServices();
        }
        return instancia;
    }

    /**
     * Metodo para el registro de driver de conexión.
     */
    private void registrarDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MedidaServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, "johncarlos1943", "castillo30");
        } catch (SQLException ex) {
            Logger.getLogger(MedidaServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void testConexion() {
        try {
            getConexion().close();
            System.out.println("Conexión realizado con exito...");
        } catch (SQLException ex) {
            Logger.getLogger(MedidaServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
