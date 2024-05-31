package ec.edu.ups.Bakend.Entity;
import javax.persistence.*;

@Entity
public class Client_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cient_id;
    @Column(name = "client_name")
    private String nombre;
    @Column(name = "client_mail")
    private String correo;
    @Column(name = "client_phone")
    private String telefono;

    public Client_Entity() {

    }

    public long getCient_id() {
        return cient_id;
    }

    public void setCient_id(long cient_id) {
        this.cient_id = cient_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Client_Entity{" +
                "cient_id=" + cient_id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
