package com.ufund.api.ufundapi.persistence;

import com.ufund.api.ufundapi.model.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserFileDAO implements UserDAO{
    Map<Integer,User> users;
    private ObjectMapper objectMapper;
    private static int nextId;
    private String filename;

    public UserFileDAO(@Value("${Users.file}") String filename, ObjectMapper objectMapper) throws IOException 
    {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    private User[] getUserArray() {
        return getUserArray(null);
    }

    private User[] getUserArray(String containsText) { 
        ArrayList<User> UserArrayList = new ArrayList<>();

        for (User user : users.values()) {
            if (containsText == null || user.getUsername().contains(containsText)) {
                UserArrayList.add(user);
            }
        }

        User[] UserArray = new User[UserArrayList.size()];
        UserArrayList.toArray(UserArray);
        return UserArray;
    }


    private boolean save() throws IOException {
        User[] UserArray = getUserArray();
        objectMapper.writeValue(new File(filename),UserArray);
        return true;
    }

    private boolean load() throws IOException {
        users = new TreeMap<>();
        nextId = 0;

        User[] userArray = objectMapper.readValue(new File(filename),User[].class);

        for (User user : userArray) {
            users.put(user.getId(),user);
            if (user.getId() > nextId)
                nextId = user.getId();
        }
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public User[] findUser(String text) {
        synchronized(users) {
            return getUserArray(text);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public User getUser(int id) {
        synchronized(users) {
            if (users.containsKey(id))
                return users.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public User addUser(User user) throws IOException {
        synchronized(users) {
            User newUser = new User(nextId(),user.getUsername(),user.getPassword());
            users.put(newUser.getId(),newUser);
            save(); // may throw an IOException
            return newUser;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteUser(int id) throws IOException {
        synchronized(users) {
            if (users.containsKey(id)) {
                users.remove(id);
                return save();
            }
            else
                return false;
        }
    }
}
