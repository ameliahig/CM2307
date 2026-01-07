package menu.actions;
import model.User;

public class ManageProfileAction implements MenuAction {
    private final User user;

    public ManageProfileAction(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        System.out.println("Managing profile for " + user.getUsername());
        // Call ProfileService
    }
}