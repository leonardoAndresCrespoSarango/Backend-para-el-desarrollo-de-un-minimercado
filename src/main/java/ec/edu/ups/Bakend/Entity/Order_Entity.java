package ec.edu.ups.Bakend.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Order_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;
    @Column(name = "order_supplierID")
    private long proveedorid;
    @Column(name = "order_orderDate")
    private LocalDateTime fechaPedido;
    @Column(name = "order_deadline")
    private LocalDateTime fechaEntrega;

    public Order_Entity() {
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getProveedorid() {
        return proveedorid;
    }

    public void setProveedorid(long proveedorid) {
        this.proveedorid = proveedorid;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Override
    public String toString() {
        return "Order_Entity{" +
                "order_id=" + order_id +
                ", proveedorid=" + proveedorid +
                ", fechaPedido=" + fechaPedido +
                ", fechaEntrega=" + fechaEntrega +
                '}';
    }
}
