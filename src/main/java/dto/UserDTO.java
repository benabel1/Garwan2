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
	
	@NotNull(message = "UserDTO username as null")
	private String username;
	
	@NotNull(message = "UserDTO roles as null")
	private String roles;

	public UserDTO() {
		super();
	}

	public UserDTO(@Email(message = "UserDTO has not valid e mail") String email, @NotNull(message = "UserDTO username as null") String username, @NotNull(message = "UserDTO roles as null") String roles) {
		super();
		this.email = email;
		this.username = username;
		this.roles = roles;
	}

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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}


}
