package japapino.securityexample;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
//		auth.authenticationProvider(authenticationProvider) when using usernames and passwords from a database? 
			.withUser("stefan").password("test").roles("ADMIN").and()
			.withUser("test").password("testpass").roles("USER"); 
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity
			.authorizeRequests()
			//.andMatchers("**//rest/*)
			.anyRequest() //allow all requests
			.permitAll()
			.and().httpBasic();
		httpSecurity.csrf().disable(); 
		
	}

}
