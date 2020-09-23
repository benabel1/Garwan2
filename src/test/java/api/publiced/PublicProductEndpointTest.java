package api.publiced;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.servlet.ServletContext;

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

import app.GarwanMainApplication;
import data.Product;
import repo.GarwanProductRepository;
import services.ProductService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = GarwanMainApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PublicProductEndpointTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	ProductService service;

	@Autowired
	GarwanProductRepository product;

	@Test
	public void testCountOfProducts() throws Exception {
		assertEquals(7, product.count());
	}

	@Test
	public void testAllProduct() throws Exception {

		mockMVC.perform(get("/public/products/all")
				.accept(MediaType.ALL)
				.param("direction", "DESC"))
		.andDo(print())
		.andExpect(status().isOk());

	}
	@Test
	public void testFilteredProducts() throws Exception {

		mockMVC.perform(get("/public/products/filterMinMax")
				.accept(MediaType.APPLICATION_JSON)
				.param("min", "10")
				.param("max", "900"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	public void testDetails() throws Exception {
		
		mockMVC.perform(get("/public/products/details")
				.param("id", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("kavovar")));
						
	}

	@Test
	public void testAddingOne() throws Exception {
		long count = product.count();

		Product neproduct = new Product();
		neproduct.setDesription("Description");

		product.save(neproduct);

		assertEquals(count + 1, product.count());
	}

}
