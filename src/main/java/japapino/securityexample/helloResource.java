package japapino.securityexample;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello")
public class helloResource {

//	@GetMapping
//	public String hello() {
//
//		return "Hello World!";
//	}

	//get the role of the user
	@GetMapping
	public String hello(@AuthenticationPrincipal final UserDetails userDetails) {
		String username = userDetails.getUsername();
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		authorities.forEach(System.out::println);
		return "hello world";

	}
}
