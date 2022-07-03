package ar.edu.unju.fi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ar.edu.unju.fi.service.imp.LoginUsuarioServiceImp;

@Configuration
public class WebSecurityConfiguration {

	String[] resources = new String[] { "/include/**", "/css/**", "/icons/**", "/images/**", "/js/**", "/layer/**",
	"/webjars/**" };
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/", "/home", "/ciudadano/nuevo","/ciudadano/guardar").permitAll().antMatchers(resources).permitAll().anyRequest()
		.authenticated().and().formLogin().loginPage("/login/ciudadano").permitAll().defaultSuccessUrl("/ciudadano/home/")
		.failureUrl("/login?error=true").usernameParameter("dni").passwordParameter("password").and().logout()
		.permitAll();

http.headers().frameOptions().sameOrigin();

return http.build();
		
	}
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		return bCryptPasswordEncoder;
	}
	
	@Autowired
	LoginUsuarioServiceImp userDetailsService;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
