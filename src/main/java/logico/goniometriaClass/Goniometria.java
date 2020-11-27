package logico.goniometriaClass;

import logico.servicios.DataBaseServices;
import logico.servicios.PacienteServicios;
import logico.servicios.PersonaServicios;
import org.jasypt.contrib.org.apache.commons.codec_1_3.Encoder;
import org.jasypt.util.text.AES256TextEncryptor;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Goniometria {
    private static Goniometria goniometria;
    private static Connection con = null;
    private static Statement stmt = null;
    private AES256TextEncryptor userEncryptor = new AES256TextEncryptor();
    private AES256TextEncryptor passwordEncryptor = new AES256TextEncryptor();


    public Goniometria() {
        this.con = null;

    }

    public static Goniometria getInstance( ) {
        if (goniometria == null)
            goniometria=new Goniometria();
        return goniometria;
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        Goniometria.con = con;
    }



    public void Start_Connection_DataBase(String IP_ADDRESS, String port, String DataBase, String user, String password){
        try{
            userEncryptor.setPassword("admin");
            passwordEncryptor.setPassword("admin");
            //Class.forName("com.mysql.jdbc.Driver");
            //con= DriverManager.getConnection("jdbc:mysql://"+IP_ADDRESS+":"+port+"/"+DataBase,user,password);
            //stmt= null;
           // stmt = con.createStatement();
            System.out.println("imprimi");


//here sonoo is database name, root is username and password

         }catch(Exception e){ System.out.println(e);}
    }
    public void Execute_query(String query){

    //"select 'Hello World!'"
        try {

            ResultSet rs= stmt.executeQuery(query);
            while(rs.next()){
               for (int i=1;i<=rs.getMetaData().getColumnCount();i++){
                    System.out.print(rs.getString(i)+" ");
                }
                System.out.print("\n");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isAvailable(String ID_cuenta){
        int cant = Goniometria.getInstance().cant("select count(*) from cuenta where "+"cuenta.ID_cuenta = '"+ID_cuenta+"'");
        System.out.println("cuenta "+cant);
        if (cant == 0){
            return true;
        }
        return false;
    }

    public boolean verificar_cuenta(String user){
        try {

            int rs= stmt.executeUpdate("UPDATE `goniometria`.`cuenta`\n" +
                    "SET `Validacion` = 1" +
                    " WHERE `ID_cuenta` = '"+user+"'");
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public AES256TextEncryptor getUserEncryptor() {
        return userEncryptor;
    }

    public AES256TextEncryptor getPasswordEncryptor() {
        return passwordEncryptor;
    }

    public void setPasswordEncryptor(AES256TextEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    public void setUserEncryptor(AES256TextEncryptor userEncryptor) {
        this.userEncryptor = userEncryptor;
    }




    public void correoException(Exception e){

    }

    public boolean send_correo_online(String correo, String mensaje, String asunto){
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");



        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "goniometria.project@gmail.com";
        String contrasena = "castillo30";
        String receptor = correo;




        MimeMessage mail = new MimeMessage(sesion);
        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            mail.setSubject(asunto);
            mail.setContent(mensaje,"text/html");

            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia,contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transportar.close();

            return true;

        } catch (AddressException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean send_correo(String correo, String user){
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");



        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "goniometria.project@gmail.com";
        String contrasena = "castillo30";
        String receptor = correo;
        String asunto = "Verification account";

        String mensaje= null;
        try {
            String enc = userEncryptor.encrypt(user);
            System.out.println("encriptart ante" + enc);
            mensaje = "Verifica cuenta en el siguiente enlace: <button>https://app1.goniometer-exoglove.me/verification/?username="+ URLEncoder.encode( enc, StandardCharsets.UTF_8.toString() )+"</button>";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        MimeMessage mail = new MimeMessage(sesion);
        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            mail.setSubject(asunto);
            mail.setContent(mensaje,"text/html");

            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia,contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transportar.close();

            return true;

        } catch (AddressException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean verificacion(String user, String pass){


        Connection con = null; //objeto conexion.
        try {
            //
            String query = "select count(*) from cuenta where cuenta.ID_cuenta = '"+ user +"' and cuenta.Password = '"+pass+"'";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            int cant = 0;
            while(rs.next()){
                cant = rs.getInt(1);
            }
            if (cant == 1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }





        return false;
    }
    public boolean verificacion_correo(String user, String pass){


        Connection con = null; //objeto conexion.
        try {
            //
            String query = "select count(*) from cuenta where cuenta.ID_cuenta = '"+ user +"' and cuenta.Password = '"+pass+"' and cuenta.Validacion = 1";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            int cant = 0;
            while(rs.next()){
                cant = rs.getInt(1);
            }
            if (cant == 1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        return false;
    }

    public  void close_connectio() throws SQLException {
        if (con.isClosed()==false){
            con.close();
        }
    }

    public String getTimestamp(){


        Connection con = null; //objeto conexion.
        String fecha = null;
        try {
            //
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement("select DATE_ADD(current_timestamp , INTERVAL -4 HOUR);");
            ResultSet rs = prepareStatement.executeQuery();

            while(rs.next()){
                Timestamp date = rs.getTimestamp(1);
                fecha = date.toString();
            }
            return fecha;
        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;


    }

    public boolean Execute_insert(String query){


        Connection con = null; //objeto conexion.
        try {
            //
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            int rd = prepareStatement.executeUpdate(query);
            if (rd == 1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;


    }


    String hola = "Select count(cuenta.id='anna', cuenta.passowrd='pera' from cuenta)";
    public int cant(String query){
        int total = 0;




        Connection con = null; //objeto conexion.
        try {
            //

            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();

            while(rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return total;
    }

    public String return_ID_Generate(String query, String code, int longitud){
        StringBuilder value = new StringBuilder();
        int number = 0;
        int code_long = code.length();




        Connection con = null; //objeto conexion.
        try {
            //

            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();

            while(rs.next()){
                number = rs.getInt(1);
            }
            String number_String = Integer.toString(number+1);

            int total_longitud_0 = longitud - code_long - number_String.length();
            for (int i = 0; i< code_long; i++){
                value.append(code.charAt(i));
            }
            for (int i = 0; i< total_longitud_0; i++){
                value.append('0');
            }
            for (int i = 0; i< number_String.length(); i++){
                value.append(number_String.charAt(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }




        return value.toString();
    }
    public boolean insertQuery(String query){
        Connection con = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
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
    public String return_ID_Generate_Max_Id(String query, String code, int longitud){
        StringBuilder value = new StringBuilder();
        long number = 0;
        long code_long = code.length();
        String receiver = "";
        StringBuilder receiverConst = new StringBuilder();
        boolean contin = false;




        Connection con = null; //objeto conexion.
        try {
            //

            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();

            while(rs.next()){
                receiver = rs.getString(1);
            }
            try {
                if (receiver!=null || receiver!=""){
                    for (int i =0; i <receiver.length(); i++){
                        if(contin==true){

                            receiverConst.append(receiver.charAt(i));
                        }else if (receiver.charAt(i)=='-'){
                            contin = true;
                        }
                    }
                    number = Long.parseLong(receiverConst.toString());
                }else{
                    number = 0;
                }
            }catch (NullPointerException E){
                number = 0;
            }







            String number_String = Long.toString(number+1);

            long total_longitud_0 = longitud - code_long - number_String.length();
            for (int i = 0; i< code_long; i++){
                value.append(code.charAt(i));
            }
            for (int i = 0; i< total_longitud_0; i++){
                value.append('0');
            }
            for (int i = 0; i< number_String.length(); i++){
                value.append(number_String.charAt(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }




        return value.toString();
    }
}
