package ec.edu.ups.Bakend.Services;


import ec.edu.ups.Bakend.Entity.Sale_Detail_Entity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Sale_Detal_Service {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Sale_Detail_Entity> getListarVentasDetalle() {
        String jpql = "SELECT u FROM Sale_Detail_Entity u";
        Query query = entityManager.createQuery(jpql, Sale_Detail_Entity.class);
        return query.getResultList();
    }
    public void insertarVentaDetalle(Sale_Detail_Entity ventaDetalle) {
        entityManager.persist(ventaDetalle);
    }
    public void eliminarVentaDetalle(long id) {
        Sale_Detail_Entity ventaDetalleExistente = entityManager.find(Sale_Detail_Entity.class, id);
        if (ventaDetalleExistente != null) {
            entityManager.remove(ventaDetalleExistente);
        }
    }

    public Sale_Detail_Entity actualizarVentaDetalle(long id, Sale_Detail_Entity ventaDetalleActualizado) {
        Sale_Detail_Entity ventaDetalleExistente = entityManager.find(Sale_Detail_Entity.class, id);
        if (ventaDetalleExistente != null) {
            ventaDetalleExistente.setCantidad(ventaDetalleActualizado.getCantidad());
            ventaDetalleExistente.setPrecio(ventaDetalleActualizado.getPrecio());
            entityManager.merge(ventaDetalleExistente);
        }
        return ventaDetalleExistente;
    }

    public Sale_Detail_Entity buscarPorIdVentaDetalle(long id) {
        return entityManager.find(Sale_Detail_Entity.class, id);
    }

    public Sale_Detail_Entity buscarPorVentaDetalle_VentaID(long id) {
        String jpql = "SELECT u FROM Sale_Detail_Entity u WHERE u.sale_id = :id";
        Query query = entityManager.createQuery(jpql, Sale_Detail_Entity.class);
        query.setParameter("id", id);
        List<Sale_Detail_Entity> ventaDetalle = query.getResultList();
        if (!ventaDetalle.isEmpty()) {
            return ventaDetalle.get(0);
        }
        return null;
    }

    public List<Sale_Detail_Entity> getListarVentasPorIDCabecera(long id) {
        String jpql = "SELECT u FROM Sale_Detail_Entity u WHERE u.sale_id = :id";
        Query query = entityManager.createQuery(jpql, Sale_Detail_Entity.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
