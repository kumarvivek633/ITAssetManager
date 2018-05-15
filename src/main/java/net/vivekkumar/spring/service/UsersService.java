/*
 *
 */
package net.vivekkumar.spring.service;

import net.vivekkumar.spring.model.User;

/**
 * The Interface UsersService.
 */
public interface UsersService {

    /**
     * Adds the user.
     *
     * @param user
     *            the user
     * @return the user
     */
    public User addUser(User user);

    /**
     * Find user.
     *
     * @param email
     *            the email
     * @return the user
     */
    public User findUser(String email);

    /**
     * Find user by email or emp id.
     *
     * @param email
     *            the email
     * @param empId
     *            the emp id
     * @return the user
     */
    public User findUserByEmailOrEmpId(String email, Long empId);

    /**
     * Find user by emp id.
     *
     * @param empId
     *            the emp id
     * @return the user
     */
    public User findUserByEmpId(Long empId);

    /**
     * Validate user.
     *
     * @param user
     *            the user
     * @return true, if successful
     */
    public boolean validateUser(User user);
}
