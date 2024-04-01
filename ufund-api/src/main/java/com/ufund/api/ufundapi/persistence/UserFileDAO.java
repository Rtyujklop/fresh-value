package com.ufund.api.ufundapi.persistence;

import com.ufund.api.ufundapi.model.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserFileDAO implements UserDAO{
    Map<Integer,User> users;
    private ObjectMapper objectMapper;
    private int nextId;
    private String filename;

    public UserFileDAO(@Value("${Users.file}") String filename, ObjectMapper objectMapper) throws IOException 
    {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private synchronized int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    private User[] getuserArray() {
        return getuserArray(null);
    }

    private User[] getuserArray(String containsText) { 
        ArrayList<User> userArrayList = new ArrayList<>();

        for (User user : users.values()) {
            if (containsText == null || user.getUsername().contains(containsText)) {
                userArrayList.add(user);
            }
        }

        User[] userArray = new User[userArrayList.size()];
        userArrayList.toArray(userArray);
        return userArray;
    }


    private boolean save() throws IOException {
        User[] userArray = getuserArray();
        objectMapper.writeValue(new File(filename),userArray);
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
            return getuserArray(text);
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
