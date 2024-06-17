package ec.edu.ups.Bakend.Services;

import ec.edu.ups.Bakend.Entity.Order_Entity;
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

public class Order_ServiceTest {

    @InjectMocks
    private Order_Service orderService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Order_Entity> typedQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListarPedidos() {
        Order_Entity pedido1 = new Order_Entity();
        pedido1.setProveedorid(1L);
        pedido1.setFechaPedido(LocalDateTime.now());
        pedido1.setFechaEntrega(LocalDateTime.now().plusDays(7));

        Order_Entity pedido2 = new Order_Entity();
        pedido2.setProveedorid(2L);
        pedido2.setFechaPedido(LocalDateTime.now());
        pedido2.setFechaEntrega(LocalDateTime.now().plusDays(5));

        List<Order_Entity> pedidos = Arrays.asList(pedido1, pedido2);

        when(entityManager.createQuery(anyString(), eq(Order_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(pedidos);

        List<Order_Entity> result = orderService.getListarPedidos();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getProveedorid());
    }

    @Test
    public void testInsertarPedido() {
        Order_Entity pedido = new Order_Entity();
        pedido.setProveedorid(1L);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setFechaEntrega(LocalDateTime.now().plusDays(7));

        orderService.insertarPedido(pedido);

        verify(entityManager, times(1)).persist(pedido);
    }

    @Test
    public void testEliminarPedido() {
        Order_Entity pedido = new Order_Entity();
        pedido.setOrder_id(1L);
        pedido.setProveedorid(1L);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setFechaEntrega(LocalDateTime.now().plusDays(7));

        when(entityManager.find(Order_Entity.class, 1L)).thenReturn(pedido);

        orderService.eliminarPedido(1L);

        verify(entityManager, times(1)).remove(pedido);
    }

    @Test
    public void testActualizarPedido() {
        Order_Entity existingPedido = new Order_Entity();
        existingPedido.setOrder_id(1L);
        existingPedido.setProveedorid(1L);
        existingPedido.setFechaPedido(LocalDateTime.now());
        existingPedido.setFechaEntrega(LocalDateTime.now().plusDays(7));

        Order_Entity updatedPedido = new Order_Entity();
        updatedPedido.setFechaPedido(LocalDateTime.now());
        updatedPedido.setFechaEntrega(LocalDateTime.now().plusDays(5));

        when(entityManager.find(Order_Entity.class, 1L)).thenReturn(existingPedido);

        Order_Entity result = orderService.actualizarPedido(1L, updatedPedido);

        assertNotNull(result);
        assertEquals(updatedPedido.getFechaPedido(), result.getFechaPedido());
        assertEquals(updatedPedido.getFechaEntrega(), result.getFechaEntrega());

        verify(entityManager, times(1)).merge(existingPedido);
    }

    @Test
    public void testBuscarPorId() {
        Order_Entity pedido = new Order_Entity();
        pedido.setOrder_id(1L);
        pedido.setProveedorid(1L);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setFechaEntrega(LocalDateTime.now().plusDays(7));

        when(entityManager.find(Order_Entity.class, 1L)).thenReturn(pedido);

        Order_Entity result = orderService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getOrder_id());
        assertEquals(1L, result.getProveedorid());
    }

    @Test
    public void testBuscarPorPedido_ProveedorID() {
        Order_Entity pedido = new Order_Entity();
        pedido.setOrder_id(1L);
        pedido.setProveedorid(1L);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setFechaEntrega(LocalDateTime.now().plusDays(7));

        when(entityManager.createQuery(anyString(), eq(Order_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(pedido));

        Order_Entity result = orderService.buscarPorPedido_ProveedorID(1L);

        assertNotNull(result);
        assertEquals(1L, result.getProveedorid());
    }
}
