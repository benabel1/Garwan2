package api.publiced;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import repo.GarwanProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
//@AutoConfigureMockMvc
@WebAppConfiguration
//@TestPropertySource("classpath:application-test.properties")
public class ProductDetailsEndpointTest {

//	@Autowired
	private MockMvc mockMVC;

	@MockBean
	GarwanProductRepository productRepo;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() throws Exception {
		this.mockMVC = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void givenWac_whenServletContext_thenItProvidesGreetController() {
		ServletContext servletContext = wac.getServletContext();

		assertNotNull(servletContext);
		assertTrue(servletContext instanceof MockServletContext);
		assertNotNull(wac.getBean("greetController"));
	}

	@Test
	public void testTODO() throws Exception {

		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.get("/public/info").accept(MediaType.APPLICATION_JSON)).andReturn();

	}

}
