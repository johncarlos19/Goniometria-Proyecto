package logico.servicios;

import logico.goniometriaClass.Goniometria;
import logico.goniometriaClass.PreMedida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreMedidaServicios {
    private static PreMedidaServicios instancia;

    public static PreMedidaServicios getInstance() {
        if (instancia == null)
            instancia = new PreMedidaServicios();
        return instancia;
    }
    public List<PreMedida> listaPreMedida(String idPaciente){
        List<PreMedida> preMedidas = new ArrayList<PreMedida>();
        Connection con = null;
        String query = null;
        try {


            con = DataBaseServices.getInstancia().getConexion();
            //
            query = "select rm.ID_paciente ,a.ID_angulo, ac.ID_cuatrodedos, cu.id_dedos, r.ID_rom,\n" +
                    "ac.Interfalangicas_distales , ac.Interfalangicas_proximales , ac.Metacarpofalangicas, cu.anular,\n" +
                    "cu.indice, cu.mayor, cu.menique, r.Flexion, r.Extension, r.Abduccion, r.Aduccion from registro_medida_a_tomar rm, angulos a, cuatrodedos cu, articulaciones_cuatrodedos ac, rom r\n" +
                    "where rm.ID_angulo = a.ID_angulo and a.ID_cuatrodedos = ac.ID_cuatrodedos and ac.ID_dedos = cu.id_dedos and cu.ID_rom = r.ID_rom and rm.ID_paciente = ?;";
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1, idPaciente);

            //Antes de ejecutar seteo los parametros.


            //
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                PreMedida aux = new PreMedida(rs.getString(2),null,null,null);
                if (rs.getString(3)!=null){

                    if (rs.getInt(6)==1){
                        aux.setArticulaciones(selectMedida(4));
                    }else if (rs.getInt(7)==1){
                        aux.setArticulaciones(selectMedida(3));

                    }else if (rs.getInt(8)==1){
                        aux.setArticulaciones(selectMedida(2));

                    }

                    if (rs.getInt(9)==1){
                        aux.setLugar(selectdedo(3));
                    }else if (rs.getInt(10)==1){
                        aux.setLugar(selectdedo(1));

                    }else if (rs.getInt(11)==1){
                        aux.setLugar(selectdedo(2));

                    }else if (rs.getInt(12)==1){
                        aux.setLugar(selectdedo(4));

                    }



                    if (rs.getInt(13)==1){
                        aux.setRom(selectRom(1));
                    }else if (rs.getInt(14)==1){
                        aux.setRom(selectRom(2));

                    }else if (rs.getInt(15)==1){
                        aux.setRom(selectRom(4));

                    }else if (rs.getInt(16)==1){
                        aux.setRom(selectRom(3));

                    }

                    preMedidas.add(aux);
                }
            }
            query = "select   rm.ID_paciente ,a.ID_angulo, ap.ID_pulgar, r.ID_rom,\n" +
                    "ap.MetacarpofalangicasP, ap.Interfalangicas_proximalesP, ap.CarpometacarpianasP,\n" +
                    "                               r.Flexion, r.Extension, r.Abduccion, r.Aduccion\n" +
                    "\n" +
                    "                        from registro_medida_a_tomar rm, angulos a, articulaciones_pulgar ap, rom r\n" +
                    "                        where rm.ID_angulo = a.ID_angulo and a.ID_pulgar = ap.ID_pulgar\n" +
                    "                        and ap.ID_rom = r.ID_rom and rm.ID_paciente = ?;";
            prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1, idPaciente);

            rs = prepareStatement.executeQuery();
            while(rs.next()){
                PreMedida aux = new PreMedida(rs.getString(2),null,null,null);
                if (rs.getString(3)!=null){

                    aux.setLugar(selectdedo(5));


                    if (rs.getInt(5)==1){
                        aux.setArticulaciones(selectMedida(2));
                    }else if (rs.getInt(6)==1){
                        aux.setArticulaciones(selectMedida(3));

                    }else if (rs.getInt(7)==1){
                        aux.setArticulaciones(selectMedida(1));

                    }

                    if (rs.getInt(8)==1){
                        aux.setRom(selectRom(1));
                    }else if (rs.getInt(9)==1){
                        aux.setRom(selectRom(2));

                    }else if (rs.getInt(10)==1){
                        aux.setRom(selectRom(4));

                    }else if (rs.getInt(11)==1){
                        aux.setRom(selectRom(3));

                    }

                    preMedidas.add(aux);
                }
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

        return preMedidas;
    }
    public boolean addPreMedida(PreMedida preMedida, String id_paciente){
        String id_rom;
        id_rom = addROM(preMedida);
        String id_angulo = addLugarArticulacion(preMedida,id_rom);

        insertQuery("insert into registro_medida_a_tomar(ID_paciente, ID_angulo) values ('" + id_paciente + "','" + id_angulo + "');");






        return true;
    }
    private String addROM(PreMedida preMedida){
        String id_rom = null;
        Connection con = null;
        String query = null;



        if (preMedida.getRom().equalsIgnoreCase("Flexion"))
        {
            id_rom = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(rom.ID_rom) from goniometria.rom;", "ROM-", 20);
            query = "insert into rom(ID_rom, Flexion, Extension, Aduccion, Abduccion) VALUE ('" + id_rom + "'," + "1" + ", -1,-1,-1);";
        }
        else if(preMedida.getRom().equalsIgnoreCase("Extension")) {
            id_rom = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(rom.ID_rom) from goniometria.rom;", "ROM-", 20);
            query = "insert into rom(ID_rom, Flexion, Extension, Aduccion, Abduccion) VALUE ('" + id_rom + "',-1, " + "1" + ",-1,-1);";
        }else if( preMedida.getRom().equalsIgnoreCase( "abduccion")) {
            id_rom = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(rom.ID_rom) from goniometria.rom;", "ROM-", 20);
            query = "insert into rom(ID_rom, Flexion, Extension, Aduccion, Abduccion) VALUE ('" + id_rom + "',-1, -1,-1," + "1" + ");";
        }else if( preMedida.getRom().equalsIgnoreCase( "aduccion")) {
            id_rom = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(rom.ID_rom) from goniometria.rom;", "ROM-", 20);
            query = "insert into rom(ID_rom, Flexion, Extension, Aduccion, Abduccion) VALUE ('" + id_rom + "', -1, -1," + "1" + ",-1);";
        }
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
        return id_rom;
    }private String addLugarArticulacion(PreMedida preMedida, String id_rom){
        String id_ded = null;
        String id_4ded = null;
        String id_pulgar = null;
        String id_angulo = null;

        if (preMedida.getLugar().equalsIgnoreCase("menique")) {
            id_ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(cuatrodedos.id_dedos) from goniometria.cuatrodedos;", "DED-", 20);
            insertQuery("insert into cuatrodedos(id_dedos, menique, anular, mayor, indice, ID_rom) values ('" + id_ded + "', 1 , 0 , 0 , 0 , '" + id_rom + "');");



            if (preMedida.getArticulaciones().equalsIgnoreCase("metocarpofalangicas")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery(
                        "insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 1 , 0 , 0  , '" + id_ded + "');");
            }else if (preMedida.getArticulaciones().equalsIgnoreCase("interfalangicas proximales")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery(
                        "insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 0 , 1 , 0  , '" + id_ded + "');");
            }else if (preMedida.getArticulaciones().equalsIgnoreCase("interfalangicas Distales")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery("insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 0 , 0 , 1  , '" + id_ded + "');");
            }
        } else if (preMedida.getLugar().equalsIgnoreCase("anular")) {
            id_ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(cuatrodedos.id_dedos) from goniometria.cuatrodedos;", "DED-", 20);
            insertQuery("insert into cuatrodedos(id_dedos, menique, anular, mayor, indice, ID_rom) values ('" + id_ded + "', 0 , 1 , 0 , 0 , '" + id_rom + "');");
            if (preMedida.getArticulaciones().equalsIgnoreCase("metocarpofalangicas")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery(
                        "insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 1 , 0 , 0  , '" + id_ded + "');");
            }else if (preMedida.getArticulaciones().equalsIgnoreCase("interfalangicas proximales")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery(
                        "insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 0 , 1 , 0  , '" + id_ded + "');");
            }else if (preMedida.getArticulaciones().equalsIgnoreCase("interfalangicas Distales")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery("insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 0 , 0 , 1  , '" + id_ded + "');");
            }
        }else if (preMedida.getLugar().equalsIgnoreCase("indice")) {
            id_ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(cuatrodedos.id_dedos) from goniometria.cuatrodedos;", "DED-", 20);
            insertQuery("insert into cuatrodedos(id_dedos, menique, anular, mayor, indice, ID_rom) values ('" + id_ded + "', 0 , 0 , 0 , 1 , '" + id_rom + "');");
            if (preMedida.getArticulaciones().equalsIgnoreCase("metocarpofalangicas")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery(
                        "insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 1 , 0 , 0  , '" + id_ded + "');");
            }else if (preMedida.getArticulaciones().equalsIgnoreCase("interfalangicas proximales")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery(
                        "insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 0 , 1 , 0  , '" + id_ded + "');");
            }else if (preMedida.getArticulaciones().equalsIgnoreCase("interfalangicas Distales")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery("insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 0 , 0 , 1  , '" + id_ded + "');");
            }
        }else if (preMedida.getLugar().equalsIgnoreCase("medio")) {
            id_ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(cuatrodedos.id_dedos) from goniometria.cuatrodedos;", "DED-", 20);
            insertQuery("insert into cuatrodedos(id_dedos, menique, anular, mayor, indice, ID_rom) values ('" + id_ded + "', 0 , 0 , 1 , 0 , '" + id_rom + "');");
            if (preMedida.getArticulaciones().equalsIgnoreCase("metocarpofalangicas")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery(
                        "insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 1 , 0 , 0  , '" + id_ded + "');");
            }else if (preMedida.getArticulaciones().equalsIgnoreCase("interfalangicas proximales")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery(
                        "insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 0 , 1 , 0  , '" + id_ded + "');");
            }else if (preMedida.getArticulaciones().equalsIgnoreCase("interfalangicas Distales")) {
                id_4ded = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_cuatrodedos.ID_cuatrodedos) from goniometria.articulaciones_cuatrodedos;", "4DE-",
                        20);
                insertQuery("insert into articulaciones_cuatrodedos(ID_cuatrodedos, Metacarpofalangicas, Interfalangicas_proximales, Interfalangicas_distales, ID_dedos) values ('" + id_4ded + "', 0 , 0 , 1  , '" + id_ded + "');");
            }
        }else if (preMedida.getLugar().equalsIgnoreCase("pulgar")) {
                            id_pulgar = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(articulaciones_pulgar.ID_pulgar) from goniometria.articulaciones_pulgar;", "PUL-", 20);
                            if (preMedida.getArticulaciones().equalsIgnoreCase("metocarpofalangicas")) {
                                insertQuery("insert into articulaciones_pulgar(ID_pulgar, CarpometacarpianasP, MetacarpofalangicasP, Interfalangicas_proximalesP, ID_rom) VALUES('" + id_pulgar + "',0,1,0,'" + id_rom + "');");
                            }else if (preMedida.getArticulaciones().equalsIgnoreCase("interfalangicas proximales")) {
                                insertQuery("insert into articulaciones_pulgar(ID_pulgar, CarpometacarpianasP, MetacarpofalangicasP, Interfalangicas_proximalesP, ID_rom) VALUES('" + id_pulgar + "',0,0,1,'" + id_rom + "');");
                            }else if (preMedida.getArticulaciones().equalsIgnoreCase("carpometacarpiana")) {
                                insertQuery(
                                        "insert into articulaciones_pulgar(ID_pulgar, CarpometacarpianasP, MetacarpofalangicasP, Interfalangicas_proximalesP, ID_rom) VALUES('" + id_pulgar + "',1,0,0,'" + id_rom + "');");
                            }
        }
        if (preMedida.getLugar().equalsIgnoreCase("pulgar")) {
            id_angulo = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(angulos.ID_angulo) from goniometria.angulos;", "ANG-", 20);
            insertQuery(
                    "insert into angulos(ID_angulo, ID_medida, ID_pulgar, ID_cuatrodedos) VALUES ('" + id_angulo + "', " + "null" + ", '" + id_pulgar + "', null);");
        }else{
            id_angulo = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(angulos.ID_angulo) from goniometria.angulos;", "ANG-", 20);
            insertQuery(
                    "insert into angulos(ID_angulo, ID_medida, ID_pulgar, ID_cuatrodedos) VALUES ('" + id_angulo + "', " + "null" + ", null, '" + id_4ded + "');");
        }
        return id_angulo;
    }
    private boolean insertQuery(String query){
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
