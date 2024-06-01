package ec.edu.ups.Bakend.Services;


import ec.edu.ups.Bakend.Entity.Supplier_Entity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Supplier_Service {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Supplier_Entity> getListarProveedores() {
        String jpql = "SELECT u FROM Supplier_Entity u";
        Query query = entityManager.createQuery(jpql, Supplier_Entity.class);
        return query.getResultList();
    }

    public void insertarProveedor(Supplier_Entity proveedor) {
        entityManager.persist(proveedor);
    }

    public void eliminarProveedor(long id) {
        Supplier_Entity proveedorExistente = entityManager.find(Supplier_Entity.class, id);
        if (proveedorExistente != null) {
            entityManager.remove(proveedorExistente);
        }
    }

    public Supplier_Entity actualizarProveedor(long id, Supplier_Entity proveedorActualizado) {
        Supplier_Entity proveedorExistente = entityManager.find(Supplier_Entity.class, id);
        if (proveedorExistente != null) {
            proveedorExistente.setNombre(proveedorActualizado.getNombre());
            proveedorExistente.setContacto(proveedorActualizado.getContacto());
            proveedorExistente.setDireccion(proveedorActualizado.getDireccion());
            proveedorExistente.setTelefono(proveedorActualizado.getTelefono());
            entityManager.merge(proveedorExistente);
        }
        return proveedorExistente;
    }

    public Supplier_Entity buscarPorId(long id) {
        return entityManager.find(Supplier_Entity.class, id);
    }

    public Supplier_Entity buscarPorProveedorNombre(String nombre) {
        String jpql = "SELECT u FROM Supplier_Entity u WHERE u.nombre = :nombre";
        Query query = entityManager.createQuery(jpql, Supplier_Entity.class);
        query.setParameter("nombre", nombre);
        List<Supplier_Entity> proveedor = query.getResultList();
        if (!proveedor.isEmpty()) {
            return proveedor.get(0);
        }
        return null;
    }

}
