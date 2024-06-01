package ec.edu.ups.Bakend.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Promotion_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long promotion_id;
    @Column(name = "order_name")
    private String nombre;
    @Column(name = "order_detail")
    private String descripcion;
    @Column(name = "order_startDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaInicio;
    @Column(name = "order_endDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaFin;

    public Promotion_Entity() {
    }

    public long getPromotion_id() {
        return promotion_id;
    }

    public void setPromotion_id(long promotion_id) {
        this.promotion_id = promotion_id;
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

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Promotion_Entity{" +
                "promotion_id=" + promotion_id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
