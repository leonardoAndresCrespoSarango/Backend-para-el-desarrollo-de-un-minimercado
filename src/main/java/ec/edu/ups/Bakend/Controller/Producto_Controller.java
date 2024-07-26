package ec.edu.ups.Bakend.Controller;

import ec.edu.ups.Bakend.Entity.Product_Entity;
import ec.edu.ups.Bakend.Services.Product_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Producto", description = "Operaciones relacionadas con la gestión de productos")
public class Producto_Controller {
    private Product_Service productService;

    @Autowired
    public Producto_Controller(Product_Service productService) {
        this.productService = productService;
    }

    @Operation(summary = "Listar todos los productos", description = "Obtiene una lista de todos los productos")
    @GetMapping("/list_products")
    public List<Product_Entity> obtenerTodosLosItems() {
        return productService.getListarProductos();
    }

    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto con los datos proporcionados")
    @PostMapping("/create_product")
    public ResponseEntity<String> crearProducto(@RequestBody Product_Entity producto) {
        try {
            productService.insertarProduccto(producto);
            return ResponseEntity.ok("Producto creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el producto: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un producto existente", description = "Actualiza los datos de un producto existente según su ID")
    @PutMapping("/update_product/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable("id") long id, @RequestBody Product_Entity productoActualizado) {
        Product_Entity productoActualizadoResultado = productService.actualizarProducto(id, productoActualizado);
        if (productoActualizadoResultado != null) {
            return ResponseEntity.ok("Producto actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update_product/{id}/{stock}")
    public ResponseEntity<String> actualizarStockProducto(@PathVariable("id") long id, @PathVariable("stock") long stock) {
        Product_Entity productoActualizadoResultado = productService.actualizarStockProducto(id, stock);
        if (productoActualizadoResultado != null) {
            return ResponseEntity.ok("Producto actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update_productV1/{id}")
    public ResponseEntity<String> actualizarStockProductoV1(@PathVariable("id") long id, @RequestBody Product_Entity productoActualizado) {
        Product_Entity productoActualizadoResultado = productService.actualizarStockProducto(id, productoActualizado.getStock());
        if (productoActualizadoResultado != null) {
            return ResponseEntity.ok("Producto actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update_productV2/{id}")
    public ResponseEntity<String> actualizarStockProductoV2(@PathVariable("id") long id, @RequestBody Product_Entity productoActualizado) {
        Product_Entity productoActualizadoResultado = productService.actualizarStockProductoVenta(id, productoActualizado.getStock());
        if (productoActualizadoResultado != null) {
            return ResponseEntity.ok("Producto actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/updatePro")
    public ResponseEntity<String> actualizarProductos(@RequestBody Product_Entity productoActualizado) {
        Product_Entity productoActualizadoResultado = productService.actualizarProducto(productoActualizado.getProduct_id(), productoActualizado);
        if (productoActualizadoResultado != null) {
            return ResponseEntity.ok("Producto actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto según su ID")
    @DeleteMapping("/delete_product_by_ID/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable("id") long id) {
        productService.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado exitosamente");
    }

    @DeleteMapping("/deletePro")
    public ResponseEntity<String> eliminarProductos(@RequestBody Product_Entity productoActualizado) {
        productService.eliminarProducto(productoActualizado.getProduct_id());
        return ResponseEntity.ok("Producto eliminado exitosamente");
    }

    @Operation(summary = "Buscar producto por ID", description = "Busca un producto según su ID")
    @GetMapping("/search_product_by_ID/{id}")
    public ResponseEntity<Product_Entity> buscarPorId(@PathVariable("id") long id) {
        Product_Entity productoEncontrado = productService.buscarPorIdProducto(id);
        if (productoEncontrado != null) {
            return ResponseEntity.ok(productoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search_product_by_ID")
    public ResponseEntity<Product_Entity> buscarPorIdO(@RequestBody Product_Entity productoABuscar ) {
        Product_Entity productoEncontrado = productService.buscarPorIdProducto(productoABuscar.getProduct_id());
        if (productoEncontrado != null) {
            return ResponseEntity.ok(productoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @Operation(summary = "Buscar producto por nombre", description = "Busca un producto según su nombre")
    @GetMapping("/search_product_by_name/{nombre}")
    public ResponseEntity<Product_Entity> buscarProducto(@PathVariable("nombre") String nombre) {
        Product_Entity productoEncontrado = productService.buscarPorProductoNombre(nombre);
        if (productoEncontrado != null) {
            return ResponseEntity.ok(productoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
