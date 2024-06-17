package ec.edu.ups.Bakend.Services;

import ec.edu.ups.Bakend.Entity.Product_Entity;
import ec.edu.ups.Bakend.Entity.Sale_Detail_Entity;
import ec.edu.ups.Bakend.Entity.Sale_Entity;
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

public class Sale_Detail_ServiceTest {

    @InjectMocks
    private Sale_Detal_Service saleDetailService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Sale_Detail_Entity> typedQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListarVentasDetalle() {
        Sale_Detail_Entity detalle1 = new Sale_Detail_Entity();
        detalle1.setSale_id(1L);
        detalle1.setProduct_id(1L);
        detalle1.setCantidad(5);
        detalle1.setPrecio(100.0);

        Sale_Detail_Entity detalle2 = new Sale_Detail_Entity();
        detalle2.setSale_id(1L);
        detalle2.setProduct_id(2L);
        detalle2.setCantidad(3);
        detalle2.setPrecio(50.0);

        List<Sale_Detail_Entity> detalles = Arrays.asList(detalle1, detalle2);

        when(entityManager.createQuery(anyString(), eq(Sale_Detail_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(detalles);

        List<Sale_Detail_Entity> result = saleDetailService.getListarVentasDetalle();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getSale_id());
    }

    @Test
    public void testInsertarVentaDetalle() {
        Sale_Detail_Entity detalle = new Sale_Detail_Entity();
        detalle.setSale_id(1L);
        detalle.setProduct_id(1L);
        detalle.setCantidad(5);
        detalle.setPrecio(100.0);

        saleDetailService.insertarVentaDetalle(detalle);

        verify(entityManager, times(1)).persist(detalle);
    }

    @Test
    public void testEliminarVentaDetalle() {
        Sale_Detail_Entity detalle = new Sale_Detail_Entity();
        detalle.setSaleDet_id(1L);
        detalle.setSale_id(1L);
        detalle.setProduct_id(1L);
        detalle.setCantidad(5);
        detalle.setPrecio(100.0);

        when(entityManager.find(Sale_Detail_Entity.class, 1L)).thenReturn(detalle);

        saleDetailService.eliminarVentaDetalle(1L);

        verify(entityManager, times(1)).remove(detalle);
    }

    @Test
    public void testActualizarVentaDetalle() {
        Sale_Detail_Entity existingDetalle = new Sale_Detail_Entity();
        existingDetalle.setSaleDet_id(1L);
        existingDetalle.setSale_id(1L);
        existingDetalle.setProduct_id(1L);
        existingDetalle.setCantidad(5);
        existingDetalle.setPrecio(100.0);

        Sale_Detail_Entity updatedDetalle = new Sale_Detail_Entity();
        updatedDetalle.setCantidad(10);
        updatedDetalle.setPrecio(150.0);

        when(entityManager.find(Sale_Detail_Entity.class, 1L)).thenReturn(existingDetalle);

        Sale_Detail_Entity result = saleDetailService.actualizarVentaDetalle(1L, updatedDetalle);

        assertNotNull(result);
        assertEquals(10, result.getCantidad());
        assertEquals(150.0, result.getPrecio());

        verify(entityManager, times(1)).merge(existingDetalle);
    }

    @Test
    public void testBuscarPorIdVentaDetalle() {
        Sale_Detail_Entity detalle = new Sale_Detail_Entity();
        detalle.setSaleDet_id(1L);
        detalle.setSale_id(1L);
        detalle.setProduct_id(1L);
        detalle.setCantidad(5);
        detalle.setPrecio(100.0);

        when(entityManager.find(Sale_Detail_Entity.class, 1L)).thenReturn(detalle);

        Sale_Detail_Entity result = saleDetailService.buscarPorIdVentaDetalle(1L);

        assertNotNull(result);
        assertEquals(1L, result.getSaleDet_id());
        assertEquals(1L, result.getSale_id());
    }

    @Test
    public void testBuscarPorVentaDetalle_VentaID() {
        Sale_Detail_Entity detalle = new Sale_Detail_Entity();
        detalle.setSaleDet_id(1L);
        detalle.setSale_id(1L);
        detalle.setProduct_id(1L);
        detalle.setCantidad(5);
        detalle.setPrecio(100.0);

        when(entityManager.createQuery(anyString(), eq(Sale_Detail_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(detalle));

        Sale_Detail_Entity result = saleDetailService.buscarPorVentaDetalle_VentaID(1L);

        assertNotNull(result);
        assertEquals(1L, result.getSale_id());
    }
}