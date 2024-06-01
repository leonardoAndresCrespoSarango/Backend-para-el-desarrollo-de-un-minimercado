package ec.edu.ups.Bakend.Controller;

import ec.edu.ups.Bakend.Entity.Client_Entity;
import ec.edu.ups.Bakend.Services.Client_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Cliente", description = "Operaciones relacionadas con la gestión de clientes")
public class Client_Controller {
    private Client_Service clientService;

    @Autowired
    public Client_Controller(Client_Service clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Listar todos los clientes", description = "Obtiene una lista de todos los clientes")
    @GetMapping("/list_clients")
    public List<Client_Entity> obtenerTodosLosItems() {
        return clientService.getListarClientes();
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente con los datos proporcionados")
    @PostMapping("/create_client")
    public ResponseEntity<String> crearCliente(@RequestBody Client_Entity cliente) {
        try {
            clientService.insertarCliente(cliente);
            return ResponseEntity.ok("Cliente creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el cliente: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un cliente existente", description = "Actualiza los datos de un cliente existente según su ID")
    @PutMapping("/update_client/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable("id") long id, @RequestBody Client_Entity clienteActualizado) {
        Client_Entity clienteActualizadoResultado = clientService.actualizarClinte(id, clienteActualizado);
        if (clienteActualizadoResultado != null) {
            return ResponseEntity.ok("Cliente actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente según su ID")
    @DeleteMapping("/delete_client_by_ID/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable("id") long id) {
        clientService.eliminarCliente(id);
        return ResponseEntity.ok("Cliente eliminado exitosamente");
    }

    @Operation(summary = "Buscar cliente por ID", description = "Busca un cliente según su ID")
    @GetMapping("/search_client_by_ID/{id}")
    public ResponseEntity<Client_Entity> buscarPorId(@PathVariable("id") long id) {
        Client_Entity clienteEncontrado = clientService.buscarPorId(id);
        if (clienteEncontrado != null) {
            return ResponseEntity.ok(clienteEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar cliente por número de cédula", description = "Busca un cliente según su número de cédula")
    @GetMapping("/search_client_by_personalID/{cedula}")
    public ResponseEntity<Client_Entity> buscarPorCedula(@PathVariable("cedula") String cedula) {
        Client_Entity clienteEncontrado = clientService.buscarPorClienteCedula(cedula);
        if (clienteEncontrado != null) {
            return ResponseEntity.ok(clienteEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
