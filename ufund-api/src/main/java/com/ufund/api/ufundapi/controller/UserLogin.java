package com.ufund.api.ufundapi.controller;

public class UserLogin {
    private String username;
    private String password;

    // Constructor
    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // Method to validate the login credentials
    public boolean authenticate(String enteredUsername, String enteredPassword) {
        // Check if the entered username and password match the stored credentials
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }

    // Getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setter methods (if needed)
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example usage
        UserLogin user = new UserLogin("exampleUser", "examplePassword");

        // Simulate user input (entered username and password)
        String enteredUsername = "exampleUser";
        String enteredPassword = "examplePassword";

        // Authenticate user
        if (user.authenticate(enteredUsername, enteredPassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}
