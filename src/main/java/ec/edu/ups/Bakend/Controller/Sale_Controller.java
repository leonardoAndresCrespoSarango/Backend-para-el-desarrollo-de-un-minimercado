package ec.edu.ups.Bakend.Controller;

import ec.edu.ups.Bakend.Entity.Sale_Entity;
import ec.edu.ups.Bakend.Services.Sale_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Sale", description = "Operaciones relacionadas con la gestión de ventas")
public class Sale_Controller {

    private Sale_Service saleService;

    @Autowired
    public Sale_Controller(Sale_Service saleService) {
        this.saleService = saleService;
    }

    @Operation(summary = "Listar todas las ventas", description = "Obtiene una lista de todas las ventas")
    @GetMapping("/list_sales")
    public List<Sale_Entity> obtenerTodosLosItems() {
        return saleService.getListarVentas();
    }

    @Operation(summary = "Crear una nueva venta", description = "Crea una nueva venta con los datos proporcionados")
    @PostMapping("/create_sale")
    public ResponseEntity<String> crearVenta(@RequestBody Sale_Entity venta) {
        try {
            saleService.insertarVenta(venta);
            return ResponseEntity.ok("Venta creada exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear la venta: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar una venta existente", description = "Actualiza los datos de una venta existente según su ID")
    @PutMapping("/update_sale/{id}")
    public ResponseEntity<String> actualizarVenta(@PathVariable("id") long id, @RequestBody Sale_Entity ventaActualizado) {
        Sale_Entity ventaActualizadoResultado = saleService.actualizarVenta(id, ventaActualizado);
        if (ventaActualizadoResultado != null) {
            return ResponseEntity.ok("Venta actualizada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una venta", description = "Elimina una venta según su ID")
    @DeleteMapping("/delete_sale_by_ID/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable("id") long id) {
        saleService.eliminarVenta(id);
        return ResponseEntity.ok("Venta eliminada exitosamente");
    }

    @Operation(summary = "Buscar venta por ID", description = "Busca una venta según su ID")
    @GetMapping("/search_sale_by_ID/{id}")
    public ResponseEntity<Sale_Entity> buscarPorId(@PathVariable("id") long id) {
        Sale_Entity ventaEncontrado = saleService.buscarPorIdVenta(id);
        if (ventaEncontrado != null) {
            return ResponseEntity.ok(ventaEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar venta por ID de cliente", description = "Busca una venta según el ID del cliente")
    @GetMapping("/search_sale_by_clientID/{id}")
    public ResponseEntity<Sale_Entity> buscarPorClienteId(@PathVariable("id") long id) {
        Sale_Entity ventaEncontrado = saleService.buscarPorVentaClienteID(id);
        if (ventaEncontrado != null) {
            return ResponseEntity.ok(ventaEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search_sale_by_numeroV/{numeroventa}")
    public ResponseEntity<Sale_Entity> buscarPorNumeroVenta(@PathVariable("numeroventa") String numero_venta) {
        Sale_Entity ventaEncontrado = saleService.buscarPorVentaNumeroVenta(numero_venta);
        if (ventaEncontrado != null) {
            return ResponseEntity.ok(ventaEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
