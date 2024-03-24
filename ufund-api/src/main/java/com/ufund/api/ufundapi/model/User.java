package com.ufund.api.ufundapi.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("id") private int id;
    @JsonProperty("username") private String username;
    @JsonProperty("password") private String password;

    public User(@JsonProperty("id") int id, @JsonProperty("username") String username, @JsonProperty("password") String password)
    {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return this.username;
    }

    public int getId()
    {
        return this.id;
    }

    public String getPassword()
    {
        return this.password;
    }
}
