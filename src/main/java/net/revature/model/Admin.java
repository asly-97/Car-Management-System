package net.revature.model;

public class Admin extends Person{
    private String username, password;

    public Admin(String username, String password, int person_id, String first_name, String last_name) {
        super(person_id, first_name, last_name);
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
