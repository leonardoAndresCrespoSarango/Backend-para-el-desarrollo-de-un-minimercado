package ec.edu.ups.Bakend.Services;


import ec.edu.ups.Bakend.Entity.Stock_Entity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Stock_Service {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Stock_Entity> getListarStock() {
        String jpql = "SELECT u FROM Stock_Entity u";
        Query query = entityManager.createQuery(jpql, Stock_Entity.class);
        return query.getResultList();
    }

    public void insertarStock(Stock_Entity stock) {
        entityManager.persist(stock);
    }

    public void eliminarStock(long id) {
        Stock_Entity stockExistente = entityManager.find(Stock_Entity.class, id);
        if (stockExistente != null) {
            entityManager.remove(stockExistente);
        }
    }

    public Stock_Entity actualizarStock(long id, Stock_Entity stockActualizado) {
        Stock_Entity stockExistente = entityManager.find(Stock_Entity.class, id);
        if (stockExistente != null) {
            stockExistente.setCantidad(stockActualizado.getCantidad());
            entityManager.merge(stockExistente);
        }
        return stockExistente;
    }

    public Stock_Entity buscarPorId(long id) {
        return entityManager.find(Stock_Entity.class, id);
    }

}
