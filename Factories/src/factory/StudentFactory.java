package factory;
import model.User;
import model.Student;

public class StudentFactory extends UserFactory {

    @Override
    protected User createUser(String username, String email, String password) {
        return new Student(username, email, password);
    }
}