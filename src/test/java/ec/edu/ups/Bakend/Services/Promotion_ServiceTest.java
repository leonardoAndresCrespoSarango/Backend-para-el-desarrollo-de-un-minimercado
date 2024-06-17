package ec.edu.ups.Bakend.Services;

import ec.edu.ups.Bakend.Entity.Promotion_Entity;
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

public class Promotion_ServiceTest {

    @InjectMocks
    private Promotion_Service promotionService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Promotion_Entity> typedQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListarPromociones() {
        Promotion_Entity promocion1 = new Promotion_Entity();
        promocion1.setNombre("Promo1");
        promocion1.setDescripcion("Descripción de Promo1");
        promocion1.setFechaInicio(LocalDateTime.now());
        promocion1.setFechaFin(LocalDateTime.now().plusDays(7));

        Promotion_Entity promocion2 = new Promotion_Entity();
        promocion2.setNombre("Promo2");
        promocion2.setDescripcion("Descripción de Promo2");
        promocion2.setFechaInicio(LocalDateTime.now());
        promocion2.setFechaFin(LocalDateTime.now().plusDays(10));

        List<Promotion_Entity> promociones = Arrays.asList(promocion1, promocion2);

        when(entityManager.createQuery(anyString(), eq(Promotion_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(promociones);

        List<Promotion_Entity> result = promotionService.getListarPromociones();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Promo1", result.get(0).getNombre());
    }

    @Test
    public void testInsertarPromocion() {
        Promotion_Entity promocion = new Promotion_Entity();
        promocion.setNombre("Promo Test");
        promocion.setDescripcion("Descripción de Promo Test");
        promocion.setFechaInicio(LocalDateTime.now());
        promocion.setFechaFin(LocalDateTime.now().plusDays(5));

        promotionService.insertarPromocion(promocion);

        verify(entityManager, times(1)).persist(promocion);
    }

    @Test
    public void testEliminarPromocion() {
        Promotion_Entity promocion = new Promotion_Entity();
        promocion.setPromotion_id(1L);
        promocion.setNombre("Promo Test");
        promocion.setDescripcion("Descripción de Promo Test");
        promocion.setFechaInicio(LocalDateTime.now());
        promocion.setFechaFin(LocalDateTime.now().plusDays(5));

        when(entityManager.find(Promotion_Entity.class, 1L)).thenReturn(promocion);

        promotionService.eliminarPromocion(1L);

        verify(entityManager, times(1)).remove(promocion);
    }

    @Test
    public void testActualizarPromocion() {
        Promotion_Entity existingPromocion = new Promotion_Entity();
        existingPromocion.setPromotion_id(1L);
        existingPromocion.setNombre("Promo Test");
        existingPromocion.setDescripcion("Descripción de Promo Test");
        existingPromocion.setFechaInicio(LocalDateTime.now());
        existingPromocion.setFechaFin(LocalDateTime.now().plusDays(5));

        Promotion_Entity updatedPromocion = new Promotion_Entity();
        updatedPromocion.setNombre("Promo Test Modificada");
        updatedPromocion.setDescripcion("Descripción Modificada");
        updatedPromocion.setFechaInicio(existingPromocion.getFechaInicio());
        updatedPromocion.setFechaFin(existingPromocion.getFechaFin());

        when(entityManager.find(Promotion_Entity.class, 1L)).thenReturn(existingPromocion);

        Promotion_Entity result = promotionService.actualizarPromocion(1L, updatedPromocion);

        assertNotNull(result);
        assertEquals("Promo Test Modificada", result.getNombre());

        verify(entityManager, times(1)).merge(existingPromocion);
    }

    @Test
    public void testBuscarPorId() {
        Promotion_Entity promocion = new Promotion_Entity();
        promocion.setPromotion_id(1L);
        promocion.setNombre("Promo Test");
        promocion.setDescripcion("Descripción de Promo Test");
        promocion.setFechaInicio(LocalDateTime.now());
        promocion.setFechaFin(LocalDateTime.now().plusDays(5));

        when(entityManager.find(Promotion_Entity.class, 1L)).thenReturn(promocion);

        Promotion_Entity result = promotionService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("Promo Test", result.getNombre());
    }

    @Test
    public void testBuscarPorPromocionNombre() {
        Promotion_Entity promocion = new Promotion_Entity();
        promocion.setPromotion_id(1L);
        promocion.setNombre("Promo Test");
        promocion.setDescripcion("Descripción de Promo Test");
        promocion.setFechaInicio(LocalDateTime.now());
        promocion.setFechaFin(LocalDateTime.now().plusDays(5));

        when(entityManager.createQuery(anyString(), eq(Promotion_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(promocion));

        Promotion_Entity result = promotionService.buscarPorPromocionNombre("Promo Test");

        assertNotNull(result);
        assertEquals("Promo Test", result.getNombre());
    }
}
