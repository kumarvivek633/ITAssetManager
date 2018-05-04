package net.vivekkumar.spring.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import net.vivekkumar.spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
	public User findByEmailOrEmpId(String email, Long empId);
	
	public User findByEmpId(Long empId);
}