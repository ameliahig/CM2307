package factory; 
import model.User;

public abstract class UserFactory {

    public User registerUser(String username, String email, String password) {
        // shared logic could go here (validation, logging, etc.)
        return createUser(username, email, password);
    }

    protected abstract User createUser(String username, String email, String password);
}