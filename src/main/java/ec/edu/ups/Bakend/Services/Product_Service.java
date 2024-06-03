package ec.edu.ups.Bakend.Services;


import ec.edu.ups.Bakend.Entity.Product_Entity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Product_Service {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Product_Entity> getListarProductos() {
        String jpql = "SELECT u FROM Product_Entity u";
        Query query = entityManager.createQuery(jpql, Product_Entity.class);
        return query.getResultList();
    }
    public void insertarProduccto(Product_Entity producto) {
        Product_Entity productoExistente = buscarPorProductoNombre(producto.getNombre());
        if (productoExistente == null) {
            entityManager.persist(producto);
        } else {
            throw new RuntimeException("El producto ya existe");
        }
    }
    public void eliminarProducto(long id) {
        Product_Entity productoExistente = entityManager.find(Product_Entity.class, id);
        if (productoExistente != null) {
            entityManager.remove(productoExistente);
        }
    }

    public Product_Entity actualizarProducto(long id, Product_Entity productoActualizado) {
        Product_Entity productoExistente = entityManager.find(Product_Entity.class, id);
        if (productoExistente != null) {
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setDescripcion(productoActualizado.getDescripcion());
            productoExistente.setCategoria(productoActualizado.getCategoria());
            productoExistente.setPrecioUnitario(productoActualizado.getPrecioUnitario());
            productoExistente.setStock(productoActualizado.getStock());
            entityManager.merge(productoExistente);
        }
        return productoExistente;
    }

    public Product_Entity buscarPorIdProducto(long id) {
        return entityManager.find(Product_Entity.class, id);
    }

    public Product_Entity buscarPorProductoNombre(String nombre) {
        String jpql = "SELECT u FROM Product_Entity u WHERE u.nombre = :nombre";
        Query query = entityManager.createQuery(jpql, Product_Entity.class);
        query.setParameter("nombre", nombre);
        List<Product_Entity> producto = query.getResultList();
        if (!producto.isEmpty()) {
            return producto.get(0);
        }
        return null;
    }
}
