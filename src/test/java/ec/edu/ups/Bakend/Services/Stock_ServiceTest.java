package ec.edu.ups.Bakend.Services;

import ec.edu.ups.Bakend.Entity.Stock_Entity;
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

public class Stock_ServiceTest {

    @InjectMocks
    private Stock_Service stockService;

    @Mock
    private EntityManager entityManager;


    @Mock
    private TypedQuery<Stock_Entity> typedQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListarStock() {
        Stock_Entity stock1 = new Stock_Entity();
        stock1.setCantidad(100);
        stock1.setProveedorid(1L);

        Stock_Entity stock2 = new Stock_Entity();
        stock2.setCantidad(200);
        stock2.setProveedorid(2L);

        List<Stock_Entity> stocks = Arrays.asList(stock1, stock2);

        when(entityManager.createQuery(anyString(), eq(Stock_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(stocks);

        List<Stock_Entity> result = stockService.getListarStock();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(100, result.get(0).getCantidad());
    }

    @Test
    public void testInsertarStock() {
        Stock_Entity stock = new Stock_Entity();
        stock.setCantidad(100);
        stock.setProveedorid(1L);

        stockService.insertarStock(stock);

        verify(entityManager, times(1)).persist(stock);
    }

    @Test
    public void testEliminarStock() {
        Stock_Entity stock = new Stock_Entity();
        stock.setCantidad(100);
        stock.setProveedorid(1L);
        stock.setStock_id(1L);

        when(entityManager.find(Stock_Entity.class, 1L)).thenReturn(stock);

        stockService.eliminarStock(1L);

        verify(entityManager, times(1)).remove(stock);
    }

    @Test
    public void testActualizarStock() {
        Stock_Entity existingStock = new Stock_Entity();
        existingStock.setCantidad(100);
        existingStock.setProveedorid(1L);
        existingStock.setStock_id(1L);

        Stock_Entity updatedStock = new Stock_Entity();
        updatedStock.setCantidad(200);

        when(entityManager.find(Stock_Entity.class, 1L)).thenReturn(existingStock);

        Stock_Entity result = stockService.actualizarStock(1L, updatedStock);

        assertNotNull(result);
        assertEquals(200, result.getCantidad());

        verify(entityManager, times(1)).merge(existingStock);
    }

    @Test
    public void testBuscarPorId() {
        Stock_Entity stock = new Stock_Entity();
        stock.setCantidad(100);
        stock.setProveedorid(1L);
        stock.setStock_id(1L);

        when(entityManager.find(Stock_Entity.class, 1L)).thenReturn(stock);

        Stock_Entity result = stockService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getStock_id());
        assertEquals(100, result.getCantidad());
    }
}
