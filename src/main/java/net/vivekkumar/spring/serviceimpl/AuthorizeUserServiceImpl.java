package net.vivekkumar.spring.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.vivekkumar.spring.model.AuthorisedUser;
import net.vivekkumar.spring.repositories.AuthorizedUserRepository;
import net.vivekkumar.spring.service.AuthorizeUserService;

@Service
public class AuthorizeUserServiceImpl implements AuthorizeUserService {
	@Autowired
	private AuthorizedUserRepository authorizedUserRepository;

	@Transactional(readOnly = true)
	public AuthorisedUser checkAccess(String email, String password) {
		return authorizedUserRepository.findByEmailAndPassword(email, password);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AuthorisedUser registerUser(AuthorisedUser authorisedUser) {
		return authorizedUserRepository.save(authorisedUser);
	}
}
