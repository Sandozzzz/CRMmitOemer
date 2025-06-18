package com.winston.crm_mit_oemer.service;

import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.model.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

/**
 * Login Manager class is used to manage user authentification.
 * **/
public class LoginManager {

    private static final LoginManager instance = new LoginManager();
    private final UserManager userManager = new UserManager();
    private User loggedInUser;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        return instance;
    }

    public boolean logIn(UserDTO userDTO) throws SQLException {
        userManager.findByEmail(userDTO.getEmail()).ifPresent(user -> {
            if (BCrypt.checkpw(userDTO.getPassword(), user.getPassword())) {
                loggedInUser = user;
            }
        });
        return loggedInUser !=null;
    }

    public void logOut(){
        loggedInUser = null;
    }

    public User getCurrentUser(){
        return loggedInUser;
    }

    public boolean updatePassword() throws SQLException {
        System.out.println(loggedInUser.getPassword());
       return userManager.update(loggedInUser);
    }




}
