package ec.edu.ups.Bakend.Entity;
import javax.persistence.*;


@Entity
public class Sale_Detail_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long saleDet_id;
    @Column(name = "saleDet_saleID")
    private long sale_id;
    @Column(name = "saleDet_productID")
    private long product_id;
    @Column(name = "saleDet_amount")
    private long cantidad;
    @Column(name = "saleDet_price")
    private double precio;


    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Product_Entity producto;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale_Entity sale;

    public Sale_Detail_Entity() {
    }

    public long getSaleDet_id() {
        return saleDet_id;
    }

    public void setSaleDet_id(long saleDet_id) {
        this.saleDet_id = saleDet_id;
    }

    public long getSale_id() {
        return sale_id;
    }

    public void setSale_id(long sale_id) {
        this.sale_id = sale_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Sale_Detail_Entity{" +
                "saleDet_id=" + saleDet_id +
                ", sale_id=" + sale_id +
                ", product_id=" + product_id +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
