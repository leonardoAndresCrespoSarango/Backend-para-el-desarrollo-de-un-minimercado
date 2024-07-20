package ec.edu.ups.Bakend.Controller;

import ec.edu.ups.Bakend.Entity.User_Entity;
import ec.edu.ups.Bakend.Services.User_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Usuario", description = "Operaciones relacionadas con la gestión de usuarios")
public class User_Controller {

    private User_Service userService;

    @Autowired
    public User_Controller(User_Service userService) {
        this.userService = userService;
    }

    @Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista de todos los usuarios")
    @GetMapping("/list_users")
    public List<User_Entity> obtenerTodosLosItems() {
        return userService.getListarUsuario();
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario con los datos proporcionados")
    @PostMapping("/create_user")
    public ResponseEntity<String> crearUsuario(@RequestBody User_Entity usuario) {
        try {
            userService.insertarUsuario(usuario);
            return ResponseEntity.ok("Usuario creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se pudo crear el usuario: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un usuario existente", description = "Actualiza los datos de un usuario existente según su ID")
    @PutMapping("/update_user/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable("id") long id, @RequestBody User_Entity usuarioActualizado) {
        User_Entity usuarioActualizadoResultado = userService.actualizarUsuario(id, usuarioActualizado);
        if (usuarioActualizadoResultado != null) {
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario según su ID")
    @DeleteMapping("/delete_user_by_ID/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") long id) {
        userService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado exitosamente");
    }

    @Operation(summary = "Buscar usuario por ID", description = "Busca un usuario según su ID")
    @GetMapping("/search_user_by_ID/{id}")
    public ResponseEntity<User_Entity> buscarPorId(@PathVariable("id") long id) {
        User_Entity usuarioEncontrado = userService.buscarPorId(id);
        if (usuarioEncontrado != null) {
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar usuario por nombre", description = "Busca un usuario según su nombre")
    @GetMapping("/search_user_by_name/{nombre}")
    public ResponseEntity<User_Entity> buscarUsu(@PathVariable("nombre") String usu) {
        User_Entity usuarioEncontrado = userService.buscarPorUsuarioNombre(usu);
        if (usuarioEncontrado != null) {
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar usuario por nombre y contraseña", description = "Busca un usuario según su nombre y contraseña")
    @GetMapping("/search_user_by_name/{nombre}/user_password/{contrasenia}")
    public ResponseEntity<Object> buscarUsu(@PathVariable("nombre") String usu, @PathVariable("contrasenia") String contrasenia) {
        User_Entity usuarioEncontrado = userService.buscarPorUsuarioYContrasenia(usu, contrasenia);
        if (usuarioEncontrado != null) {
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody User_Entity usuario) {
        User_Entity usuarioEncontrado = userService.buscarPorUsuarioYContrasenia(usuario.getNombre(), usuario.getContrasenia());
        if (usuarioEncontrado != null) {
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
