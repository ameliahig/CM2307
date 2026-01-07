package factory; 
import model.User;
import model.Admin;

public class AdminFactory extends UserFactory {

    @Override
    protected User createUser(String username, String email, String password) {
        // admin-specific creation logic could go here
        return new Admin(username, email, password);
    }
}