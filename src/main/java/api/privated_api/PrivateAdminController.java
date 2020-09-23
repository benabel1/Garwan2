package api.privated_api;


import java.util.List;

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

import data.Product;
import dto.OrderDTO;
import dto.ProductDTO;
import dto.UserDTO;
import mappers.ProductMapper;
import services.OrderService;
import services.ProductService;
import services.UserService;

/**
 * Controller only for ADMIN user 
 * 
 * @author Doma
 *
 */
@RestController
@RequestMapping(value = "/admin")
public class PrivateAdminController {
	
	Logger logger = LoggerFactory.getLogger(PrivateAdminController.class);

	@Autowired
	OrderService orderService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;


	@PostMapping(value = "/addProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createProduct(@RequestBody ProductDTO dto) {

		Product p = ProductMapper.fromDTO(dto);
		p = productService.create(p);
		logger.info("New product was created " + p);
		ProductDTO newDTO = ProductMapper.toDTO(p);
		
		return new ResponseEntity<String>(newDTO + " was craeted for: ", HttpStatus.OK);
	}

	@GetMapping(value = "/allProducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> allProducts() {
		List<ProductDTO> result = productService.getAllProducts();

		return new ResponseEntity<List<ProductDTO>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/allOrders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDTO>> allOrders() {
		List<OrderDTO> result = orderService.getAllOrders();

		return new ResponseEntity<List<OrderDTO>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/allUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> allUsers() {
		List<UserDTO> result = userService.getAllUsers();

		return new ResponseEntity<List<UserDTO>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> info() {
		return new ResponseEntity<String>(this + " is running", HttpStatus.OK);
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
		return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
