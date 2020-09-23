package app;

import java.math.BigDecimal;
import java.util.Collections;

import org.hibernate.mapping.Collection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import api.privated_api.PrivateAdminController;
import api.privated_api.PrivateAuthController;
import api.privated_api.PrivateAuthWithEntityController;
import api.public_api.PublicProductEndpoint;
import api.public_api.PublicUserRegistrationController;
import data.AnimalCategory;
import data.Product;
import dto.AnimalCategoryDTO;
import repo.GarwanProductRepository;

@SpringBootConfiguration
@EnableJpaRepositories(basePackages = { "repo" })
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

	@Bean
	PrivateAuthWithEntityController authControllerWithEntity() {
		return new PrivateAuthWithEntityController();
	}

	@Bean
	public CommandLineRunner demo(GarwanProductRepository repository) {
		return (args) -> {
//			insert into product_table (product_id, name, price, description) 
//			VALUES (1, 'kavovar', 1145.00, 'Super tuper tool for making coffee');
//
//			insert into product_table (product_id, name, price, description) 
//			VALUES (2, 'termoska', 50.00, 'Super tuper tool for making coffee');
//
//			insert into product_table (product_id, name, price, description) 
//			VALUES (3, 'monitor', 550.00, 'I want this staff!!');
//
//			insert into product_table (product_id, name, price, description) 
//			VALUES (4, 'keybaord', 22.00, 'Querty or not Querty');
//
//			insert into product_table (product_id, name, price, description) 
//			VALUES (5, 'mouse', 28.00, 'Caught me if you can');
//
//			insert into product_table (product_id, name, price, description) 
//			VALUES (6, 'knife', 18.00, 'I can slice almost everything');
//
//			insert into product_table (product_id, name, price, description) 
//			VALUES (7, 'BFG', 180000.00, 'How does it appear here???');
			
			Product pKavovar = new Product(0, "kavovar", new BigDecimal(1145.00d), AnimalCategory.others, "Super tuper tool for making coffee", Collections.emptyList());
			repository.save(pKavovar);
			
			Product pTermoska = new Product(2, "termoska", new BigDecimal(50.00d), AnimalCategory.others, "Super tuper tool for keeping coffee hot", Collections.emptyList());
			repository.save(pTermoska);
			
			Product pMonitor = new Product(3, "monitor", new BigDecimal(550.00d), AnimalCategory.others, "I want this staff!!", Collections.emptyList());
			repository.save(pMonitor);
			
			Product pKeyboard = new Product(4, "keyboard", new BigDecimal(22.00d), AnimalCategory.others, "Querty or not Querty", Collections.emptyList());
			repository.save(pKeyboard);
			
			Product pMouse = new Product(5, "mouse", new BigDecimal(28.00d), AnimalCategory.others, "Caught me if you can", Collections.emptyList());
			repository.save(pMouse);
			
			Product pKnife = new Product(6, "knife", new BigDecimal(18.00d), AnimalCategory.others, "I can slice almost everything!! Baccon, bacon, ... .", Collections.emptyList());
			repository.save(pKnife);
			
			Product pBFG = new Product(7, "BFG", new BigDecimal(1800000.00d), AnimalCategory.others, "How does it appear here???", Collections.emptyList());
			repository.save(pBFG);
		};
	}
	
	
}
