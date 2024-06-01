package ec.edu.ups.Bakend.Controller;


import ec.edu.ups.Bakend.Entity.Client_Entity;
import ec.edu.ups.Bakend.Services.Client_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:4200")
public class Client_Controller {
    private Client_Service clientService;
    @Autowired
    public Client_Controller(Client_Service clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/list_clients")
    public List<Client_Entity> obtenerTodosLosItems() {
        return clientService.getListarClientes();
    }
    @PostMapping("/create_client")
    public ResponseEntity<String> crearCliente(@RequestBody Client_Entity cliente) {
        try {
            clientService.insertarCliente(cliente);
            return ResponseEntity.ok("cliente creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el cliente: " + e.getMessage());
        }
    }
    @PutMapping("/update_client/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable("id") long id, @RequestBody Client_Entity clienteActualizado) {
        Client_Entity clienteActualizadoResultado = clientService.actualizarClinte(id, clienteActualizado);
        if (clienteActualizadoResultado != null) {
            return ResponseEntity.ok("cliente actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete_client_by_ID/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") long id) {
        clientService.eliminarCliente(id);
        return ResponseEntity.ok("cliente eliminado exitosamente");
    }

    @GetMapping("/search_client_by_ID/{id}")
    public ResponseEntity<Client_Entity> buscarPorId(@PathVariable("id") long id) {
        Client_Entity clienteEncontrado = clientService.buscarPorId(id);
        if (clienteEncontrado != null) {
            return ResponseEntity.ok(clienteEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/search_client_by_personalID/{cedula}")
    public ResponseEntity<Client_Entity> buscarUsu(@PathVariable("cedula") String cedula) {
        Client_Entity usuarioEncontrado = clientService.buscarPorClienteCedula(cedula);
        if (usuarioEncontrado != null) {
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
