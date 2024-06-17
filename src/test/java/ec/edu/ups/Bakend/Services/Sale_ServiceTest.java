package ec.edu.ups.Bakend.Services;

import ec.edu.ups.Bakend.Entity.Client_Entity;
import ec.edu.ups.Bakend.Entity.Sale_Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Sale_ServiceTest {

    @InjectMocks
    private Sale_Service saleService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Sale_Entity> typedQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListarVentas() {
        Sale_Entity sale1 = new Sale_Entity();
        sale1.setFecha_venta(LocalDateTime.now());
        sale1.setCliente_id("1");
        sale1.setTotal(100.0);

        Sale_Entity sale2 = new Sale_Entity();
        sale2.setFecha_venta(LocalDateTime.now());
        sale2.setCliente_id("2");
        sale2.setTotal(200.0);

        List<Sale_Entity> sales = Arrays.asList(sale1, sale2);

        when(entityManager.createQuery(anyString(), eq(Sale_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(sales);

        List<Sale_Entity> result = saleService.getListarVentas();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getCliente_id());
    }

    @Test
    public void testInsertarVenta() {
        Sale_Entity sale = new Sale_Entity();
        sale.setFecha_venta(LocalDateTime.now());
        sale.setCliente_id("1");
        sale.setTotal(100.0);

        saleService.insertarVenta(sale);

        verify(entityManager, times(1)).persist(sale);
    }

    @Test
    public void testEliminarVenta() {
        Sale_Entity sale = new Sale_Entity();
        sale.setSale_id(1L);
        sale.setFecha_venta(LocalDateTime.now());
        sale.setCliente_id("1");
        sale.setTotal(100.0);

        when(entityManager.find(Sale_Entity.class, 1L)).thenReturn(sale);

        saleService.eliminarVenta(1L);

        verify(entityManager, times(1)).remove(sale);
    }

    @Test
    public void testActualizarVenta() {
        Sale_Entity existingSale = new Sale_Entity();
        existingSale.setSale_id(1L);
        existingSale.setFecha_venta(LocalDateTime.now());
        existingSale.setCliente_id("1");
        existingSale.setTotal(100.0);

        Sale_Entity updatedSale = new Sale_Entity();
        updatedSale.setTotal(150.0);

        when(entityManager.find(Sale_Entity.class, 1L)).thenReturn(existingSale);

        Sale_Entity result = saleService.actualizarVenta(1L, updatedSale);

        assertNotNull(result);
        assertEquals(150.0, result.getTotal());

        verify(entityManager, times(1)).merge(existingSale);
    }

    @Test
    public void testBuscarPorIdVenta() {
        Sale_Entity sale = new Sale_Entity();
        sale.setSale_id(1L);
        sale.setFecha_venta(LocalDateTime.now());
        sale.setCliente_id("1");
        sale.setTotal(100.0);

        when(entityManager.find(Sale_Entity.class, 1L)).thenReturn(sale);

        Sale_Entity result = saleService.buscarPorIdVenta(1L);

        assertNotNull(result);
        assertEquals(1L, result.getSale_id());
        assertEquals("1", result.getCliente_id());
    }

    @Test
    public void testBuscarPorVentaClienteID() {
        Sale_Entity sale = new Sale_Entity();
        sale.setSale_id(1L);
        sale.setFecha_venta(LocalDateTime.now());
        sale.setCliente_id("1");
        sale.setTotal(100.0);

        when(entityManager.createQuery(anyString(), eq(Sale_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(sale));

        Sale_Entity result = saleService.buscarPorVentaClienteID(1);

        assertNotNull(result);
        assertEquals("1", result.getCliente_id());
    }
}
