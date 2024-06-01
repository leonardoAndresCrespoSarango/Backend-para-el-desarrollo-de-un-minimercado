package ec.edu.ups.Bakend.Controller;


import ec.edu.ups.Bakend.Entity.Sale_Detail_Entity;
import ec.edu.ups.Bakend.Services.Sale_Detal_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale-detail")
@CrossOrigin(origins = "http://localhost:4200")
public class Sale_Detail_Controller {
    private Sale_Detal_Service saleDetService;
    @Autowired
    public Sale_Detail_Controller(Sale_Detal_Service saleDetService) {
        this.saleDetService = saleDetService;
    }
    @GetMapping("/list_salesDetail")
    public List<Sale_Detail_Entity> obtenerTodosLosItems() {
        return saleDetService.getListarVentasDetalle();
    }
    @PostMapping("/create_saleDetail")
    public ResponseEntity<String> crearVentaDetalle(@RequestBody Sale_Detail_Entity venta) {
        try {
            saleDetService.insertarVentaDetalle(venta);
            return ResponseEntity.ok("venta detalle creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear la venta detalle: " + e.getMessage());
        }
    }
    @PutMapping("/update_saleDetail/{id}")
    public ResponseEntity<String> actualizarVentaDetalle(@PathVariable("id") long id, @RequestBody Sale_Detail_Entity ventaDetailleActualizado) {
        Sale_Detail_Entity ventaDetalleActualizadoResultado = saleDetService.actualizarVentaDetalle(id, ventaDetailleActualizado);
        if (ventaDetalleActualizadoResultado != null) {
            return ResponseEntity.ok("venta detalle actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete_saleDetail_by_ID/{id}")
    public ResponseEntity<String> eliminarVentaDetalle(@PathVariable("id") long id) {
        saleDetService.eliminarVentaDetalle(id);
        return ResponseEntity.ok("venta detalle eliminado exitosamente");
    }

    @GetMapping("/search_saleDetail_by_ID/{id}")
    public ResponseEntity<Sale_Detail_Entity> buscarPorId(@PathVariable("id") long id) {
        Sale_Detail_Entity ventaDetalleEncontrado = saleDetService.buscarPorIdVentaDetalle(id);
        if (ventaDetalleEncontrado != null) {
            return ResponseEntity.ok(ventaDetalleEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/search_saleDetail_by_saleID/{id}")
    public ResponseEntity<Sale_Detail_Entity> buscarUsu(@PathVariable("id") long id) {
        Sale_Detail_Entity ventaDetalleEncontrado = saleDetService.buscarPorVentaDetalle_VentaID(id);
        if (ventaDetalleEncontrado != null) {
            return ResponseEntity.ok(ventaDetalleEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
