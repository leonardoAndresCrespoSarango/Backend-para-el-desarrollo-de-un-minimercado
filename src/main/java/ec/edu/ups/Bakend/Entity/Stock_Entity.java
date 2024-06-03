package ec.edu.ups.Bakend.Entity;
import javax.persistence.*;

@Entity
public class Stock_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stock_id;
    @Column(name = "stock_amount")
    private long cantidad;
    @Column(name = "stock_supplierID")
    private long proveedorid;


    @OneToOne
    @JoinColumn(name = "product_id")
    private Product_Entity product;


    public Stock_Entity() {
    }

    public long getStock_id() {
        return stock_id;
    }

    public void setStock_id(long stock_id) {
        this.stock_id = stock_id;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public long getProveedorid() {
        return proveedorid;
    }

    public void setProveedorid(long proveedorid) {
        this.proveedorid = proveedorid;
    }

    @Override
    public String toString() {
        return "Stock_Entity{" +
                "stock_id=" + stock_id +
                ", cantidad=" + cantidad +
                ", proveedorid=" + proveedorid +
                '}';
    }
}
