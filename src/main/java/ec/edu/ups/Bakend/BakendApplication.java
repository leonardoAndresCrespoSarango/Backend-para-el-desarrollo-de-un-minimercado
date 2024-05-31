package ec.edu.ups.Bakend;

import ec.edu.ups.Bakend.Entity.Sale_Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;


@SpringBootApplication
public class BakendApplication {



	public static void main(String[] args) {
		SpringApplication.run(BakendApplication.class, args);
		LocalDateTime fechaActual = LocalDateTime.now();
		System.out.println("Fecha actual: " + fechaActual);
		Sale_Entity sale = new Sale_Entity();
		sale.setFecha_venta(fechaActual);
	}


}
