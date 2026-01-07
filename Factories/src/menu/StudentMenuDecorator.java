package menu;
import model.User;
import menu.actions.*;

public class StudentMenuDecorator extends MenuDecorator {

    public StudentMenuDecorator(Menu wrappedMenu, User user) {
        super(wrappedMenu);

        if (wrappedMenu instanceof AbstractMenu base) {
            base.actions.put(5, new SearchRoomAction());
            base.actions.put(6, new RequestBookingAction(user));
        }
    }

    @Override
    public void show() {
        super.show();

        System.out.println("5. Search for Room");
        System.out.println("6. Request a Booking");
    }
}