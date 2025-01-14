package com.ufund.api.ufundapi.controller;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.persistence.UserDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-Tier")
class UserControllerTests {

    @Mock
    private UserDAO mockUserDAO;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setupUserController() {
        mockUserDAO = mock(UserDAO.class);
        userController = new UserController(mockUserDAO);
    }

    @Test
    void testCreateUser() throws IOException {
        // Setup
        User testUser = new User(3, "test@test.org", "password");
        when(mockUserDAO.addUser(testUser)).thenReturn(testUser);

        // Invoke
        ResponseEntity<User> response = userController.addUser(testUser);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(testUser,response.getBody());
    }

    @Test
    void testCreateUserFail() throws IOException {
        // Setup
        User testUser = new User(1, "Test", "password");
        when(mockUserDAO.addUser(testUser)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = userController.addUser(testUser);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }
    
    @Test
    void testAddUserException() throws IOException {   
        // Setup
        User newUser = new User(1,"Test", "password");
        doThrow(new IOException()).when(mockUserDAO).addUser(newUser);

        // Invoke
        ResponseEntity<User> response = userController.addUser(newUser);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    void testSearchUser() throws IOException 
    { 
        // Setup
        String searchString = "Te";
        User[] users = new User[1];
        users[0] = new User(99, "Test", "Password");
        when(mockUserDAO.findUser(searchString)).thenReturn(users);

        // Invoke
        ResponseEntity<User[]> response = userController.searchUsers(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(users,response.getBody());
    }

    @Test
    void testGetUser() throws IOException
    {
        int id = 1;
        User user = new User(1, "Test", "password");
        when(mockUserDAO.getUser(id)).thenReturn(user);

        ResponseEntity<User> response = userController.getUser(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUserNotFound() throws IOException
    {
        int id = 2;
        when(mockUserDAO.getUser(id)).thenReturn(null);

        ResponseEntity<User> response = userController.getUser(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteUser() throws IOException
    {
        // Setup
        int userId = 1;
        User user = new User(userId, "Test", "Password");
        when(mockUserDAO.getUser(userId)).thenReturn(user); 
        when(mockUserDAO.deleteUser(userId)).thenReturn(true);

        // Invoke
        ResponseEntity<User> response = userController.deleteUser(userId);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteUserNotFound() throws IOException
    {
        // Setup
        int userId = 1;
        when(mockUserDAO.deleteUser(userId)).thenReturn(false);

        // Invoke
        ResponseEntity<User> response = userController.deleteUser(userId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode()); 
    }
    
    @Test
    void testDeleteUserException() throws IOException
    {
        // Setup
        int userId = 1;
        User user = new User(userId, "Test", "Password");
        when(mockUserDAO.getUser(userId)).thenReturn(user);
        when(mockUserDAO.deleteUser(userId)).thenThrow(new IOException());

        // Invoke
        ResponseEntity<User> response = userController.deleteUser(userId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


}
