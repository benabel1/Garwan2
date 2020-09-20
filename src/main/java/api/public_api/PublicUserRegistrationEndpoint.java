package api.public_api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDTO;
import services.UserService;

@RestController
@RequestMapping(value = "/public/users")
public class PublicUserRegistrationEndpoint {
	
	@Autowired
	UserService service;

	@PostMapping(value = "addUser")
	public UserDTO registrationUser(@Valid @RequestBody UserDTO user) {
		return service.registerNewUser(user);
	}
	
	@GetMapping(value = "info")
	public ResponseEntity<String> info() {
		return new ResponseEntity<String>(this + " is running", HttpStatus.OK);
	}
	
}
