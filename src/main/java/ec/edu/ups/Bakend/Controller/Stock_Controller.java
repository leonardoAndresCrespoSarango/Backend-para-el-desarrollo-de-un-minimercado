package ec.edu.ups.Bakend.Controller;

import ec.edu.ups.Bakend.Entity.Stock_Entity;
import ec.edu.ups.Bakend.Services.Stock_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Stock", description = "Operaciones relacionadas con la gestión del stock")
public class Stock_Controller {

    private Stock_Service stockService;

    @Autowired
    public Stock_Controller(Stock_Service stockService) {
        this.stockService = stockService;
    }

    @Operation(summary = "Listar todo el stock", description = "Obtiene una lista de todos los elementos en el stock")
    @GetMapping("/list_stock")
    public List<Stock_Entity> obtenerTodosLosItems() {
        return stockService.getListarStock();
    }

    @Operation(summary = "Crear un nuevo elemento de stock", description = "Crea un nuevo elemento de stock con los datos proporcionados")
    @PostMapping("/create_stock")
    public ResponseEntity<String> crearStock(@RequestBody Stock_Entity stock) {
        try {
            stockService.insertarStock(stock);
            return ResponseEntity.ok("Stock creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el stock: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un elemento de stock existente", description = "Actualiza los datos de un elemento de stock existente según su ID")
    @PutMapping("/update_stock/{id}")
    public ResponseEntity<String> actualizarStock(@PathVariable("id") long id, @RequestBody Stock_Entity stockActualizado) {
        Stock_Entity stockActualizadoResultado = stockService.actualizarStock(id, stockActualizado);
        if (stockActualizadoResultado != null) {
            return ResponseEntity.ok("Stock actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un elemento de stock", description = "Elimina un elemento de stock según su ID")
    @DeleteMapping("/delete_stock_by_ID/{id}")
    public ResponseEntity<String> eliminarStock(@PathVariable("id") long id) {
        stockService.eliminarStock(id);
        return ResponseEntity.ok("Stock eliminado exitosamente");
    }

    @Operation(summary = "Buscar elemento de stock por ID", description = "Busca un elemento de stock según su ID")
    @GetMapping("/search_stock_by_ID/{id}")
    public ResponseEntity<Stock_Entity> buscarPorId(@PathVariable("id") long id) {
        Stock_Entity stockEncontrado = stockService.buscarPorId(id);
        if (stockEncontrado != null) {
            return ResponseEntity.ok(stockEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
