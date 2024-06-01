package ec.edu.ups.Bakend.Controller;

import ec.edu.ups.Bakend.Entity.Sale_Detail_Entity;
import ec.edu.ups.Bakend.Services.Sale_Detal_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale-detail")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Sale Detail", description = "Operaciones relacionadas con la gestión de los detalles de ventas")
public class Sale_Detail_Controller {

    private Sale_Detal_Service saleDetService;

    @Autowired
    public Sale_Detail_Controller(Sale_Detal_Service saleDetService) {
        this.saleDetService = saleDetService;
    }

    @Operation(summary = "Listar todos los detalles de ventas", description = "Obtiene una lista de todos los detalles de ventas")
    @GetMapping("/list_salesDetail")
    public List<Sale_Detail_Entity> obtenerTodosLosItems() {
        return saleDetService.getListarVentasDetalle();
    }

    @Operation(summary = "Crear un nuevo detalle de venta", description = "Crea un nuevo detalle de venta con los datos proporcionados")
    @PostMapping("/create_saleDetail")
    public ResponseEntity<String> crearVentaDetalle(@RequestBody Sale_Detail_Entity venta) {
        try {
            saleDetService.insertarVentaDetalle(venta);
            return ResponseEntity.ok("Detalle de venta creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el detalle de venta: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un detalle de venta existente", description = "Actualiza los datos de un detalle de venta existente según su ID")
    @PutMapping("/update_saleDetail/{id}")
    public ResponseEntity<String> actualizarVentaDetalle(@PathVariable("id") long id, @RequestBody Sale_Detail_Entity ventaDetailleActualizado) {
        Sale_Detail_Entity ventaDetalleActualizadoResultado = saleDetService.actualizarVentaDetalle(id, ventaDetailleActualizado);
        if (ventaDetalleActualizadoResultado != null) {
            return ResponseEntity.ok("Detalle de venta actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un detalle de venta", description = "Elimina un detalle de venta según su ID")
    @DeleteMapping("/delete_saleDetail_by_ID/{id}")
    public ResponseEntity<String> eliminarVentaDetalle(@PathVariable("id") long id) {
        saleDetService.eliminarVentaDetalle(id);
        return ResponseEntity.ok("Detalle de venta eliminado exitosamente");
    }

    @Operation(summary = "Buscar detalle de venta por ID", description = "Busca un detalle de venta según su ID")
    @GetMapping("/search_saleDetail_by_ID/{id}")
    public ResponseEntity<Sale_Detail_Entity> buscarPorId(@PathVariable("id") long id) {
        Sale_Detail_Entity ventaDetalleEncontrado = saleDetService.buscarPorIdVentaDetalle(id);
        if (ventaDetalleEncontrado != null) {
            return ResponseEntity.ok(ventaDetalleEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar detalle de venta por ID de venta", description = "Busca un detalle de venta según el ID de la venta")
    @GetMapping("/search_saleDetail_by_saleID/{id}")
    public ResponseEntity<Sale_Detail_Entity> buscarPorVentaId(@PathVariable("id") long id) {
        Sale_Detail_Entity ventaDetalleEncontrado = saleDetService.buscarPorVentaDetalle_VentaID(id);
        if (ventaDetalleEncontrado != null) {
            return ResponseEntity.ok(ventaDetalleEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
