package logico.controlador;

public class Create_Person {
    private String email;
    private String sector;
    private String user;
    private String apellido;
    private String ciudad;
    private String phone;
    private String nume_residen;
    private String pais;
    private String datedeli;
    private String especialidad;
    private String nombre;
    private String calle;
    private String cedula;
    private String pass;
    private String sexo;

    public Create_Person() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNume_residen() {
        return nume_residen;
    }

    public void setNume_residen(String nume_residen) {
        this.nume_residen = nume_residen;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDatedeli() {
        return datedeli;
    }

    public void setDatedeli(String datedeli) {
        this.datedeli = datedeli;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSexo() {
        return pass;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setValue(String key, String value){
        switch (key){
            case "email":
                this.email = value;
                break;
            case "sector":
                this.sector = value;
                break;
            case "user":
                this.user = value;
                break;
            case "apellido":
                this.apellido = value;
                break;
            case "ciudad":
                this.ciudad = value;
                break;
            case "phone":
                this.phone = value;
                break;
            case "nume_residen":
                this.nume_residen = value;
                break;
            case "pais":
                this.pais = value;
                break;
            case "datedeli":
                this.datedeli = value;
                break;
            case "especialidad":
                this.especialidad = value;
                break;
            case "nombre":
                this.nombre = value;
                break;
            case "calle":
                this.calle = value;
                break;
            case "cedula":
                this.cedula = value;
                break;
            case "pass":
                this.pass = value;
                break;
            case "sexo":
                this.sexo = value;
                break;

        }
    }
}
