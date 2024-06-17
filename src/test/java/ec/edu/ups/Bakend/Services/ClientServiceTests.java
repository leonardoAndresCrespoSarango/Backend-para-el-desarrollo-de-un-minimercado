package ec.edu.ups.Bakend.Services;


import ec.edu.ups.Bakend.Entity.Client_Entity;
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

public class ClientServiceTests {

    @InjectMocks
    private Client_Service clientService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Client_Entity> typedQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListarClientes() {
        Client_Entity client1 = new Client_Entity();
        client1.setNombre("John");
        client1.setCedula("1234567890");
        client1.setCorreo("john@example.com");
        client1.setTelefono("123456789");

        Client_Entity client2 = new Client_Entity();
        client2.setNombre("Jane");
        client2.setCedula("0987654321");
        client2.setCorreo("jane@example.com");
        client2.setTelefono("987654321");

        List<Client_Entity> clients = Arrays.asList(client1, client2);

        when(entityManager.createQuery(anyString(), eq(Client_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(clients);

        List<Client_Entity> result = clientService.getListarClientes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNombre());
    }

    @Test
    public void testInsertarCliente() {
        Client_Entity client = new Client_Entity();
        client.setNombre("John");
        client.setCedula("1234567890");
        client.setCorreo("john@example.com");
        client.setTelefono("123456789");

        when(entityManager.createQuery(anyString(), eq(Client_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList());

        clientService.insertarCliente(client);

        verify(entityManager, times(1)).persist(client);
    }

    @Test
    public void testEliminarCliente() {
        Client_Entity client = new Client_Entity();
        client.setNombre("John");
        client.setCedula("1234567890");
        client.setCorreo("john@example.com");
        client.setTelefono("123456789");
        client.setCient_id(1L);

        when(entityManager.find(Client_Entity.class, 1L)).thenReturn(client);

        clientService.eliminarCliente(1L);

        verify(entityManager, times(1)).remove(client);
    }

    @Test
    public void testActualizarCliente() {
        Client_Entity existingClient = new Client_Entity();
        existingClient.setNombre("John");
        existingClient.setCedula("1234567890");
        existingClient.setCorreo("john@example.com");
        existingClient.setTelefono("123456789");
        existingClient.setCient_id(1L);

        Client_Entity updatedClient = new Client_Entity();
        updatedClient.setNombre("Johnny");
        updatedClient.setCedula("0987654321");
        updatedClient.setCorreo("johnny@example.com");
        updatedClient.setTelefono("987654321");

        when(entityManager.find(Client_Entity.class, 1L)).thenReturn(existingClient);

        Client_Entity result = clientService.actualizarClinte(1L, updatedClient);

        assertNotNull(result);
        assertEquals("Johnny", result.getNombre());
        assertEquals("0987654321", result.getCedula());
        assertEquals("johnny@example.com", result.getCorreo());
        assertEquals("987654321", result.getTelefono());

        verify(entityManager, times(1)).merge(existingClient);
    }

    @Test
    public void testBuscarPorId() {
        Client_Entity client = new Client_Entity();
        client.setNombre("John");
        client.setCedula("1234567890");
        client.setCorreo("john@example.com");
        client.setTelefono("123456789");
        client.setCient_id(1L);

        when(entityManager.find(Client_Entity.class, 1L)).thenReturn(client);

        Client_Entity result = clientService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getCient_id());
        assertEquals("John", result.getNombre());
    }

    @Test
    public void testBuscarPorClienteCedula() {
        Client_Entity client = new Client_Entity();
        client.setNombre("John");
        client.setCedula("1234567890");
        client.setCorreo("john@example.com");
        client.setTelefono("123456789");

        when(entityManager.createQuery(anyString(), eq(Client_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(client));

        Client_Entity result = clientService.buscarPorClienteCedula("1234567890");

        assertNotNull(result);
        assertEquals("John", result.getNombre());
    }
}
