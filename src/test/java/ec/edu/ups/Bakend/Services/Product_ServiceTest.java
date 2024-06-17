package ec.edu.ups.Bakend.Services;

import ec.edu.ups.Bakend.Entity.Product_Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Product_ServiceTest {

    @InjectMocks
    private Product_Service productService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Product_Entity> typedQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListarProductos() {
        Product_Entity producto1 = new Product_Entity();
        producto1.setNombre("Producto1");
        producto1.setDescripcion("Descripción de Producto1");
        producto1.setCategoria("Categoria1");
        producto1.setPrecioUnitario("10.5");
        producto1.setStock(50);

        Product_Entity producto2 = new Product_Entity();
        producto2.setNombre("Producto2");
        producto2.setDescripcion("Descripción de Producto2");
        producto2.setCategoria("Categoria2");
        producto2.setPrecioUnitario("20.75");
        producto2.setStock(100);

        List<Product_Entity> productos = Arrays.asList(producto1, producto2);

        when(entityManager.createQuery(anyString(), eq(Product_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(productos);

        List<Product_Entity> result = productService.getListarProductos();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Producto1", result.get(0).getNombre());
    }

    @Test
    public void testInsertarProducto() {
        Product_Entity producto = new Product_Entity();
        producto.setNombre("Producto Test");
        producto.setDescripcion("Descripción de Producto Test");
        producto.setCategoria("Categoria Test");
        producto.setPrecioUnitario("15.25");
        producto.setStock(75);

        productService.insertarProduccto(producto);

        verify(entityManager, times(1)).persist(producto);
    }


    @Test
    public void testEliminarProducto() {
        Product_Entity producto = new Product_Entity();
        producto.setProduct_id(1L);
        producto.setNombre("Producto Test");
        producto.setDescripcion("Descripción de Producto Test");
        producto.setCategoria("Categoria Test");
        producto.setPrecioUnitario("15.25");
        producto.setStock(75);

        when(entityManager.find(Product_Entity.class, 1L)).thenReturn(producto);

        productService.eliminarProducto(1L);

        verify(entityManager, times(1)).remove(producto);
    }

    @Test
    public void testActualizarProducto() {
        Product_Entity existingProducto = new Product_Entity();
        existingProducto.setProduct_id(1L);
        existingProducto.setNombre("Producto Test");
        existingProducto.setDescripcion("Descripción de Producto Test");
        existingProducto.setCategoria("Categoria Test");
        existingProducto.setPrecioUnitario("15.25");
        existingProducto.setStock(75);

        Product_Entity updatedProducto = new Product_Entity();
        updatedProducto.setNombre("Producto Modificado");
        updatedProducto.setDescripcion("Descripción Modificada");
        updatedProducto.setCategoria(existingProducto.getCategoria());
        updatedProducto.setPrecioUnitario(existingProducto.getPrecioUnitario());
        updatedProducto.setStock(existingProducto.getStock());

        when(entityManager.find(Product_Entity.class, 1L)).thenReturn(existingProducto);

        Product_Entity result = productService.actualizarProducto(1L, updatedProducto);

        assertNotNull(result);
        assertEquals("Producto Modificado", result.getNombre());

        verify(entityManager, times(1)).merge(existingProducto);
    }

    @Test
    public void testBuscarPorIdProducto() {
        Product_Entity producto = new Product_Entity();
        producto.setProduct_id(1L);
        producto.setNombre("Producto Test");
        producto.setDescripcion("Descripción de Producto Test");
        producto.setCategoria("Categoria Test");
        producto.setPrecioUnitario("15.25");
        producto.setStock(75);

        when(entityManager.find(Product_Entity.class, 1L)).thenReturn(producto);

        Product_Entity result = productService.buscarPorIdProducto(1L);

        assertNotNull(result);
        assertEquals("Producto Test", result.getNombre());
    }



}
