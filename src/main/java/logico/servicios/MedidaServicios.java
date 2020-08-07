package logico.servicios;

import logico.goniometriaClass.Goniometria;
import logico.goniometriaClass.Medida;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedidaServicios {
    private static MedidaServicios instancia;

    public static MedidaServicios getInstance( ) {
        if (instancia == null)
            instancia = new MedidaServicios();
        return instancia;
    }

    public List<Medida> listaEstudiantes() {
        List<Medida> lista = new ArrayList<>();
        /*Connection con = null; //objeto conexion.
        try {
            //
            String query = "select * from estudiante ";
            con = DataBaseServices.getInstancia().getConexion(); //referencia a la conexion.
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Medida est = new Medida();
                est.setMatricula(rs.getInt("matricula"));
                est.setNombre(rs.getString("nombre"));
                est.setApellido(rs.getString("apellido"));
                est.setCarrera(rs.getString("carrera"));
                est.setTelefono(rs.getString("telefono"));

                lista.add(est);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/

        return lista;
    }
    public Medida getMedida(String id_Medida, String lug) {
        Medida est = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...

            if(lug.equalsIgnoreCase("4dedos")){
                String query = "select t.ID_paciente, t.ID_medida, m.Fecha_realizacion, m.ID_especialista, a.ID_angulo, ac.ID_cuatrodedos, cu.id_dedos, r.ID_rom,\n" +
                        "       ac.Interfalangicas_distales , ac.Interfalangicas_proximales , ac.Metacarpofalangicas, cu.anular,\n" +
                        "        cu.indice, cu.mayor, cu.menique, r.Flexion, r.Extension, r.Abduccion, r.Aduccion\n" +
                        "\n" +
                        "from medidas m, angulos a, cuatrodedos cu, articulaciones_cuatrodedos ac, rom r, terapia t\n" +
                        "where m.ID_medida = a.ID_medida and a.ID_cuatrodedos = ac.ID_cuatrodedos and ac.ID_dedos = cu.id_dedos\n" +
                        "and cu.ID_rom = r.ID_rom and t.ID_medida = m.ID_medida and t.ID_medida = ?;";
                con = DataBaseServices.getInstancia().getConexion();
                //
                PreparedStatement prepareStatement = con.prepareStatement(query);
                //Antes de ejecutar seteo los parametros.
                prepareStatement.setString(1, id_Medida);
                //Ejecuto...
                ResultSet rs = prepareStatement.executeQuery();
                while(rs.next()){

                    est = new Medida();
                    est.setId_medida(rs.getString(2));
                    Date date = rs.getDate(3);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    String strDate = dateFormat.format(date);
                    est.setFecha_realizacion(strDate);
                    est.setId_especialista(rs.getString(4));
                    est.setId_angulo(rs.getString(5));
                    if(rs.getString(6) != null){
                        est.setId_articulaciones(rs.getString(6));
                        est.setId_cuatrodedos(rs.getString(7));
                        est.setId_rom(rs.getString(8));
                        if(rs.getInt(9) == 1){
                            est.setMedida(selectMedida(4));
                        }else if(rs.getInt(10) == 1){
                            est.setMedida(selectMedida(3));
                        }else if(rs.getInt(11) == 1){
                            est.setMedida(selectMedida(2));
                        }
                        if(rs.getInt(12) == 1){
                            est.setLugar(selectdedo(3));
                        }else if(rs.getInt(13) == 1){
                            est.setLugar(selectdedo(1));
                        }else if(rs.getInt(14) == 1){
                            est.setLugar(selectdedo(2));
                        }else if(rs.getInt(15) == 1){
                            est.setLugar(selectdedo(4));
                        }
                        if(rs.getDouble(16) != -1){

                            est.setRom(selectRom(1));
                            est.setRom_value(rs.getDouble(16));
                        }else if(rs.getDouble(17) != -1){
                            est.setRom(selectRom(2));
                            est.setRom_value(rs.getDouble(17));
                        }else if(rs.getDouble(18) != -1){
                            est.setRom(selectRom(3));
                            est.setRom_value(rs.getDouble(18));
                        }else if(rs.getDouble(19) != -1){
                            est.setRom(selectRom(4));
                            est.setRom_value(rs.getDouble(19));
                        }
                    }


                }
            }else if(lug.equalsIgnoreCase("pulgar")){
                String query = "select t.ID_paciente, t.ID_medida, m.Fecha_realizacion, m.ID_especialista, a.ID_angulo, ap.ID_pulgar, r.ID_rom,\n" +//7
                        "       ap.MetacarpofalangicasP, ap.Interfalangicas_proximalesP, ap.CarpometacarpianasP,\n" +
                        "       r.Flexion, r.Extension, r.Abduccion, r.Aduccion\n" +
                        "\n" +
                        "from medidas m, angulos a, articulaciones_pulgar ap, rom r, terapia t\n" +
                        "where m.ID_medida = a.ID_medida and a.ID_pulgar = ap.ID_pulgar\n" +
                        "and ap.ID_rom = r.ID_rom and t.ID_medida = m.ID_medida and t.ID_medida = ?;";
                con = DataBaseServices.getInstancia().getConexion();
                //
                PreparedStatement prepareStatement = con.prepareStatement(query);
                //Antes de ejecutar seteo los parametros.
                prepareStatement.setString(1, id_Medida);
                //Ejecuto...
                ResultSet rs = prepareStatement.executeQuery();
                while(rs.next()){

                    est = new Medida();
                    est.setId_medida(rs.getString(2));
                    Date date = rs.getDate(3);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    String strDate = dateFormat.format(date);
                    est.setFecha_realizacion(strDate);
                    est.setId_especialista(rs.getString(4));
                    est.setId_angulo(rs.getString(5));
                    if(rs.getString(6) != null){
                        est.setId_articulaciones(rs.getString(6));

                        est.setId_rom(rs.getString(7));
                        if(rs.getInt(8) == 1){
                            est.setMedida(selectMedida(2));
                        }else if(rs.getInt(9) == 1){
                            est.setMedida(selectMedida(3));
                        }else if(rs.getInt(10) == 1){
                            est.setMedida(selectMedida(1));
                        }
                        est.setLugar(selectdedo(5));

                        if(rs.getDouble(11) != -1){

                            est.setRom(selectRom(1));
                            est.setRom_value(rs.getDouble(11));
                        }else if(rs.getDouble(12) != -1){
                            est.setRom(selectRom(2));
                            est.setRom_value(rs.getDouble(12));
                        }else if(rs.getDouble(13) != -1){
                            est.setRom(selectRom(3));
                            est.setRom_value(rs.getDouble(13));
                        }else if(rs.getDouble(14) != -1){
                            est.setRom(selectRom(4));
                            est.setRom_value(rs.getDouble(14));
                        }
                    }


                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedidaServicios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MedidaServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return est;
    }
    private String selectRom(int index){
        switch (index){
            case 1:
                return "Flexion";
            case 2:
                return "Extension";
            case 3:
                return "abduccion";
            case 4:
                return "aduccion";
        }
        return null;
    }


    private String selectdedo(int index){
        switch (index){
            case 1:
                return "indice";
            case 2:
                return "medio";
            case 3:
                return "anular";
            case 4:
                return "meñique";
            case 5:
                return "pulgar";
        }
        return null;
    }

    private String selectMedida(int index){
        switch (index){
            case 1:
                return "carpometacarpiana";
            case 2:
                return "metocarpofalangicas";
            case 3:
                return "interfalangicas proximales";
            case 4:
                return "interfalangicas Distales";
        }
        return null;
    }





}
