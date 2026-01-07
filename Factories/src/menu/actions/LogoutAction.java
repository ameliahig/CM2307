package menu.actions;
import model.User;
import service.Session;

public class LogoutAction implements MenuAction {
    private final Session session;

    public LogoutAction(Session session) {
        this.session = session;
    }

    @Override
    public void execute() {
        System.out.println("Logging out...");
        session.logout();
    }
}