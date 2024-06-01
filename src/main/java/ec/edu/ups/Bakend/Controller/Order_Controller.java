package ec.edu.ups.Bakend.Controller;

import ec.edu.ups.Bakend.Entity.Order_Entity;
import ec.edu.ups.Bakend.Services.Order_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Pedido", description = "Operaciones relacionadas con la gestión de pedidos")
public class Order_Controller {
    private Order_Service orderService;

    @Autowired
    public Order_Controller(Order_Service orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Listar todos los pedidos", description = "Obtiene una lista de todos los pedidos")
    @GetMapping("/list_orders")
    public List<Order_Entity> obtenerTodosLosItems() {
        return orderService.getListarPedidos();
    }

    @Operation(summary = "Crear un nuevo pedido", description = "Crea un nuevo pedido con los datos proporcionados")
    @PostMapping("/create_order")
    public ResponseEntity<String> crearPedido(@RequestBody Order_Entity pedido) {
        try {
            orderService.insertarPedido(pedido);
            return ResponseEntity.ok("Pedido creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el pedido: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un pedido existente", description = "Actualiza los datos de un pedido existente según su ID")
    @PutMapping("/update_order/{id}")
    public ResponseEntity<String> actualizarPedido(@PathVariable("id") long id, @RequestBody Order_Entity pedidoActualizado) {
        Order_Entity pedidoActualizadoResultado = orderService.actualizarPedido(id, pedidoActualizado);
        if (pedidoActualizadoResultado != null) {
            return ResponseEntity.ok("Pedido actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un pedido", description = "Elimina un pedido según su ID")
    @DeleteMapping("/delete_order_by_ID/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable("id") long id) {
        orderService.eliminarPedido(id);
        return ResponseEntity.ok("Pedido eliminado exitosamente");
    }

    @Operation(summary = "Buscar pedido por ID", description = "Busca un pedido según su ID")
    @GetMapping("/search_order_by_ID/{id}")
    public ResponseEntity<Order_Entity> buscarPorId(@PathVariable("id") long id) {
        Order_Entity pedidoEncontrado = orderService.buscarPorId(id);
        if (pedidoEncontrado != null) {
            return ResponseEntity.ok(pedidoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar pedido por ID de proveedor", description = "Busca un pedido según el ID de su proveedor")
    @GetMapping("/search_order_by_supplierID/{id}")
    public ResponseEntity<Order_Entity> buscarPorIdProveedor(@PathVariable("id") long id) {
        Order_Entity pedidoEncontrado = orderService.buscarPorPedido_ProveedorID(id);
        if (pedidoEncontrado != null) {
            return ResponseEntity.ok(pedidoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
