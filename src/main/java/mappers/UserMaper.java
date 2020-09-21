package mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import data.Garwan_User;
import dto.UserDTO;

@Component
public class UserMaper {
	
	static Logger logger = LoggerFactory.getLogger(UserMaper.class);

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public static Garwan_User toDTO(UserDTO dto) {
		if(dto == null) {
			logger.info("UserDTO was null at start of mapping!");
			return null;
		}
		
		Garwan_User user = new Garwan_User();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setRoles(dto.getRoles());
		
		return user;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public static UserDTO toDTO(Garwan_User user) {
		if (user == null) {
			logger.info("User was null at start of mapping!");
			return null;
		}
		
		UserDTO dto = new UserDTO();
		dto.setEmail(user.getEmail());
		dto.setUsername(user.getUsername());
		dto.setRoles(user.getRoles());
		
		return dto;
	}
}
