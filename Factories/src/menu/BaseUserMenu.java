package menu;
import model.User;
import service.Session;
import menu.actions.*;

public class BaseUserMenu extends AbstractMenu {

    public BaseUserMenu(User user, Session session) {
        actions.put(1, new LogoutAction(session));
        actions.put(2, new ManageProfileAction(user));
        actions.put(3, new ViewBookingsAction(user));
        actions.put(4, new LeaveReviewAction(user));
    }

    @Override
    public void show() {
        System.out.println("1. Logout");
        System.out.println("2. Manage Profile");
        System.out.println("3. View Bookings");
        System.out.println("4. Leave Review");
    };
}

// public class BaseUserMenu implements Menu {
//     @Override
//     public void show() {
//         System.out.println("1. Manage Profile");
//         System.out.println("2. View Bookings");
//         System.out.println("3. Leave Review");
//     };
// }