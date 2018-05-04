package net.vivekkumar.spring.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.vivekkumar.spring.model.User;
import net.vivekkumar.spring.repositories.UserRepository;
import net.vivekkumar.spring.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public User findUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Transactional(readOnly = true)
	public User findUserByEmailOrEmpId(String email, Long empId) {
		return userRepository.findByEmailOrEmpId(email, empId);
	}

	public boolean validateUser(User user) {
		if (user.getEmail() == null || user.getEmail().equals("") || user.getEmpId() == null
				|| user.getFirstName() == null || user.getFirstName().equals("") || user.getLastName() == null
				|| user.getLastName().equals("")) {
			user.setHasError(true);
			user.setError("Please Fill all details!!");
		}
		return user.getHasError();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User addUser(User user) {
		user.setCreationDate(new Date());
		user = userRepository.save(user);
		return user;
	}
	
	@Transactional(readOnly = true)
	public User findUserByEmpId(Long empId) {
		return userRepository.findByEmpId(empId);
	}
}
