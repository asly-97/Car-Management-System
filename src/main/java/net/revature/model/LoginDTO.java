package net.revature.model;

/* What is a DTO? Data Transfer Object. It's meant to model some data that doesn't pertain to a DB table
For instance, maybe we have login functionality that only takes a username/password
We want a user to be able to log in with ONLY their username/password, instead of an entire Employee object
NOTE: we never store DTOs in the DB - they're solely for DATA TRANSFER

Two main use cases:

1) When you don't want to send or use an entire object (we only need username/password to log in)
2) When you don't intend to store the incoming data in the DB (it's only for java logic) */
public class LoginDTO {

    private String username, password;


    //need a no args constructor for the javalin 6 deserialization
    //which is basically what helps us make Java <-> JSON conversions
    public LoginDTO() {
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}