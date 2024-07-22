package ec.edu.ups.Bakend.Controller;

import ec.edu.ups.Bakend.Entity.Promotion_Entity;
import ec.edu.ups.Bakend.Services.Promotion_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Promotion", description = "Operaciones relacionadas con la gestión de promociones")
public class Promotion_Controller {
    private Promotion_Service promotionService;

    @Autowired
    public Promotion_Controller(Promotion_Service promotionService) {
        this.promotionService = promotionService;
    }

    @Operation(summary = "Listar todas las promociones", description = "Obtiene una lista de todas las promociones")
    @GetMapping("/list_promotions")
    public List<Promotion_Entity> obtenerTodosLosItems() {
        return promotionService.getListarPromociones();
    }

    @Operation(summary = "Crear una nueva promoción", description = "Crea una nueva promoción con los datos proporcionados")
    @PostMapping("/create_promotion")
    public ResponseEntity<String> crearPromocion(@RequestBody Promotion_Entity promocion) {
        try {
            promotionService.insertarPromocion(promocion);
            return ResponseEntity.ok("Promoción creada exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear la promoción: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar una promoción existente", description = "Actualiza los datos de una promoción existente según su ID")
    @PutMapping("/update_promotion/{id}")
    public ResponseEntity<String> actualizarPromocion(@PathVariable("id") long id, @RequestBody Promotion_Entity promocionActualizado) {
        Promotion_Entity promocionActualizadoResultado = promotionService.actualizarPromocion(id, promocionActualizado);
        if (promocionActualizadoResultado != null) {
            return ResponseEntity.ok("Promoción actualizada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/updateProm")
    public ResponseEntity<String> actualizarPromociones(@RequestBody Promotion_Entity promocionActualizado) {
        Promotion_Entity promocionActualizadoResultado = promotionService.actualizarPromocion(promocionActualizado.getPromotion_id(), promocionActualizado);
        if (promocionActualizadoResultado != null) {
            return ResponseEntity.ok("Promoción actualizada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una promoción", description = "Elimina una promoción según su ID")
    @DeleteMapping("/delete_promotion_by_ID/{id}")
    public ResponseEntity<String> eliminarPromocion(@PathVariable("id") long id) {
        promotionService.eliminarPromocion(id);
        return ResponseEntity.ok("Promoción eliminada exitosamente");
    }

    @DeleteMapping("/deleteProm")
    public ResponseEntity<String> eliminarPromociones(@RequestBody Promotion_Entity promocionEliminada) {
        promotionService.eliminarPromocion(promocionEliminada.getPromotion_id());
        return ResponseEntity.ok("Promoción eliminada exitosamente");
    }

    @Operation(summary = "Buscar promoción por ID", description = "Busca una promoción según su ID")
    @GetMapping("/search_promotion_by_ID/{id}")
    public ResponseEntity<Promotion_Entity> buscarPorId(@PathVariable("id") long id) {
        Promotion_Entity promocionEncontrado = promotionService.buscarPorId(id);
        if (promocionEncontrado != null) {
            return ResponseEntity.ok(promocionEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar promoción por nombre", description = "Busca una promoción según su nombre")
    @GetMapping("/search_promotion_by_name/{name}")
    public ResponseEntity<Promotion_Entity> buscarPromocion(@PathVariable("name") String name) {
        Promotion_Entity promocionEncontrado = promotionService.buscarPorPromocionNombre(name);
        if (promocionEncontrado != null) {
            return ResponseEntity.ok(promocionEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
