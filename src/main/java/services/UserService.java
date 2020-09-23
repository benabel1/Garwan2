package services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.Garwan_User;
import dto.UserDTO;
import mappers.UserMaper;
import repo.UserRepositary;

@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepositary userRepo;
	
	public UserDTO registerNewUser(UserDTO user) {
		
		if (user == null || userRepo.findByEmail(user.getEmail()) == null) {
			Garwan_User newUser = UserMaper.toDTO(user);
			newUser = userRepo.save(newUser);
			logger.info("New user was registered into DB");
			
			return UserMaper.toDTO(newUser);
		}
		
		return null;
	}

	/**
	 * Get all users in DB
	 * 
	 * @return
	 */
	public List<UserDTO> getAllUsers() {
		List<UserDTO> list = new ArrayList<UserDTO>();
				
		for (Garwan_User user : userRepo.findAll()) {
			list.add(UserMaper.toDTO(user));
		}
		
		return list;
	}

}
