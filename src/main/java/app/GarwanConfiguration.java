package app;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import api.privated_api.PrivateAdminController;
import api.privated_api.PrivateAuthController;
import api.public_api.PublicProductEndpoint;
import api.public_api.PublicUserRegistrationController;


@SpringBootConfiguration
@EnableJpaRepositories(basePackages = {"repo"})
@ComponentScan(basePackages = { "services" })
@EntityScan("data")
public class GarwanConfiguration {

	@Bean 
	PublicProductEndpoint prodcutBean() {
		return new PublicProductEndpoint();
	}
	
	@Bean
	PrivateAdminController orderController() {
		return new PrivateAdminController();
	}
	
	@Bean
	PublicUserRegistrationController publicUsersController() {
		return new PublicUserRegistrationController();
	}
	
	@Bean
	PrivateAuthController authController() {
		return new PrivateAuthController();
	}
}
