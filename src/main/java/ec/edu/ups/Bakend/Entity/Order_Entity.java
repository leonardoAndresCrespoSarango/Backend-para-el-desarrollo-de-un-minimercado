package ec.edu.ups.Bakend.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.List;


@Entity
public class Order_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;
    @Column(name = "order_supplierID")
    private long proveedorid;
    @Column(name = "order_orderDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaPedido;
    @Column(name = "order_deadline")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaEntrega;

    //Conexión producto
    @Column(name= "order_productID")
    private long product_id;

    @OneToMany(mappedBy = "order")
    private List<Product_Entity> products;


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

    //Getter y setter producto
    public long getProduct_id() {
        return product_id;
    }
    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Order_Entity{" +
                "order_id=" + order_id +
                ", proveedorid=" + proveedorid +
                ", fechaPedido=" + fechaPedido +
                ", fechaEntrega=" + fechaEntrega +
                ", product_id=" + product_id +
                ", products=" + products +
                '}';
    }
}
