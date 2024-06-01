package ec.edu.ups.Bakend.Services;


import ec.edu.ups.Bakend.Entity.Order_Entity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Order_Service {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Order_Entity> getListarPedidos() {
        String jpql = "SELECT u FROM Order_Entity u";
        Query query = entityManager.createQuery(jpql, Order_Entity.class);
        return query.getResultList();
    }

    public void insertarPedido(Order_Entity pedido) {
        entityManager.persist(pedido);
    }

    public void eliminarPedido(long id) {
        Order_Entity pedidoExistente = entityManager.find(Order_Entity.class, id);
        if (pedidoExistente != null) {
            entityManager.remove(pedidoExistente);
        }
    }

    public Order_Entity actualizarPedido(long id, Order_Entity pedidoActualizado) {
        Order_Entity pedidoExistente = entityManager.find(Order_Entity.class, id);
        if (pedidoExistente != null) {
            pedidoExistente.setFechaPedido(pedidoActualizado.getFechaPedido());
            pedidoExistente.setFechaEntrega(pedidoActualizado.getFechaEntrega());
            entityManager.merge(pedidoExistente);
        }
        return pedidoExistente;
    }

    public Order_Entity buscarPorId(long id) {
        return entityManager.find(Order_Entity.class, id);
    }

    public Order_Entity buscarPorPedido_ProveedorID(long id) {
        String jpql = "SELECT u FROM Order_Entity u WHERE u.proveedorid = :id";
        Query query = entityManager.createQuery(jpql, Order_Entity.class);
        query.setParameter("id", id);
        List<Order_Entity> pedido = query.getResultList();
        if (!pedido.isEmpty()) {
            return pedido.get(0);
        }
        return null;
    }
}
