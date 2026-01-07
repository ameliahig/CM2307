package menu.actions;
import model.User;
import model.Homeowner;
import service.Session;

public class ManageBookingRequestsAction implements MenuAction {
    private final Session session;
    
    public ManageBookingRequestsAction(Session session) {
        this.session = session;
    }
    
    @Override
    public void execute() {
        System.out.println("Managing room booking requests...");
        // Call ManageBookingRequest
    }
}