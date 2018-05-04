package net.vivekkumar.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import net.vivekkumar.spring.model.AuthorisedUser;
import net.vivekkumar.spring.model.User;
import net.vivekkumar.spring.service.AuthorizeUserService;
import net.vivekkumar.spring.service.UsersService;

@RestController
public class AuthorisedUsersController {

	Logger LOG = LoggerFactory.getLogger(AuthorisedUsersController.class);

	@Autowired
	private AuthorizeUserService authorizeUserServiceImpl;

	@Autowired
	private UsersService usersServiceImpl;

	@GetMapping("/login")
	public AuthorisedUser checkAccess(@RequestHeader("email") String email,
			@RequestHeader("password") String password) {
		AuthorisedUser user = authorizeUserServiceImpl.checkAccess(email, password);
		if (user != null) {
			LOG.info("User {} logged in", email);
		}else{
			user = new AuthorisedUser();
			user.setHasError(true);
			user.setError("User Not Authorized");
		}
		return user;
	}

	@PostMapping("/Register_User")
	public AuthorisedUser registerUser(@RequestBody AuthorisedUser authorisedUser) {
		if (authorisedUser != null) {
			User user = usersServiceImpl.findUser(authorisedUser.getEmail());
			if (user != null) {
				authorisedUser.setUser(user);
				authorisedUser.setHasError(false);
				authorisedUser = authorizeUserServiceImpl.registerUser(authorisedUser);
				LOG.info("User {} registered", authorisedUser.getEmail());
			} else {
				authorisedUser.setHasError(true);
				authorisedUser.setError("You are not a Xpanxion Emplooyeee.");
			}
		}
		return authorisedUser;

	}

}