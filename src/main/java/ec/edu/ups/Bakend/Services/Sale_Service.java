package ec.edu.ups.Bakend.Services;


import ec.edu.ups.Bakend.Entity.Sale_Entity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Sale_Service {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Sale_Entity> getListarVentas() {
        String jpql = "SELECT u FROM Sale_Entity u";
        Query query = entityManager.createQuery(jpql, Sale_Entity.class);
        return query.getResultList();
    }
    public void insertarVenta(Sale_Entity venta) {
            entityManager.persist(venta);
    }
    public void eliminarVenta(long id) {
        Sale_Entity ventaExistente = entityManager.find(Sale_Entity.class, id);
        if (ventaExistente != null) {
            entityManager.remove(ventaExistente);
        }
    }

    public Sale_Entity actualizarVenta(long id, Sale_Entity ventaActualizado) {
        Sale_Entity ventaExistente = entityManager.find(Sale_Entity.class, id);
        if (ventaExistente != null) {
            ventaExistente.setTotal(ventaActualizado.getTotal());
            entityManager.merge(ventaExistente);
        }
        return ventaExistente;
    }

    public Sale_Entity buscarPorIdVenta(long id) {
        return entityManager.find(Sale_Entity.class, id);
    }

    public Sale_Entity buscarPorVentaClienteID(long id) {
        String jpql = "SELECT u FROM Sale_Entity u WHERE u.cliente_id = :id";
        Query query = entityManager.createQuery(jpql, Sale_Entity.class);
        query.setParameter("id", id);
        List<Sale_Entity> venta = query.getResultList();
        if (!venta.isEmpty()) {
            return venta.get(0);
        }
        return null;
    }

    public Sale_Entity buscarPorVentaNumeroVenta(String numero_venta) {
        String jpql = "SELECT u FROM Sale_Entity u WHERE u.numero_venta = :numero_venta";
        Query query = entityManager.createQuery(jpql, Sale_Entity.class);
        query.setParameter("numero_venta", numero_venta);
        List<Sale_Entity> venta = query.getResultList();
        if (!venta.isEmpty()) {
            return venta.get(0);
        }
        return null;
    }


}
