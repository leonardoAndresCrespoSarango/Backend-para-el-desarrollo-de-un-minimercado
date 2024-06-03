package ec.edu.ups.Bakend.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("app para gestión de un minimercado")
                        .version("1.0.0")
                        .description("Esta API proporciona funcionalidades para la gestión de un minimercado, incluyendo operaciones de inventario, ventas, compras y más. Utiliza PostgreSQL como base de datos para almacenar y gestionar la información."));
    }
}
