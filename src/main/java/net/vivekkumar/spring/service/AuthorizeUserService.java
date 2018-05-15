/*
 *
 */
package net.vivekkumar.spring.service;

import net.vivekkumar.spring.model.AuthorisedUser;

/**
 * The Interface AuthorizeUserService.
 */
public interface AuthorizeUserService {

    /**
     * Activate user.
     *
     * @param authorisedUser
     *            the authorised user
     * @return the authorised user
     */
    public AuthorisedUser activateUser(AuthorisedUser authorisedUser);

    /**
     * Check access.
     *
     * @param email
     *            the email
     * @param password
     *            the password
     * @return the authorised user
     */
    public AuthorisedUser checkAccess(String email, String password);

    /**
     * Check registered.
     *
     * @param email
     *            the email
     * @return the authorised user
     */
    public AuthorisedUser checkRegistered(String email);

    /**
     * Register user.
     *
     * @param authorisedUser
     *            the authorised user
     * @return the authorised user
     */
    public AuthorisedUser registerUser(AuthorisedUser authorisedUser);
}
