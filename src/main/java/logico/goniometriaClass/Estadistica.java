package logico.goniometriaClass;

import com.fasterxml.jackson.databind.ObjectMapper;
import logico.servicios.MedidaServicios;
import logico.servicios.TerapiaServicios;

import java.util.ArrayList;
import java.util.List;



public class Estadistica {
    private String mes;
    private String dia;
    private String medida;
    private String lugar;
    private String rom;
    private String idPaciente;
    private String value_mes;
    private String value_dias;
    private double valorMayor;
    private int cant_mes;
    private int cant_dia;
    private List<Medida> medidaList = new ArrayList<Medida>();


    public Estadistica() {
    }

    public Estadistica(String medida, String lugar, String rom,String idPaciente) {
        this.medida = medida;
        this.lugar = lugar;
        this.rom = rom;
        this.idPaciente = idPaciente;
        int numeroMes = 1;
        double rom_valueMes = 0;
        int cantMes = 1;
        int numeroDias = 1;
        double rom_valueDias = 0;
        int cantDias = 1;
        boolean entroDia = false;
        boolean entroMes = false;
        double valorAlto = 0;
        List<String> mesList = new ArrayList<String>();
        List<String> mesromValueList = new ArrayList<String>();
        List<String> diasList = new ArrayList<String>();
        List<String> diasromValueList = new ArrayList<String>();
        for (Medida medida1: TerapiaServicios.getInstance().listaMedida(idPaciente," order by ID_paciente ASC")
             ) {
            if (medida1.getMedida().equalsIgnoreCase(medida) && medida1.getRom().equalsIgnoreCase(rom) && medida1.getLugar().equalsIgnoreCase(lugar) ){
                this.medidaList.add(medida1);
                if (valorAlto < medida1.getRom_value()){
                    valorAlto = medida1.getRom_value();
                }
                if(rom_valueMes ==0 && rom_valueDias==0){
                    rom_valueMes = medida1.getRom_value();
                    rom_valueDias = medida1.getRom_value();

                }
                if (medida1.getFecha_realizacionTimeStamp().getMonth()+1 != numeroMes){
                    if (entroMes == false){
                        rom_valueMes = medida1.getRom_value();

                    }
                    entroMes = false;
                    numeroMes = medida1.getFecha_realizacionTimeStamp().getMonth()+1;
                    mesList.add(retornarMes(numeroMes));
                    mesromValueList.add(Double.toString(round(rom_valueMes/cantMes,2)));
                    cantMes = 1;
                    this.cant_mes += cantMes;
                    rom_valueMes = medida1.getRom_value();
                }else{
                    rom_valueMes += medida1.getRom_value();
                    cantMes +=1;
                    entroMes = true;
                }
                if (medida1.getFecha_realizacionTimeStamp().getDate() != numeroDias){
                    if (entroDia == false){
                        rom_valueDias = medida1.getRom_value();

                    }
                    entroDia = false;
                    numeroDias = medida1.getFecha_realizacionTimeStamp().getDate();
                    diasList.add(retornarMes(medida1.getFecha_realizacionTimeStamp().getMonth()+1)+"-"+Integer.toString(numeroDias));
                    diasromValueList.add(Double.toString(round(rom_valueDias/cantDias,2)));
                    cantDias = 1;
                    this.cant_dia += cantDias;
                    rom_valueDias = medida1.getRom_value();
                }else{
                    rom_valueDias += medida1.getRom_value();
                    cantDias +=1;
                    entroDia = true;
                }

            }

        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.valorMayor = valorAlto;
            this.mes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mesList);
            System.out.println(mes);
            this.value_mes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mesromValueList);
            System.out.println(value_mes);
            this.dia = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(diasList);
            System.out.println(dia);
            this.value_dias = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(diasromValueList);
            System.out.println(value_dias);
        } catch(Exception e) {
            e.printStackTrace();
        }



    }



    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public String getValorMayor() {
        return Double.toString(round(valorMayor,2));
    }

    public void setValorMayor(double valorMayor) {
        this.valorMayor = valorMayor;
    }

    public String  getAnguloReferencia(){
        double angulo_ref = 90;

    if(lugar.equalsIgnoreCase("pulgar")){

        if(medida.equalsIgnoreCase("interfalangicas proximales")){
            switch (rom){
                case "Extension":
                    angulo_ref =  90;
                    break;
                default:
                    angulo_ref =  90;
                    break;
            }
        }

        else if(medida.equalsIgnoreCase("metocarpofalangicas")){
            switch (rom){
                case "Flexion":
                    angulo_ref =  90;
                break;
                case "Extension":
                    angulo_ref =  10;
                break;
                default:
                    angulo_ref =  90;
                break;
            }
        }

        else if(medida.equalsIgnoreCase("carpometacarpiana")){
            switch (rom){
                case "Flexion":
                    angulo_ref =  20;
                break;
                case "Extension":
                    angulo_ref =  30;
                break;
                case "abduccion":
                    angulo_ref =  60;
                break;
                default:
                    angulo_ref =  90;
                break;
            }
        }

    }else {
        if(medida.equalsIgnoreCase("interfalangicas Distales")){
            switch (rom){
                case "Flexion":
                    angulo_ref =  75;
                break;
                case "Extension":
                    angulo_ref =  5;
                break;
                default:
                    angulo_ref =  90;
                break;
            }
        }

        else if(medida.equalsIgnoreCase("interfalangicas proximales")){
            switch (rom){
                case "Flexion":
                    angulo_ref =  90;
                break;
                default:
                    angulo_ref =  90;
                break;
            }
        }
        else if(medida.equalsIgnoreCase("metocarpofalangicas")){
            switch (rom){
                case "Flexion":
                    angulo_ref =  80;
                break;
                case "Extension":
                    angulo_ref =  30;
                break;
                case "abduccion":
                    angulo_ref =  25;
                break;
                case "aduccion":
                    angulo_ref =  20;
                break;
                default:
                    angulo_ref =  90;
                break;
            }
        }

    }
    return Double.toString(round( angulo_ref - valorMayor,2));
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getValue_mes() {
        return value_mes;
    }

    public void setValue_mes(String value_mes) {
        this.value_mes = value_mes;
    }

    public String getValue_dias() {
        return value_dias;
    }

    public void setValue_dias(String value_dias) {
        this.value_dias = value_dias;
    }

    public int getCant_mes() {
        return cant_mes;
    }

    public void setCant_mes(int cant_mes) {
        this.cant_mes = cant_mes;
    }

    public int getCant_dia() {
        return cant_dia;
    }

    public void setCant_dia(int cant_dia) {
        this.cant_dia = cant_dia;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public List<Medida> getMedidaList() {
        return medidaList;
    }

    public void setMedidaList(List<Medida> medidaList) {
        this.medidaList = medidaList;
    }

    public String retornarMes(int mes){
        String valor = null;
        switch (mes){
            case 1:
                valor = "Enero";
                break;
            case 2:
                valor = "Febrero";
                break;
            case 3:
                valor = "Marzo";
                break;
            case 4:
                valor = "Abril";
                break;
            case 5:
                valor = "Mayo";
                break;
            case 6:
                valor = "Junio";
                break;
            case 7:
                valor = "Julio";
                break;
            case 8:
                valor = "Agosto";
                break;
            case 9:
                valor = "Septiembre";
                break;
            case 10:
                valor = "Octubre";
                break;
            case 11:
                valor = "Noviembre";
                break;
            case 12:
                valor = "Diciembre";
                break;
            default:
                valor =  null;
                break;
        }
    return valor;
    }

}
