/*
 *
 */
package net.vivekkumar.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import net.vivekkumar.spring.model.AuthorisedUser;
import net.vivekkumar.spring.model.User;
import net.vivekkumar.spring.service.AuthorizeUserService;
import net.vivekkumar.spring.service.UsersService;

/**
 * The Class AuthorisedUsersController.
 */
@RestController
public class AuthorisedUsersController {

    /** The log. */
    Logger LOG = LoggerFactory.getLogger(AuthorisedUsersController.class);

    /** The authorize user service impl. */
    @Autowired
    private AuthorizeUserService authorizeUserServiceImpl;

    /** The users service impl. */
    @Autowired
    private UsersService usersServiceImpl;

    /**
     * Activate user.
     *
     * @param authorisedUser
     *            the authorised user
     * @return the authorised user
     */
    @PutMapping("/Activate_User")
    public AuthorisedUser activateUser(@RequestBody AuthorisedUser authorisedUser) {
        if (authorisedUser != null) {
            int otp = authorisedUser.getOtp().intValue();
            authorisedUser = authorizeUserServiceImpl.checkRegistered(authorisedUser.getEmail());
            if (authorisedUser != null && otp == authorisedUser.getOtp().intValue()) {
                authorisedUser = authorizeUserServiceImpl.activateUser(authorisedUser);
            } else {
                authorisedUser.setHasError(true);
                authorisedUser.setError("Otp Incorrect!");
            }
        }
        return authorisedUser;

    }

    /**
     * Check access.
     *
     * @param email
     *            the email
     * @param password
     *            the password
     * @return the authorised user
     */
    @GetMapping("/login")
    public AuthorisedUser checkAccess(@RequestHeader("email") String email,
            @RequestHeader("password") String password) {
        AuthorisedUser user = authorizeUserServiceImpl.checkAccess(email, password);
        if (user != null) {
            LOG.info("User {} logged in", email);
        } else {
            user = new AuthorisedUser();
            user.setHasError(true);
            user.setError("User Not Authorized");
        }
        return user;
    }

    /**
     * Register user.
     *
     * @param authorisedUser
     *            the authorised user
     * @return the authorised user
     */
    @PostMapping("/Register_User")
    public AuthorisedUser registerUser(@RequestBody AuthorisedUser authorisedUser) {
        if (authorisedUser != null) {
            AuthorisedUser authorisedUser2 = authorizeUserServiceImpl.checkRegistered(authorisedUser.getEmail());
            if (authorisedUser2 != null) {
                authorisedUser = authorisedUser2;
                if (authorisedUser.getActivated()) {
                    authorisedUser.setHasError(true);
                    authorisedUser.setError("You are already registered user. Please go to Login page to login!");
                }
            } else {
                User user = usersServiceImpl.findUser(authorisedUser.getEmail());
                if (user != null) {
                    authorisedUser.setUser(user);
                    authorisedUser.setHasError(false);
                    authorisedUser = authorizeUserServiceImpl.registerUser(authorisedUser);
                    LOG.info("User {} registered", authorisedUser.getEmail());
                } else {
                    authorisedUser.setHasError(true);
                    authorisedUser.setError("You are not a Xpanxion Employee.");
                }
            }
        }
        return authorisedUser;

    }

}