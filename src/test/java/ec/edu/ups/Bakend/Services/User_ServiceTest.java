package ec.edu.ups.Bakend.Services;

import ec.edu.ups.Bakend.Entity.User_Entity;
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

public class User_ServiceTest {

    @InjectMocks
    private User_Service userService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<User_Entity> typedQuery;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetListarUsuario() {
        User_Entity user1 = new User_Entity("John", "john@example.com", "password", "admin");
        User_Entity user2 = new User_Entity("Jane", "jane@example.com", "password", "user");
        List<User_Entity> users = Arrays.asList(user1, user2);

        when(entityManager.createQuery(anyString(), eq(User_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(users);

        List<User_Entity> result = userService.getListarUsuario();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNombre());
    }

    @Test
    public void testInsertarUsuario() {
        User_Entity user = new User_Entity("John", "john@example.com", "password", "admin");

        when(entityManager.createQuery(anyString(), eq(User_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList());

        userService.insertarUsuario(user);

        verify(entityManager, times(1)).persist(user);
    }

    @Test
    public void testEliminarUsuario() {
        User_Entity user = new User_Entity("John", "john@example.com", "password", "admin");
        user.setUser_id(1L);

        when(entityManager.find(User_Entity.class, 1L)).thenReturn(user);

        userService.eliminarUsuario(1L);

        verify(entityManager, times(1)).remove(user);
    }

    @Test
    public void testActualizarUsuario() {
        User_Entity existingUser = new User_Entity("John", "john@example.com", "password", "admin");
        existingUser.setUser_id(1L);
        User_Entity updatedUser = new User_Entity("Johnny", "johnny@example.com", "newpassword", "admin");

        when(entityManager.find(User_Entity.class, 1L)).thenReturn(existingUser);

        User_Entity result = userService.actualizarUsuario(1L, updatedUser);

        assertNotNull(result);
        assertEquals("Johnny", result.getNombre());
        assertEquals("johnny@example.com", result.getCorreo());
        assertEquals("newpassword", result.getContrasenia());

        verify(entityManager, times(1)).merge(existingUser);
    }

    @Test
    public void testBuscarPorId() {
        User_Entity user = new User_Entity("John", "john@example.com", "password", "admin");
        user.setUser_id(1L);

        when(entityManager.find(User_Entity.class, 1L)).thenReturn(user);

        User_Entity result = userService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUser_id());
        assertEquals("John", result.getNombre());
    }

    @Test
    public void testBuscarPorUsuarioNombre() {
        User_Entity user = new User_Entity("John", "john@example.com", "password", "admin");

        when(entityManager.createQuery(anyString(), eq(User_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(user));

        User_Entity result = userService.buscarPorUsuarioNombre("John");

        assertNotNull(result);
        assertEquals("John", result.getNombre());
    }

    @Test
    public void testBuscarPorUsuarioYContrasenia() {
        User_Entity user = new User_Entity("John", "john@example.com", "password", "admin");

        when(entityManager.createQuery(anyString(), eq(User_Entity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(user));

        User_Entity result = userService.buscarPorUsuarioYContrasenia("John", "password");

        assertNotNull(result);
        assertEquals("John", result.getNombre());
    }
}
