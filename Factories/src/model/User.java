package model;

public class User {
    // users can register for an account, manage profile, leave review, view booking (past, present and upcoming)
    protected String username;
    protected String email;
    protected String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    @Override
    public String toString() {
        return "username: " + this.username + ", email: " + this.email + ", password: " + this.password;
    }
}