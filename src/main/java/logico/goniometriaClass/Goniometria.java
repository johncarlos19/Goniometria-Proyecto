package logico.goniometriaClass;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Goniometria {
    private static Goniometria goniometria;
    private static Connection con = null;
    private static Statement stmt = null;

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
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://"+IP_ADDRESS+":"+port+"/"+DataBase,user,password);
            stmt= null;
            stmt = con.createStatement();

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

    public boolean send_correo(String correo, String user){
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");



        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "bobpena14@gmail.com";
        String contrasena = "joanelvis809";
        String receptor = correo;
        String asunto = "Verification account";
                String mensaje= "Verifica cuenta en el siguiente enlace: http://138.255.251.202:7000/verification/"+user;

        MimeMessage mail = new MimeMessage(sesion);
        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            mail.setSubject(asunto);
            mail.setText(mensaje);

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
        try {
            int cant = 0;
            ResultSet rs= stmt.executeQuery("select count(*) from cuenta where cuenta.ID_cuenta = '"+ user +"' and cuenta.Password = '"+pass+"'");
            while(rs.next()){
                cant = rs.getInt(1);
            }
            if (cant == 1){
                return true;
            }else{
                return false;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return false;
    }
    public boolean verificacion_correo(String user, String pass){
        try {
            int cant = 0;
            ResultSet rs= stmt.executeQuery("select count(*) from cuenta where cuenta.ID_cuenta = '"+ user +"' and cuenta.Password = '"+pass+"' and cuenta.Validacion = 1");
            while(rs.next()){
                cant = rs.getInt(1);
            }
            if (cant == 1){
                return true;
            }else{
                return false;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return false;
    }

    public  void close_connectio() throws SQLException {
        if (con.isClosed()==false){
            con.close();
        }
    }
    public boolean Execute_insert(String query){
        try {

            int rd = stmt.executeUpdate(query);
            if (rd == 1){
                return true;
            }else{
                return false;
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    String hola = "Select count(cuenta.id='anna', cuenta.passowrd='pera' from cuenta)";
    public int cant(String query){
        int total = 0;
        try {

            ResultSet rs= stmt.executeQuery(query);
            while(rs.next()){
                total = rs.getInt(1);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return total;
    }

    public String return_ID_Generate(String query, String code, int longitud){
        StringBuilder value = new StringBuilder();
        int number = 0;
        int code_long = code.length();
        try {

            ResultSet rs= stmt.executeQuery(query);
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


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return value.toString();
    }
}
