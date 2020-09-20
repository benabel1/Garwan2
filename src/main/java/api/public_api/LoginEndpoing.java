package api.public_api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "public/login")
@Deprecated
public class LoginEndpoing {
	
	@GetMapping(value = "info")
	@ResponseBody
	public ResponseEntity<String> info() {
		return new ResponseEntity<String>("GET Response is OK and this is running", HttpStatus.OK);
	}
	
}
