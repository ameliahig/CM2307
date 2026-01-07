package menu.actions;
import model.User;

public class ViewBookingsAction implements MenuAction {
    private final User user;

    public ViewBookingsAction(User user) {
        this.user = user;
    }
    
    @Override
    public void execute() {
        System.out.println("Displaying past, present and future bookings...");
        // Call ViewBookings
    }
}