package ec.edu.ups.Bakend.Controller;


import ec.edu.ups.Bakend.Entity.Stock_Entity;
import ec.edu.ups.Bakend.Services.Stock_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "http://localhost:4200")
public class Stock_Controller {
    private Stock_Service stockService;
    @Autowired
    public Stock_Controller(Stock_Service stockService) {
        this.stockService = stockService;
    }
    @GetMapping("/list_stock")
    public List<Stock_Entity> obtenerTodosLosItems() {
        return stockService.getListarStock();
    }
    @PostMapping("/create_stock")
    public ResponseEntity<String> crearStock(@RequestBody Stock_Entity stock) {
        try {
            stockService.insertarStock(stock);
            return ResponseEntity.ok("stock creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el stock: " + e.getMessage());
        }
    }
    @PutMapping("/update_stock/{id}")
    public ResponseEntity<String> actualizarStock(@PathVariable("id") long id, @RequestBody Stock_Entity stockActualizado) {
        Stock_Entity stockActualizadoResultado = stockService.actualizarStock(id, stockActualizado);
        if (stockActualizadoResultado != null) {
            return ResponseEntity.ok("stock actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete_stock_by_ID/{id}")
    public ResponseEntity<String> eliminarPeromocion(@PathVariable("id") long id) {
        stockService.eliminarStock(id);
        return ResponseEntity.ok("stock eliminado exitosamente");
    }

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
