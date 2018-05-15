/*
 *
 */
package net.vivekkumar.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.vivekkumar.spring.model.User;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find by email.
     *
     * @param email
     *            the email
     * @return the user
     */
    public User findByEmail(String email);

    /**
     * Find by email or emp id.
     *
     * @param email
     *            the email
     * @param empId
     *            the emp id
     * @return the user
     */
    public User findByEmailOrEmpId(String email, Long empId);

    /**
     * Find by emp id.
     *
     * @param empId
     *            the emp id
     * @return the user
     */
    public User findByEmpId(Long empId);
}