package net.vivekkumar.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.vivekkumar.spring.model.AuthorisedUser;
import net.vivekkumar.spring.model.User;
import net.vivekkumar.spring.service.UsersService;

@RestController
public class UsersController {

	Logger log = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private UsersService usersServiceImpl;

	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		if (user != null && !usersServiceImpl.validateUser(user)) {
			User usr = usersServiceImpl.findUserByEmailOrEmpId(user.getEmail(), user.getEmpId());
			if (usr != null) {
				user.setHasError(true);
				user.setError("User already exists");
			} else {
				user.setHasError(false);
				user = usersServiceImpl.addUser(user);
			}
		}
		return user;

	}

}