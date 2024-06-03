package ec.edu.ups.Bakend.Entity;

import javax.persistence.*;

@Entity
public class User_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    @Column(name = "user_name")
    private String nombre;
    @Column(name = "user_mail")
    private String Correo;
    @Column(name = "user_password")
    private String Contrasenia;
    @Column(name = "user_rol")
    private String rol;

    @OneToOne(mappedBy = "user")
    private Client_Entity client;

    public User_Entity( String nombre, String correo, String contrasenia, String rol) {
        this.nombre = nombre;
        Correo = correo;
        Contrasenia = contrasenia;
        this.rol = rol;
    }

    public User_Entity() {

    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "User_Entity{" +
                "user_id=" + user_id +
                ", nombre='" + nombre + '\'' +
                ", Correo='" + Correo + '\'' +
                ", Contrasenia='" + Contrasenia + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
