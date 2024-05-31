package ec.edu.ups.Bakend.Entity;
import javax.persistence.*;


@Entity
public class Product_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long product_id;
    @Column(name = "product_name")
    private String nombre;
    @Column(name = "product_description")
    private String descripcion;
    @Column(name = "product_category")
    private String categoria;
    @Column(name = "product_unitPrice")
    private String precioUnitario;
    @Column(name = "product_stock")
    private int stock;

    public Product_Entity() {
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product_Entity{" +
                "product_id=" + product_id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precioUnitario='" + precioUnitario + '\'' +
                ", stock=" + stock +
                '}';
    }
}
