package ec.edu.ups.Bakend.Entity;
import javax.persistence.*;
@Entity
public class Supplier_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long supplier_id;
    @Column(name = "supplier_name")
    private String nombre;
    @Column(name = "supplier_contact")
    private String contacto;
    @Column(name = "supplier_location")
    private String direccion;
    @Column(name = "supplier_phone")
    private String telefono;
    @Column(name = "supplier_userID")
    private long usuarioid;

    public Supplier_Entity() {
    }

    public long getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(long supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(long usuarioid) {
        this.usuarioid = usuarioid;
    }

    @Override
    public String toString() {
        return "Supplier_Entity{" +
                "supplier_id=" + supplier_id +
                ", nombre='" + nombre + '\'' +
                ", contacto='" + contacto + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", usuarioid=" + usuarioid +
                '}';
    }
}
