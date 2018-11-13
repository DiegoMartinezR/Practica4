package diego.rayme.martinez.practica4.model;

public class Solicitud {

    private Integer id;
    private String correo;
    private String tipo;
    private String razon;
    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", correo='" + correo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", razon='" + razon + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
