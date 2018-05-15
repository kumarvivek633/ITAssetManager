/*
 *
 */
package net.vivekkumar.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.vivekkumar.spring.model.AuthorisedUser;

/**
 * The Interface AuthorizedUserRepository.
 */
public interface AuthorizedUserRepository extends JpaRepository<AuthorisedUser, String> {

    /**
     * Find by email.
     *
     * @param email
     *            the email
     * @return the authorised user
     */
    public AuthorisedUser findByEmail(String email);

    /**
     * Find by email and password.
     *
     * @param email
     *            the email
     * @param password
     *            the password
     * @return the authorised user
     */
    public AuthorisedUser findByEmailAndPassword(String email, String password);

}
