package logico.goniometriaClass;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Documento {
    private String idDocumento;
    private String base64;
    private String mimeType;
    private String nombre;
    private String idPaciente;


    public Documento( String base64, String mimeType, String nombre, String idPaciente) throws UnsupportedEncodingException {
        this.idDocumento = Goniometria.getInstance().return_ID_Generate_Max_Id("select max(Documentos.id_documento) from goniometria.Documentos","DOC-",20);
        this.base64 = base64;
        this.mimeType = mimeType;
        this.nombre = new String(nombre.getBytes("UTF-8"), "ISO-8859-1");
        this.idPaciente = idPaciente;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
}
