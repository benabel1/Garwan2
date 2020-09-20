package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import data.Garwan_User;
import repo.UserRepositary;

@Service
public class MyUserDetailsService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Autowired
	private UserRepositary userRepository;
	
	@Autowired
	PasswordEncoder encoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Garwan_User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
//		return buildUserOldWay(user);
		return buildUser(user);
	}

	/**
	 * Creation user in runtime from GARWAN USER
	 * 
	 * @param user
	 * @return
	 */
	private UserDetails buildUser(Garwan_User user) {
		return User.
				withUsername(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRoles())
				.build();
	}
	
	/**
	 * Creation user in runtime from GARWAN USER
	 * 
	 * used with BCrypt password encoder
	 * 
	 * @param user
	 * @return
	 */
	@Deprecated
	private UserDetails buildUserOldWay(Garwan_User user) {
		return User.
				withUsername(user.getUsername())
				.password(encoder.encode(user.getPassword()))
				.roles(user.getRoles())
				.build();
	}
}