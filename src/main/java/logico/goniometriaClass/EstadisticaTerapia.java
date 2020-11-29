package logico.goniometriaClass;

import com.fasterxml.jackson.databind.ObjectMapper;
import logico.servicios.MedidaServicios;
import logico.servicios.TerapiaServicios;

import java.util.ArrayList;
import java.util.List;

public class EstadisticaTerapia {
    private List<MedidaEstadistica> medidaEstadisticaList = new ArrayList<MedidaEstadistica>();
    private long cantidad = 0;
    private String mes;
    private String dia;
    private String value_mes;
    private String value_dias;
    private double valorMayor;
    private int cant_mes = 1;
    private int cant_dia = 1;
    private String especialista;


    public EstadisticaTerapia(String especialista) {
        this.especialista  = especialista;
        int numeroMes = 0;
        int rom_valueMes = 0;
        int cantMes = 0;
        int numeroDias = 0;
        int rom_valueDias = 0;
        int cantDias = 0;
        boolean entroDia = false;
        boolean entroMes = false;
        double valorAlto = 0;
        List<String> mesList = new ArrayList<String>();
        List<String> mesromValueList = new ArrayList<String>();
        List<String> diasList = new ArrayList<String>();
        List<String> diasromValueList = new ArrayList<String>();
        for (MedidaEstadistica medida1: MedidaServicios.getInstance().listaMedidaEstadistica(especialista)
        ) {

                this.medidaEstadisticaList.add(medida1);
                if(entroDia ==false && entroMes==false){
                    rom_valueMes = 0;
                    rom_valueDias = 0;
                    numeroMes = medida1.getFecha_realizacion().getMonth()+1;
                    numeroDias = medida1.getFecha_realizacion().getDate();
                    entroDia = true;
                    entroMes = true;

                }

            if (medida1.getFecha_realizacion().getDate() != numeroDias){


                diasList.add(retornarMes(numeroMes)+"-"+Integer.toString(numeroDias));
                diasromValueList.add(Double.toString(round(rom_valueDias,2)));
                numeroDias = medida1.getFecha_realizacion().getDate();
                cantDias = 1;
                this.cant_dia += cantDias;
                rom_valueDias = 1;
            }else{
                rom_valueDias += 1;
                cantDias +=1;
            }
                if (medida1.getFecha_realizacion().getMonth()+1 != numeroMes){

                    System.out.println("valor mes"+rom_valueMes);

                    mesList.add(retornarMes(numeroMes));
                    mesromValueList.add(Double.toString(round(((double) rom_valueMes),2)));
                    numeroMes = medida1.getFecha_realizacion().getMonth()+1;
                    cantMes = 1;
                    this.cant_mes += cantMes;
                    rom_valueMes = 1;

                }else{

                    rom_valueMes += 1;
                    cantMes +=1;

                }




        }
        mesList.add(retornarMes(numeroMes));
        mesromValueList.add(Double.toString(round(rom_valueMes,2)));
        diasList.add(retornarMes(numeroMes)+"-"+Integer.toString(numeroDias));
        diasromValueList.add(Double.toString(round(rom_valueDias,2)));
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

    public List<MedidaEstadistica> getMedidaEstadisticaList() {
        return medidaEstadisticaList;
    }

    public void setMedidaEstadisticaList(List<MedidaEstadistica> medidaEstadisticaList) {
        this.medidaEstadisticaList = medidaEstadisticaList;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
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

    public double getValorMayor() {
        return valorMayor;
    }

    public void setValorMayor(double valorMayor) {
        this.valorMayor = valorMayor;
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
}
