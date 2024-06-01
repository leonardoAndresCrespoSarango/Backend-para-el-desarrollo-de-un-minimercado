package ec.edu.ups.Bakend.Controller;


import ec.edu.ups.Bakend.Entity.Promotion_Entity;
import ec.edu.ups.Bakend.Services.Promotion_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
@CrossOrigin(origins = "http://localhost:4200")
public class Promotion_Controller {
    private Promotion_Service promotionService;
    @Autowired
    public Promotion_Controller(Promotion_Service promotionService) {
        this.promotionService = promotionService;
    }
    @GetMapping("/list_promotions")
    public List<Promotion_Entity> obtenerTodosLosItems() {
        return promotionService.getListarPromociones();
    }
    @PostMapping("/create_promotion")
    public ResponseEntity<String> crearPromocion(@RequestBody Promotion_Entity pedido) {
        try {
            promotionService.insertarPromocion(pedido);
            return ResponseEntity.ok("promocion creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear la promocion: " + e.getMessage());
        }
    }
    @PutMapping("/update_promotion/{id}")
    public ResponseEntity<String> actualizarPromocion(@PathVariable("id") long id, @RequestBody Promotion_Entity promocionActualizado) {
        Promotion_Entity promocionActualizadoResultado = promotionService.actualizarPromocion(id, promocionActualizado);
        if (promocionActualizadoResultado != null) {
            return ResponseEntity.ok("promocion actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete_promotion_by_ID/{id}")
    public ResponseEntity<String> eliminarPeromocion(@PathVariable("id") long id) {
        promotionService.eliminarPromocion(id);
        return ResponseEntity.ok("promocion eliminado exitosamente");
    }

    @GetMapping("/search_promotion_by_ID/{id}")
    public ResponseEntity<Promotion_Entity> buscarPorId(@PathVariable("id") long id) {
        Promotion_Entity promocionEncontrado = promotionService.buscarPorId(id);
        if (promocionEncontrado != null) {
            return ResponseEntity.ok(promocionEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


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
