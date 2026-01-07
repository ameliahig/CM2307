package service;
import model.User;
import model.Admin;
import java.util.*;

public class LoginService {

    private List<User> users;

    public LoginService(List<User> users) {
        this.users = users;
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                return user;
            }
        } 
        return null;
    }

    public boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean validPassword(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.checkPassword(password);
            }
        } 
        return false;
    }
}
