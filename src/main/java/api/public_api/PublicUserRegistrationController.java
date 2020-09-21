package api.public_api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDTO;
import services.UserService;

@RestController
@RequestMapping(value = "/public/users")
public class PublicUserRegistrationController {

	Logger logger = LoggerFactory.getLogger(PublicUserRegistrationController.class);
	
	@Autowired
	UserService service;

	@PostMapping(value = "addUser",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO registrationUser(@Valid @RequestBody UserDTO user) {
		return service.registerNewUser(user);
	}

	@GetMapping(value = "info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> info() {
		return new ResponseEntity<String>(this + " is running", HttpStatus.OK);
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
		return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
