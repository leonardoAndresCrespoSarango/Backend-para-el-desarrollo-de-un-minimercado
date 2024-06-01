package ec.edu.ups.Bakend.Controller;


import ec.edu.ups.Bakend.Entity.Sale_Entity;
import ec.edu.ups.Bakend.Services.Sale_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
@CrossOrigin(origins = "http://localhost:4200")
public class Sale_Controller {
    private Sale_Service saleService;
    @Autowired
    public Sale_Controller(Sale_Service saleService) {
        this.saleService = saleService;
    }
    @GetMapping("/list_sales")
    public List<Sale_Entity> obtenerTodosLosItems() {
        return saleService.getListarVentas();
    }
    @PostMapping("/create_sale")
    public ResponseEntity<String> crearVenta(@RequestBody Sale_Entity venta) {
        try {
            saleService.insertarVenta(venta);
            return ResponseEntity.ok("venta creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear la venta: " + e.getMessage());
        }
    }
    @PutMapping("/update_sale/{id}")
    public ResponseEntity<String> actualizarVenta(@PathVariable("id") long id, @RequestBody Sale_Entity ventaActualizado) {
        Sale_Entity ventaActualizadoResultado = saleService.actualizarVenta(id, ventaActualizado);
        if (ventaActualizadoResultado != null) {
            return ResponseEntity.ok("venta actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete_sale_by_ID/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable("id") long id) {
        saleService.eliminarVenta(id);
        return ResponseEntity.ok("venta eliminado exitosamente");
    }

    @GetMapping("/search_sale_by_ID/{id}")
    public ResponseEntity<Sale_Entity> buscarPorId(@PathVariable("id") long id) {
        Sale_Entity ventaEncontrado = saleService.buscarPorIdVenta(id);
        if (ventaEncontrado != null) {
            return ResponseEntity.ok(ventaEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/search_sale_by_clientID/{id}")
    public ResponseEntity<Sale_Entity> buscarUsu(@PathVariable("id") long id) {
        Sale_Entity ventaEncontrado = saleService.buscarPorVentaClienteID(id);
        if (ventaEncontrado != null) {
            return ResponseEntity.ok(ventaEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
