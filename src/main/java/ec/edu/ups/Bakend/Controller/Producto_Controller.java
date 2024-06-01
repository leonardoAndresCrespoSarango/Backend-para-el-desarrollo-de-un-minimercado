package ec.edu.ups.Bakend.Controller;


import ec.edu.ups.Bakend.Entity.Product_Entity;
import ec.edu.ups.Bakend.Services.Product_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class Producto_Controller {
    private Product_Service productService;
    @Autowired
    public Producto_Controller(Product_Service clientService) {
        this.productService = clientService;
    }
    @GetMapping("/list_products")
    public List<Product_Entity> obtenerTodosLosItems() {
        return productService.getListarProductos();
    }
    @PostMapping("/create_product")
    public ResponseEntity<String> crearProducto(@RequestBody Product_Entity producto) {
        try {
            productService.insertarProduccto(producto);
            return ResponseEntity.ok("producto creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el producto: " + e.getMessage());
        }
    }
    @PutMapping("/update_product/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable("id") long id, @RequestBody Product_Entity productoActualizado) {
        Product_Entity productoActualizadoResultado = productService.actualizarProducto(id, productoActualizado);
        if (productoActualizadoResultado != null) {
            return ResponseEntity.ok("producto actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete_product_by_ID/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable("id") long id) {
        productService.eliminarProducto(id);
        return ResponseEntity.ok("producto eliminado exitosamente");
    }

    @GetMapping("/search_product_by_ID/{id}")
    public ResponseEntity<Product_Entity> buscarPorId(@PathVariable("id") long id) {
        Product_Entity clienteEncontrado = productService.buscarPorIdProducto(id);
        if (clienteEncontrado != null) {
            return ResponseEntity.ok(clienteEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/search_product_by_name/{nombre}")
    public ResponseEntity<Product_Entity> buscarUsu(@PathVariable("nombre") String nombre) {
        Product_Entity productoEncontrado = productService.buscarPorProductoNombre(nombre);
        if (productoEncontrado != null) {
            return ResponseEntity.ok(productoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
