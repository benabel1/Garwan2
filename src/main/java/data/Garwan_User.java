package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "user_table")
public class Garwan_User {
	
	@Id
	@Column(name="username", unique = true)
	private String username;
	
	@Column(unique = true, name="email")
	@Email(message = "Please, this should be valid e mail")
	private String email;
	
	
	@Column(name = "password")
//	@NotNull(message = "Password should not be null")
	@Min(value = 1, message = "Password field should ne at least 1 letter")
	private String password;

	@Column(name = "roles")
	@NotNull(message = "Roles should not be null")
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
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getRoles() {
		return roles;
	}


}
