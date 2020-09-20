package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Import(value = {
		GarwanConfiguration.class,
		GarwanSecurityConfig.class,
		SwaggerConfig.class
		})
@SpringBootApplication
@EnableSwagger2
public class GarwanMainApplication {
	
	/**
	 *  Main SpringBoot application launcher
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GarwanMainApplication.class, args);
		
	}
	
}
