package factory;
import model.User;
import model.Homeowner;

public class HomeownerFactory extends UserFactory {

    @Override
    protected User createUser(String username, String email, String password) {
        return new Homeowner(username, email, password);
    }
}