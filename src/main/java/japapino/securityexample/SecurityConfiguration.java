package japapino.securityexample;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
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
				// auth.authenticationProvider(authenticationProvider) when using usernames and
				// passwords from a database?
				.withUser("stefan").password("test").roles("ADMIN").and().withUser("test").password("testpass")
				.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		// custom filter
		httpSecurity.authorizeRequests() // all requests(URI
				.antMatchers("*/hello").hasRole("USER") // any request with "/hello" in the uri will only allow users with the role of USER.
				.and()
				.addFilterBefore(customFilter(), BasicAuthenticationFilter().class)
				.and().httpBasic();
		httpSecurity.csrf().disable();

		// httpSecurity
		// .authorizeRequests()
		// .anyRequest()
		// .permitAll()
		// .fullyAuthenticated()
		// .and().httpBasic();
		// httpSecurity.csrf().disable();

		// .antMatcher
		// httpSecurity
		// .authorizeRequests() //all requests(URIs)
		// .antMatchers("*/hello").hasRole("USER") //any request with "/hello" in the
		// uri will only allow users with the role of USER.
		// .anyRequest()
		// .fullyAuthenticated()
		// .and().httpBasic();
		// httpSecurity.csrf().disable();

	}

	@Bean
	public CustomFilter customFilter() {
		// TODO Auto-generated method stub
		return new CustomFilter();
	}

}
