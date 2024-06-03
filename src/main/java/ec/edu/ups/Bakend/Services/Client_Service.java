package ec.edu.ups.Bakend.Services;


import ec.edu.ups.Bakend.Entity.Client_Entity;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Client_Service {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Client_Entity> getListarClientes() {
        String jpql = "SELECT u FROM Client_Entity u";
        Query query = entityManager.createQuery(jpql, Client_Entity.class);
        return query.getResultList();
    }
    public void insertarCliente(Client_Entity cliente) {
        Client_Entity clienteExistente = buscarPorClienteCedula(cliente.getCedula());
        if (clienteExistente == null) {
            entityManager.persist(cliente);
        } else {
            throw new RuntimeException("El usuario ya existe");
        }
    }
    public void eliminarCliente(long id) {
        Client_Entity clienteExistente = entityManager.find(Client_Entity.class, id);
        if (clienteExistente != null) {
            entityManager.remove(clienteExistente);
        }
    }

    public Client_Entity actualizarClinte(long id, Client_Entity clienteActualizado) {
        Client_Entity clienteExistente = entityManager.find(Client_Entity.class, id);
        if (clienteExistente != null) {
            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setCedula(clienteActualizado.getCedula());
            clienteExistente.setCorreo(clienteActualizado.getCorreo());
            clienteExistente.setTelefono(clienteActualizado.getTelefono());
            entityManager.merge(clienteExistente);
        }
        return clienteExistente;
    }

    public Client_Entity buscarPorId(long id) {
        return entityManager.find(Client_Entity.class, id);
    }

    public Client_Entity buscarPorClienteCedula(String cedula) {
        String jpql = "SELECT u FROM Client_Entity u WHERE u.cedula = :cedula";
        Query query = entityManager.createQuery(jpql, Client_Entity.class);
        query.setParameter("cedula", cedula);
        List<Client_Entity> cliente = query.getResultList();
        if (!cliente.isEmpty()) {
            return cliente.get(0);
        }
        return null;
    }




}
