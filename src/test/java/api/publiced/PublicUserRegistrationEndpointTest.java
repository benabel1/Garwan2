package api.publiced;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.GarwanMainApplication;
import dto.UserDTO;
import repo.UserRepositary;
import services.UserService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = GarwanMainApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PublicUserRegistrationEndpointTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	UserService service;

	@Autowired
	UserRepositary product;

	@Test
	public void testRunning() throws Exception {

		mockMVC.perform(get("/public/users/info")
				.accept(MediaType.ALL))
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString()
		.contains("is running");

	}

	@Test
	public void testRegistrationUser() throws Exception {

		String userName = "peter";
		String userRole = "ADMIN";
		String userEmail = "peter@test.peter";
		
		UserDTO user = new UserDTO();
		user.setUsername(userName);
		user.setRoles(userRole);
		user.setEmail(userEmail);
		
		mockMVC.perform(post("/public/users/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.username", is(userName)))
		.andExpect(jsonPath("$.roles", is(userRole)))
		.andExpect(jsonPath("$.email", is(userEmail)));

	}
	
	@Test
	public void testRegistrationUserThatAlreadyExist() throws Exception {

		String userName = "peter";
		String userRole = "ADMIN";
		String userEmail = "peter@test.peter";
		
		UserDTO user = new UserDTO();
		user.setUsername(userName);
		user.setRoles(userRole);
		user.setEmail(userEmail);
		
		mockMVC.perform(post("/public/users/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.username", is(userName)))
		.andExpect(jsonPath("$.roles", is(userRole)))
		.andExpect(jsonPath("$.email", is(userEmail)));

	}
	
	@Test
	public void testRegistrationUserBadUrl() throws Exception {

		mockMVC.perform(get("/public/users/badaddUser")
				.accept(MediaType.ALL))
		.andDo(print())
		.andExpect(status().isNotFound());

	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
