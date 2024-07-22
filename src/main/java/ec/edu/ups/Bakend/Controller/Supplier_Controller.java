package ec.edu.ups.Bakend.Controller;

import ec.edu.ups.Bakend.Entity.Supplier_Entity;
import ec.edu.ups.Bakend.Services.Supplier_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Proveedor", description = "Operaciones relacionadas con la gestión de proveedores")
public class Supplier_Controller {

    private Supplier_Service supplierService;

    @Autowired
    public Supplier_Controller(Supplier_Service supplierService) {
        this.supplierService = supplierService;
    }

    @Operation(summary = "Listar todos los proveedores", description = "Obtiene una lista de todos los proveedores")
    @GetMapping("/list_suppliers")
    public List<Supplier_Entity> obtenerTodosLosItems() {
        return supplierService.getListarProveedores();
    }

    @Operation(summary = "Crear un nuevo proveedor", description = "Crea un nuevo proveedor con los datos proporcionados")
    @PostMapping("/create_supplier")
    public ResponseEntity<String> crearProveedor(@RequestBody Supplier_Entity proveedor) {
        try {
            supplierService.insertarProveedor(proveedor);
            return ResponseEntity.ok("Proveedor creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el proveedor: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un proveedor existente", description = "Actualiza los datos de un proveedor existente según su ID")
    @PutMapping("/update_supplier/{id}")
    public ResponseEntity<String> actualizarProveedor(@PathVariable("id") long id, @RequestBody Supplier_Entity proveedorActualizado) {
        Supplier_Entity proveedorActualizadoResultado = supplierService.actualizarProveedor(id, proveedorActualizado);
        if (proveedorActualizadoResultado != null) {
            return ResponseEntity.ok("Proveedor actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateSup")
    public ResponseEntity<String> actualizarProveedores(@RequestBody Supplier_Entity proveedorActualizado) {
        Supplier_Entity proveedorActualizadoResultado = supplierService.actualizarProveedor(proveedorActualizado.getSupplier_id(), proveedorActualizado);
        if (proveedorActualizadoResultado != null) {
            return ResponseEntity.ok("Proveedor actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Eliminar un proveedor", description = "Elimina un proveedor según su ID")
    @DeleteMapping("/delete_supplier_by_ID/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable("id") long id) {
        supplierService.eliminarProveedor(id);
        return ResponseEntity.ok("Proveedor eliminado exitosamente");
    }
    @DeleteMapping("/deleteSup")
    public ResponseEntity<String> eliminarProveedores(@RequestBody Supplier_Entity proveedorEliminado) {
        supplierService.eliminarProveedor(proveedorEliminado.getSupplier_id());
        return ResponseEntity.ok("Proveedor eliminado exitosamente");
    }
    @Operation(summary = "Buscar proveedor por ID", description = "Busca un proveedor según su ID")
    @GetMapping("/search_supplier_by_ID/{id}")
    public ResponseEntity<Supplier_Entity> buscarPorId(@PathVariable("id") long id) {
        Supplier_Entity proveedorEncontrado = supplierService.buscarPorId(id);
        if (proveedorEncontrado != null) {
            return ResponseEntity.ok(proveedorEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar proveedor por nombre", description = "Busca un proveedor según su nombre")
    @GetMapping("/search_supplier_by_name/{nombre}")
    public ResponseEntity<Supplier_Entity> buscarProveedor(@PathVariable("nombre") String nombre) {
        Supplier_Entity proveedorEncontrado = supplierService.buscarPorProveedorNombre(nombre);
        if (proveedorEncontrado != null) {
            return ResponseEntity.ok(proveedorEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
