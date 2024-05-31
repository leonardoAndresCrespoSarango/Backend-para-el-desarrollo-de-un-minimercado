package ec.edu.ups.Bakend.Controller;

import ec.edu.ups.Bakend.Entity.User_Entity;
import ec.edu.ups.Bakend.Services.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class User_Controller {
    private User_Service userService;
    @Autowired
    public User_Controller(User_Service userService) {
        this.userService = userService;
    }
    @GetMapping("/list_users")
    public List<User_Entity> obtenerTodosLosItems() {
        return userService.getListarUsuario();
    }
    @PostMapping("/crear")
    public ResponseEntity<String> crearCatalogo(@RequestBody User_Entity usuario) {
        try {
            userService.insertarUsuario(usuario);
            return ResponseEntity.ok("Usuario creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el usuario: " + e.getMessage());
        }
    }

}
