package net.vivekkumar.spring.serviceimpl;


import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.vivekkumar.spring.model.AuthorisedUser;
import net.vivekkumar.spring.repositories.AuthorizedUserRepository;
import net.vivekkumar.spring.service.AuthorizeUserService;
import net.vivekkumar.spring.util.SendMail;

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
		AuthorisedUser auUser =  authorizedUserRepository.save(authorisedUser);
		if(auUser!=null){
			Long otp = (long) ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
			SendMail.sendMail(auUser.getEmail(), "Your OTP for activatinng the account is " + otp, "IT Manager Account Activation");
			auUser.setOtp(otp);
			auUser.setActivated(false);
		}
		return auUser;
	}
	
	@Transactional(readOnly = true)
	public AuthorisedUser checkRegistered(String email) {
		return authorizedUserRepository.findByEmail(email);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AuthorisedUser activateUser(AuthorisedUser authorisedUser) {
		authorisedUser.setActivated(true);
		authorisedUser.setOtp(0l);
		return authorizedUserRepository.save(authorisedUser);
	}
}
