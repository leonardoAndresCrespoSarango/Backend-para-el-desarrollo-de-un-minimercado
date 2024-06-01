package ec.edu.ups.Bakend.Controller;


import ec.edu.ups.Bakend.Entity.Order_Entity;
import ec.edu.ups.Bakend.Services.Order_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class Order_Controller {
    private Order_Service orderService;
    @Autowired
    public Order_Controller(Order_Service orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/list_orders")
    public List<Order_Entity> obtenerTodosLosItems() {
        return orderService.getListarPedidos();
    }
    @PostMapping("/create_order")
    public ResponseEntity<String> crearPedido(@RequestBody Order_Entity pedido) {
        try {
            orderService.insertarPedido(pedido);
            return ResponseEntity.ok("pedido creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el pedido: " + e.getMessage());
        }
    }
    @PutMapping("/update_order/{id}")
    public ResponseEntity<String> actualizarPedido(@PathVariable("id") long id, @RequestBody Order_Entity pedidoActualizado) {
        Order_Entity pedidoActualizadoResultado = orderService.actualizarPedido(id, pedidoActualizado);
        if (pedidoActualizadoResultado != null) {
            return ResponseEntity.ok("pedido actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete_order_by_ID/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable("id") long id) {
        orderService.eliminarPedido(id);
        return ResponseEntity.ok("pedido eliminado exitosamente");
    }

    @GetMapping("/search_order_by_ID/{id}")
    public ResponseEntity<Order_Entity> buscarPorId(@PathVariable("id") long id) {
        Order_Entity pedidoEncontrado = orderService.buscarPorId(id);
        if (pedidoEncontrado != null) {
            return ResponseEntity.ok(pedidoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/search_order_by_supplierID/{id}")
    public ResponseEntity<Order_Entity> buscarUsu(@PathVariable("id") long id) {
        Order_Entity pedidoEncontrado = orderService.buscarPorPedido_ProveedorID(id);
        if (pedidoEncontrado != null) {
            return ResponseEntity.ok(pedidoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
