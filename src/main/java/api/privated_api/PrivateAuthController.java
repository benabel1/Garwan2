package api.privated_api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import data.Garwan_User;
import data.OperationResult;
import data.Order;
import dto.OrderDTO;
import repo.UserRepositary;
import services.OrderService;
import services.ProductService;
import services.UserService;

@RestController
@RequestMapping(value = "auth/orders")
public class PrivateAuthController {
	
	Logger logger = LoggerFactory.getLogger(PrivateAuthController.class);

	@Autowired
	OrderService orderService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepositary repo;

	/**
	 * Create new order for logged user
	 * 
	 * @param dto
	 * @param username
	 * @return
	 */
	@PostMapping(value = "create")
	public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDTO dto, @CurrentSecurityContext(expression = "authentication.name") String username) {

		OperationResult<Order, OrderService> created;

		if (username == null) {
			logger.info("User is not existing some authetification failure");
			return new ResponseEntity<String>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		} else {
			Garwan_User user = repo.findByUsername(username);
			if(user != null) {
				created =  orderService.createOrder(dto, user);
			}
			else {
				logger.info("User was not found");
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
		}

		return new ResponseEntity<String>(created + " was craeted for: ", HttpStatus.OK);
	}

	/**
	 * Return all order created by current authenticated user
	 * 
	 * @param username- current username of authenticated user
	 * 
	 * @return list of orders
	 */
	@GetMapping(value = "myOrders")
	public ResponseEntity<List<OrderDTO>> allOrders(@CurrentSecurityContext(expression = "authentication.name") String username) {
		List<OrderDTO> result = new ArrayList<OrderDTO>();
		
		if (username != null) {
			Garwan_User user = repo.findByUsername(username);
			result = orderService.getAllOrdersByUser(user);
		}

		return new ResponseEntity<List<OrderDTO>>(result, HttpStatus.OK);
	}

	/**
	 * Default operation for check if Controller is working
	 * @return
	 */
	@GetMapping(value = "info")
	public ResponseEntity<String> info() {
		return new ResponseEntity<String>(this + " is running", HttpStatus.OK);
	}

}
