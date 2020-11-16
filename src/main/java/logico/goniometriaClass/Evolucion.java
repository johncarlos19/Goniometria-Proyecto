package logico.goniometriaClass;

import java.util.ArrayList;

public class Evolucion {
    private String idEvolucion;
    private String respuesta_tratamiento;
    private String colaborador;
    private String profesion;
    private String actividad;
    private String objetivo;
    private String conclusion;
    private String actividadEspecificar;
    private String retraso;
    private String pulso;
    private String temperatura;
    private String presion;
    private String id_documento;
    private String fecha;
    private ArrayList<String> terapiaIdList = new ArrayList<>();

    public Evolucion(String idEvolucion, String respuesta_tratamiento, String colaborador, String profesion, String actividad, String objetivo, String conclusion, String actividadEspecificar, String retraso, String pulso, String temperatura, String presion, String id_documento, ArrayList<String> terapiaIdList) {
        this.idEvolucion = idEvolucion;
        this.respuesta_tratamiento = respuesta_tratamiento;
        this.colaborador = colaborador;
        this.profesion = profesion;
        this.actividad = actividad;
        this.objetivo = objetivo;
        this.conclusion = conclusion;
        this.actividadEspecificar = actividadEspecificar;
        this.retraso = retraso;
        this.pulso = pulso;
        this.temperatura = temperatura;
        this.presion = presion;
        this.id_documento = id_documento;
        this.terapiaIdList = terapiaIdList;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isValidacion(){
        if (this.retraso == null){
            return true;
        }else{
            return false;
        }
    }

    public String getIdEvolucion() {
        return idEvolucion;
    }

    public void setIdEvolucion(String idEvolucion) {
        this.idEvolucion = idEvolucion;
    }

    public String getRespuesta_tratamiento() {
        return respuesta_tratamiento;
    }

    public void setRespuesta_tratamiento(String respuesta_tratamiento) {
        this.respuesta_tratamiento = respuesta_tratamiento;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getActividadEspecificar() {
        return actividadEspecificar;
    }

    public void setActividadEspecificar(String actividadEspecificar) {
        this.actividadEspecificar = actividadEspecificar;
    }

    public String getRetraso() {
        return retraso;
    }

    public void setRetraso(String retraso) {
        this.retraso = retraso;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getId_documento() {
        return id_documento;
    }

    public void setId_documento(String id_documento) {
        this.id_documento = id_documento;
    }

    public ArrayList<String> getTerapiaIdList() {
        return terapiaIdList;
    }

    public void setTerapiaIdList(ArrayList<String> terapiaIdList) {
        this.terapiaIdList = terapiaIdList;
    }
}
