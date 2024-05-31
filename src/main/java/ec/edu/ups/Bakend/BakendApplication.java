package ec.edu.ups.Bakend;

import ec.edu.ups.Bakend.Entity.User_Entity;
import ec.edu.ups.Bakend.Services.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BakendApplication implements CommandLineRunner {

	@Autowired
	private User_Service userService;

	public static void main(String[] args) {
		SpringApplication.run(BakendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User_Entity usuario = new User_Entity("juan", "juan@example.com", "x", "administrador");
		userService.insertarUsuario(usuario);
		System.out.println("Usuario creado: " + usuario.toString());
	}
}
