package api.privated_api
;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.servlet.ServletContext;

//import org.springframework.security.test.context.support.WithMockUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.GarwanMainApplication;
import data.Product;
import dto.ProductDTO;
import dto.UserDTO;
import repo.GarwanProductRepository;
import repo.OrderRepository;
import repo.UserRepositary;
import services.OrderService;
import services.ProductService;
import services.UserService;


//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = GarwanMainApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")

public class PrivateAdminControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;

	@Autowired
	GarwanProductRepository productDao;
	
	@Autowired
	OrderRepository orderDao;


	@Test
	public void testRunningMomeSecurity() throws Exception {

		mockMVC.perform(get("/admin/info")
				.accept(MediaType.ALL)
				//not working in this way
//				.with(httpBasic("user","password")))
		)
		.andDo(print())
		.andExpect(status().isUnauthorized());

	}
	
	@Test
	
	public void testRunning() throws Exception {

		mockMVC.perform(get("/admin/info")
				.accept(MediaType.ALL)
				//not working in this way
//				.with(httpBasic("user","password")))
		)
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString()
		.contains("is running");

	}

	@Test
	public void testAllProducts() throws Exception {

		mockMVC.perform(get("/admin/allProducts")
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	
	@Test
	public void testAllOrders() throws Exception {

		mockMVC.perform(get("/admin/allOrders")
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	
	@Test
	public void testCreateNewProduct() throws Exception {

		ProductDTO product = new ProductDTO();
		product.setPrice(new BigDecimal(999d));
		product.setName("Novy product");
		
		
		mockMVC.perform(post("/admin/addProduct")
//				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(product))
//				.with(user("anyUserName"))
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is(product.getName())))
		.andExpect(jsonPath("$.price", is(product.getPrice())))
		.andExpect(jsonPath("$.description", is(product.getDecription())));

	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
