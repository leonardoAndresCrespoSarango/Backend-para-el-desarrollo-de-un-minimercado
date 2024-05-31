package ec.edu.ups.Bakend.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Sale_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sale_id;
    @Column(name = "sale_date")
    private LocalDateTime fecha_venta;
    @Column(name = "sale_clientID")
    private String cliente_id;
    @Column(name = "sale_total")
    private double total;

    public Sale_Entity() {}

    public long getSale_id() {
        return sale_id;
    }

    public void setSale_id(long sale_id) {
        this.sale_id = sale_id;
    }

    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Sale_Entity{" +
                "sale_id=" + sale_id +
                ", fecha_venta=" + fecha_venta +
                ", cliente_id='" + cliente_id + '\'' +
                ", total=" + total +
                '}';
    }
}
