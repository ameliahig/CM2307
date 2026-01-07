package menu.actions;
import model.User;

public class RequestBookingAction implements MenuAction {
    private final User user;

    public RequestBookingAction(User user) {
        this.user = user;
    }
    
    @Override
    public void execute() {
        System.out.println("Requesting booking for " + user.getUsername());
        // Call RequestBooking
    }
}