package ec.edu.ups.Bakend.Controller;


import ec.edu.ups.Bakend.Entity.Supplier_Entity;
import ec.edu.ups.Bakend.Services.Supplier_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins = "http://localhost:4200")
public class Supplier_Controller {
    private Supplier_Service supplierService;
    @Autowired
    public Supplier_Controller(Supplier_Service userService) {
        this.supplierService = userService;
    }
    @GetMapping("/list_suppliers")
    public List<Supplier_Entity> obtenerTodosLosItems() {
        return supplierService.getListarProveedores();
    }
    @PostMapping("/create_supplier")
    public ResponseEntity<String> crearProveedor(@RequestBody Supplier_Entity usuario) {
        try {
            supplierService.insertarProveedor(usuario);
            return ResponseEntity.ok("proveedor creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el proveedor: " + e.getMessage());
        }
    }
    @PutMapping("/update_supplier/{id}")
    public ResponseEntity<String> actualizarProveedor(@PathVariable("id") long id, @RequestBody Supplier_Entity proveedorActualizado) {
        Supplier_Entity proveedorActualizadoResultado = supplierService.actualizarProveedor(id, proveedorActualizado);
        if (proveedorActualizadoResultado != null) {
            return ResponseEntity.ok("proveedor actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete_supplier_by_ID/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable("id") long id) {
        supplierService.eliminarProveedor(id);
        return ResponseEntity.ok("proveedor eliminado exitosamente");
    }

    @GetMapping("/search_supplier_by_ID/{id}")
    public ResponseEntity<Supplier_Entity> buscarPorId(@PathVariable("id") long id) {
        Supplier_Entity proveedorEncontrado = supplierService.buscarPorId(id);
        if (proveedorEncontrado != null) {
            return ResponseEntity.ok(proveedorEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/search_supplier_by_name/{nombre}")
    public ResponseEntity<Supplier_Entity> buscarProveedor(@PathVariable("nombre") String usu) {
        Supplier_Entity proveedorEncontrado = supplierService.buscarPorProveedorNombre(usu);
        if (proveedorEncontrado != null) {
            return ResponseEntity.ok(proveedorEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
