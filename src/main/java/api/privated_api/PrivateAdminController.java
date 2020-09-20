package api.privated_api;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
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
import repo.UserRepositary;
import services.OrderService;
import services.ProductService;
import services.UserService;

@RestController
@RequestMapping(value = "admin")
public class PrivateAdminController {
	
	Logger logger = LoggerFactory.getLogger(PrivateAdminController.class);

	@Autowired
	OrderService orderService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepositary repo;

	@PostMapping(value = "addProduct")
	public ResponseEntity<String> createProduct(@RequestBody ProductDTO dto) {

		Product p = ProductMapper.code(dto);
		p = productService.create(p);
		logger.info("New product was created " + p);
		ProductDTO newDTO = ProductMapper.decode(p);
		
		return new ResponseEntity<String>(newDTO + " was craeted for: ", HttpStatus.OK);
	}

	@GetMapping(value = "allProducts")
	public ResponseEntity<List<ProductDTO>> allProducts() {
		List<ProductDTO> result = productService.getAllProducts();

		return new ResponseEntity<List<ProductDTO>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "allOrders")
	public ResponseEntity<List<OrderDTO>> allOrders() {
		List<OrderDTO> result = orderService.getAllOrders();

		return new ResponseEntity<List<OrderDTO>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "allUsers")
	public ResponseEntity<List<UserDTO>> allUsers() {
		List<UserDTO> result = userService.getAllUsers();

		return new ResponseEntity<List<UserDTO>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "info")
	public ResponseEntity<String> info() {
		return new ResponseEntity<String>(this + " is running", HttpStatus.OK);
	}

}
