package net.vivekkumar.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.vivekkumar.spring.model.AuthorisedUser;
import net.vivekkumar.spring.model.User;

public interface AuthorizedUserRepository extends JpaRepository<AuthorisedUser, String>{
	
	public AuthorisedUser findByEmailAndPassword(String email, String password);

}
