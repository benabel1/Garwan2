package api.publiced;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import data.Garwan_User;
import repo.UserRepositary;

@RunWith(SpringRunner.class)
public class UserReigstrationTest {

	@MockBean
	UserRepositary userRepository;
	
	@Before
	public void setUp() {
		Garwan_User pavol = new Garwan_User();
		pavol.setUsername("Pavol");
		pavol.setRoles("ADMIN");
		
		Mockito
		.when(userRepository.findByUsername(pavol.getUsername()))
		.thenReturn(pavol);
		
		Mockito
		.when(userRepository.save(null))
		.thenReturn(null);
		
		Garwan_User d = new Garwan_User();
		d.setUsername("Jano");
		d.setRoles("USER");
		
	}
	
	@Test
	public void testIfPavolIsPresent() {
		Garwan_User searchExistingUser = userRepository.findByUsername("Pavol");
		
		assertNotNull(searchExistingUser);
		assertEquals("Pavol", searchExistingUser.getUsername());
	}
	
	@Test
	public void testSearchNoneExistingUser() {
		Garwan_User searchExistingUser = userRepository.findByUsername("Anna");
		
		assertNull(searchExistingUser);
	}
	
	@Test
	public void testAddingNull() {
		Garwan_User searchExistingUser = userRepository.save(null);
		
		assertNull(searchExistingUser);
	}
}
