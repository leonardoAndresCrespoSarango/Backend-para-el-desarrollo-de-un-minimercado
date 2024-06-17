package ec.edu.ups.Bakend.Services;

import ec.edu.ups.Bakend.Entity.Supplier_Entity;
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

public class Supplier_ServiceTest {

    @InjectMocks
    private Supplier_Service supplierService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Supplier_Entity> typedQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListarProveedores() {
        Supplier_Entity supplier1 = new Supplier_Entity();
        supplier1.setNombre("Supplier One");
        supplier1.setContacto("Contact One");
        supplier1.setDireccion("Address One");
        supplier1.setTelefono("Phone One");

        Supplier_Entity supplier2 = new Supplier_Entity();
        supplier2.setNombre("Supplier Two");
        supplier2.setContacto("Contact Two");
        supplier2.setDireccion("Address Two");
        supplier2.setTelefono("Phone Two");

        List<Supplier_Entity> suppliers = Arrays.asList(supplier1, supplier2);

        when(entityManager.createQuery(anyString(), eq(Supplier_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(suppliers);

        List<Supplier_Entity> result = supplierService.getListarProveedores();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Supplier One", result.get(0).getNombre());
    }

    @Test
    public void testInsertarProveedor() {
        Supplier_Entity supplier = new Supplier_Entity();
        supplier.setNombre("Supplier One");
        supplier.setContacto("Contact One");
        supplier.setDireccion("Address One");
        supplier.setTelefono("Phone One");

        supplierService.insertarProveedor(supplier);

        verify(entityManager, times(1)).persist(supplier);
    }

    @Test
    public void testEliminarProveedor() {
        Supplier_Entity supplier = new Supplier_Entity();
        supplier.setNombre("Supplier One");
        supplier.setContacto("Contact One");
        supplier.setDireccion("Address One");
        supplier.setTelefono("Phone One");
        supplier.setSupplier_id(1L);

        when(entityManager.find(Supplier_Entity.class, 1L)).thenReturn(supplier);

        supplierService.eliminarProveedor(1L);

        verify(entityManager, times(1)).remove(supplier);
    }

    @Test
    public void testActualizarProveedor() {
        Supplier_Entity existingSupplier = new Supplier_Entity();
        existingSupplier.setNombre("Supplier One");
        existingSupplier.setContacto("Contact One");
        existingSupplier.setDireccion("Address One");
        existingSupplier.setTelefono("Phone One");
        existingSupplier.setSupplier_id(1L);

        Supplier_Entity updatedSupplier = new Supplier_Entity();
        updatedSupplier.setNombre("Updated Supplier");
        updatedSupplier.setContacto("Updated Contact");
        updatedSupplier.setDireccion("Updated Address");
        updatedSupplier.setTelefono("Updated Phone");

        when(entityManager.find(Supplier_Entity.class, 1L)).thenReturn(existingSupplier);

        Supplier_Entity result = supplierService.actualizarProveedor(1L, updatedSupplier);

        assertNotNull(result);
        assertEquals("Updated Supplier", result.getNombre());
        assertEquals("Updated Contact", result.getContacto());
        assertEquals("Updated Address", result.getDireccion());
        assertEquals("Updated Phone", result.getTelefono());

        verify(entityManager, times(1)).merge(existingSupplier);
    }

    @Test
    public void testBuscarPorId() {
        Supplier_Entity supplier = new Supplier_Entity();
        supplier.setNombre("Supplier One");
        supplier.setContacto("Contact One");
        supplier.setDireccion("Address One");
        supplier.setTelefono("Phone One");
        supplier.setSupplier_id(1L);

        when(entityManager.find(Supplier_Entity.class, 1L)).thenReturn(supplier);

        Supplier_Entity result = supplierService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getSupplier_id());
        assertEquals("Supplier One", result.getNombre());
    }

    @Test
    public void testBuscarPorProveedorNombre() {
        Supplier_Entity supplier = new Supplier_Entity();
        supplier.setNombre("Supplier One");
        supplier.setContacto("Contact One");
        supplier.setDireccion("Address One");
        supplier.setTelefono("Phone One");

        when(entityManager.createQuery(anyString(), eq(Supplier_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(supplier));

        Supplier_Entity result = supplierService.buscarPorProveedorNombre("Supplier One");

        assertNotNull(result);
        assertEquals("Supplier One", result.getNombre());
    }
}
