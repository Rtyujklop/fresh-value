package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.User;

public class UserFileDAOTests {

    UserFileDAO mockUserFileDAO;
    User[] mockUsers;
    ObjectMapper mockObjectMapper;


    @BeforeEach
    public void setupUserFileDAO() throws IOException
    {
        mockObjectMapper = mock(ObjectMapper.class);
        
        mockUsers = new User[2];
        mockUsers[0] = new User(1, "test", "Password");
        mockUsers[1] = new User(2, "admin", "admin");

        when(mockObjectMapper
            .readValue(new File("example.txt"), User[].class))
                .thenReturn(mockUsers);
        mockUserFileDAO = new UserFileDAO("example.txt", mockObjectMapper);
    }

    @Test
    public void testGetUser()
    {
        User user = mockUserFileDAO.getUser(2);

        assertEquals(user, mockUsers[1]);
    }

    @Test
    public void testFindUsers()
    {   
        User[] users = mockUserFileDAO.findUser("test");

        assertEquals(users.length, 1, "Wrong amount of userss found");
        assertEquals(users[0], mockUsers[0], "Wrong user found");
    }

    @Test
    public void testAddUser()
    {
        User user = new User(3, "new", "bruno");

        User result = assertDoesNotThrow(() -> mockUserFileDAO.addUser(user), "Creating user threw exception");
        assertNotNull(result);

        User userToCheck = mockUserFileDAO.getUser(3);
        assertEquals(userToCheck.getId(), user.getId(), "user does not match");
    }

    @Test
    public void testDeleteUser()
    {
        boolean delete = assertDoesNotThrow(() -> mockUserFileDAO.deleteUser(1), "Deletion threw exception");
        assertEquals(delete, true, "Deletion failed");
        assertEquals(mockUserFileDAO.users.size(), 1);
    }

    @Test
    public void testUserNotFound()
    {
        User user = mockUserFileDAO.getUser(5);

        assertEquals(user, null);
    }

    public void testDeleteNotFound()
    {
        boolean delete = assertDoesNotThrow(() -> mockUserFileDAO.deleteUser(12), "Deletion threw exception");
        assertEquals(delete, false);
        assertEquals(mockUserFileDAO.users.size(), mockUsers.length);
    }
}
