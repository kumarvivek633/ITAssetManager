package net.vivekkumar.spring.service;

import java.util.List;

import net.vivekkumar.spring.model.User;

public interface UsersService {

	public User findUser(String email);

	public boolean validateUser(User user);

	public User addUser(User user);

	public User findUserByEmailOrEmpId(String email, Long empId);
	
	public User findUserByEmpId(Long empId) ;
}
