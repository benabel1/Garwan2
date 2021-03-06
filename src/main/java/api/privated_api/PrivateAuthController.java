package api.privated_api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import data.Garwan_User;
import data.OperationResult;
import data.Order;
import dto.OrderDTO;
import repo.UserRepositary;
import request.NewOrderCreation;
import services.OrderService;
import services.ProductService;
import services.UserService;

/** REST Controller for authentificated / logged users 
 * 
*/
@RestController
@RequestMapping(value = "/authU/orders")
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
	@PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createOrder(@RequestBody OrderDTO dto, @CurrentSecurityContext(expression = "authentication.name") String username) {

		OperationResult<Order, OrderService> created;

		if (username == null) {
			logger.info("User is not existing some authetification failure");
			return new ResponseEntity<String>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		} else {
			Garwan_User user = repo.findByUsername(username);
			if(user != null) {
				created =  orderService.createOrderForUser(dto, user);
			}
			else {
				logger.info("User was not found");
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
		}

		return new ResponseEntity<String>(created + " was craeted for: ", HttpStatus.CREATED);
	}
	
	/**
	 * Return all order created by for specific user
	 * 
	 * @param username- specific user
	 * 
	 * @return list of orders
	 */
	@GetMapping(value = "myOrdersRaw/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDTO>> allOrdersRaw(@PathVariable("username") String username) {
		List<OrderDTO> result = new ArrayList<OrderDTO>();
		
		if (username != null) {
			Garwan_User user = repo.findByUsername(username);
			result = orderService.getAllOrdersByUser(user);
		}

		return new ResponseEntity<List<OrderDTO>>(result, HttpStatus.OK);
	}
	
	/**
	 * PSS, this troll method help you with generation of RequestBody
	 * 
	 * @return example if can be found
	 */
	@GetMapping(value = "help", produces = MediaType.APPLICATION_JSON_VALUE)
	public NewOrderCreation trollPleaseHelpMe() {
		//TODO Bad manner, whole method remove or do some cleaning
		String username = "ja";
		NewOrderCreation example = new NewOrderCreation();
		
		if (username != null) {
			Garwan_User user = repo.findByUsername(username);
			List<OrderDTO> result = orderService.getAllOrdersByUser(user);
			if (result.size() > 0) {
				example.setOrder(result.get(0));
			}
			
		}
		
		return example;
	}

	/**
	 * Default operation for check if Controller is working
	 * @return
	 */
	@GetMapping(value = "info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> info() {
		return new ResponseEntity<String>(this + " is running", HttpStatus.OK);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
		return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
