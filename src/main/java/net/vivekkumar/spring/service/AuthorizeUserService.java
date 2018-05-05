package net.vivekkumar.spring.service;

import net.vivekkumar.spring.model.AuthorisedUser;

public interface AuthorizeUserService {

	public AuthorisedUser checkAccess(String email, String password);

	public AuthorisedUser registerUser(AuthorisedUser authorisedUser);
	
	public AuthorisedUser checkRegistered(String email);
	
	public AuthorisedUser activateUser(AuthorisedUser authorisedUser);
}
