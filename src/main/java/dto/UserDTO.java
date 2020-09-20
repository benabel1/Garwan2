package dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * DTO pre user 
 * 
 * @author Doma
 *
 */
public class UserDTO {
	@Email(message = "UserDTO has not valid e mail")
	private String email;
	
	@NotNull(message = "UserDTO has blank username")
	private String username;
	
	@NotNull
	private String roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getRoles() {
		return roles;
	}

}
