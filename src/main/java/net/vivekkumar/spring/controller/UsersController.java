/*
 *
 */
package net.vivekkumar.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.vivekkumar.spring.model.User;
import net.vivekkumar.spring.service.UsersService;

/**
 * The Class UsersController.
 */
@RestController
public class UsersController {

    /** The log. */
    Logger log = LoggerFactory.getLogger(UsersController.class);

    /** The users service impl. */
    @Autowired
    private UsersService usersServiceImpl;

    /**
     * Adds the user.
     *
     * @param user
     *            the user
     * @return the user
     */
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        if (user != null && !usersServiceImpl.validateUser(user)) {
            User usr = usersServiceImpl.findUserByEmailOrEmpId(user.getEmail(), user.getEmpId());
            if (usr != null) {
                user.setHasError(true);
                user.setError("User already exists");
            } else {
                user.setHasError(false);
                user = usersServiceImpl.addUser(user);
            }
        }
        return user;

    }

}