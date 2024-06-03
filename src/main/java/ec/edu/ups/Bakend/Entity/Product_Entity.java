package ec.edu.ups.Bakend.Entity;
import javax.persistence.*;

import java.util.List;


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
    @Column(name = "product_stockID")
    private long stock;

   
    @OneToMany(mappedBy = "producto")
    private List<Sale_Detail_Entity> saleDetails;

    @ManyToMany
    @JoinTable(
        name = "producto_promocion",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "promocion_id")
    )
    private List<Promotion_Entity> promociones;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order_Entity order;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier_Entity supplier;

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

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
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
