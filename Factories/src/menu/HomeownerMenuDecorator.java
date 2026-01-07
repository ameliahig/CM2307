package menu;
import model.User;
import service.ManageListing;
import service.Session;
import menu.actions.*;

public class HomeownerMenuDecorator extends MenuDecorator {

    public HomeownerMenuDecorator(Menu wrappedMenu, User user, Session session, ManageListing manageListing) {
        super(wrappedMenu);

        if (wrappedMenu instanceof AbstractMenu base) {
            base.actions.put(5, new CreateListingAction(session, manageListing));
            base.actions.put(6, new EditListingAction(session, manageListing));
            base.actions.put(7, new DeleteListingAction(session, manageListing));
            base.actions.put(8, new ManageBookingRequestsAction(session));
        }
    }

    @Override
    public void show() {
        super.show();

        System.out.println("5. Create Listing");
        System.out.println("6. Edit Listing");
        System.out.println("7. Delete Listing");
        System.out.println("8. Manage Booking Requests");
    }
}