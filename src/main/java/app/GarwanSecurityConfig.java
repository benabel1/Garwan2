package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class GarwanSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MyUserDetailsService userDetailsService;

	/**
	 * Override authenticationProvider by our authenticationProvider
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	/**
	 * Setup UserDetailsService and password encoder, NoOpPassword was chosen for now
	 * 
	 * @return
	 */
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

		authenticationProvider.setPasswordEncoder(passwordEncoder);
		authenticationProvider.setUserDetailsService(userDetailsService);

		return authenticationProvider;
	}

	/**
	 * Confgure HTTPSecurity, secure web call
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/**").permitAll()
				//allow all public API, swagger API and H2 embedded runtime database
				.antMatchers("/h2-console/**", "/public/**", "/v2/api-docs").permitAll()
				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("auth/orders/**").authenticated()
				.anyRequest().authenticated()
//				.and()
//				.formLogin()
//				.and()
//				.logout()
				.and()
				.httpBasic();

//		http.csrf().disable();

		
		//this section was found to allow H2 console during Security,
		//just copied
		http.csrf().ignoringAntMatchers("/h2-console/**");
		// this will allow frames with same origin which is much more safe
		http.headers().frameOptions().sameOrigin();

	}

	@Bean
	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}

}
