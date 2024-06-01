package ec.edu.ups.Bakend.Services;



import ec.edu.ups.Bakend.Entity.Promotion_Entity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class Promotion_Service {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Promotion_Entity> getListarPromociones() {
        String jpql = "SELECT u FROM Promotion_Entity u";
        Query query = entityManager.createQuery(jpql, Promotion_Entity.class);
        return query.getResultList();
    }

    public void insertarPromocion(Promotion_Entity promocion) {
        entityManager.persist(promocion);
    }

    public void eliminarPromocion(long id) {
        Promotion_Entity promocionExistente = entityManager.find(Promotion_Entity.class, id);
        if (promocionExistente != null) {
            entityManager.remove(promocionExistente);
        }
    }

    public Promotion_Entity actualizarPromocion(long id, Promotion_Entity promocionActualizado) {
        Promotion_Entity promocionExistente = entityManager.find(Promotion_Entity.class, id);
        if (promocionExistente != null) {
            promocionExistente.setNombre(promocionActualizado.getNombre());
            promocionExistente.setDescripcion(promocionActualizado.getDescripcion());
            promocionExistente.setFechaInicio(promocionActualizado.getFechaInicio());
            promocionExistente.setFechaFin(promocionActualizado.getFechaFin());
            entityManager.merge(promocionExistente);
        }
        return promocionExistente;
    }

    public Promotion_Entity buscarPorId(long id) {
        return entityManager.find(Promotion_Entity.class, id);
    }

    public Promotion_Entity buscarPorPromocionNombre(String nombre) {
        String jpql = "SELECT u FROM Promotion_Entity u WHERE u.nombre = :nombre";
        Query query = entityManager.createQuery(jpql, Promotion_Entity.class);
        query.setParameter("nombre", nombre);
        List<Promotion_Entity> promocion = query.getResultList();
        if (!promocion.isEmpty()) {
            return promocion.get(0);
        }
        return null;
    }
}
