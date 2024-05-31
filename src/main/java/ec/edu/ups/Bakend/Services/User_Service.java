package ec.edu.ups.Bakend.Services;

import ec.edu.ups.Bakend.Entity.User_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class User_Service {
    private final RestTemplate restTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    public User_Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User_Entity> getListarUsuario() {
        String jpql = "SELECT u FROM Usuario u";
        Query query = entityManager.createQuery(jpql, User_Entity.class);
        return query.getResultList();
    }

    public void insertarUsuario(User_Entity usuario) {
        User_Entity usuarioExistente = buscarPorUsuarioNombre(usuario.getNombre());
        if (usuarioExistente == null) {
            entityManager.persist(usuario);
        } else {
            throw new RuntimeException("El usuario ya existe");
        }
    }

    public void eliminarUsuario(int id) {
        User_Entity usuarioExistente = entityManager.find(User_Entity.class, id);
        if (usuarioExistente != null) {
            entityManager.remove(usuarioExistente);
        }
    }

    public User_Entity actualizarUsuario(int id, User_Entity usuarioActualizado) {
        User_Entity usuarioExistente = entityManager.find(User_Entity.class, id);
        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            usuarioExistente.setContrasenia(usuarioActualizado.getContrasenia());
            usuarioExistente.setRol(usuarioActualizado.getRol());
            entityManager.merge(usuarioExistente);
        }
        return usuarioExistente;
    }

    public User_Entity buscarPorId(int id) {
        return entityManager.find(User_Entity.class, id);
    }

    public User_Entity buscarPorUsuarioNombre(String nombre) {
        String jpql = "SELECT u FROM Usuario u WHERE u.nombre = :nombre";
        Query query = entityManager.createQuery(jpql, User_Entity.class);
        query.setParameter("nombre", nombre);
        List<User_Entity> usuarios = query.getResultList();
        if (!usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        return null;
    }

    public User_Entity buscarPorUsuarioYContrasenia(String usu, String contrasenia) {
        String jpql = "SELECT u FROM User_Entity u WHERE u.nombre = :usu AND u.contrasenia = :contrasenia";
        Query query = entityManager.createQuery(jpql, User_Entity.class);
        query.setParameter("usu", usu);
        query.setParameter("contrasenia", contrasenia);
        List<User_Entity> usuarios = query.getResultList();
        if (!usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        return null;
    }
}
