package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.persistence.UserDAO;
import com.ufund.api.ufundapi.model.User;

@RestController
@RequestMapping("Users")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private UserDAO userDAO;


    public UserController(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        LOG.info("GET /Users/" + id);
        User user = userDAO.getUser(id);

        if (user != null)
            return new ResponseEntity<User>(user,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<User[]> searchUsers(@RequestParam String username) {
        LOG.info("GET /Users/?username=" + username);
        User[] users = userDAO.findUser(username);

        return new ResponseEntity<User[]>(users, HttpStatus.OK);
        
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        LOG.info("POST /Users " + user);
        try {
            User createdUser = userDAO.addUser(user);
            if (createdUser == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
        } catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        LOG.info("DELETE /Users/" + id);
        try {
            User userToDelete = userDAO.getUser(id);
            if (userToDelete != null)
            {
                userDAO.deleteUser(id);
                return new ResponseEntity<User>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



