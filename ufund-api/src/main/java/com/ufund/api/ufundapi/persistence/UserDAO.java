package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.User;

public interface UserDAO {

    User getUser(int id);

    User addUser(User user) throws IOException;

    User[] findUser(String text);

    boolean deleteUser(int id) throws IOException;
}
