package menu.actions;
import model.User;
import model.Homeowner;
import model.RoomListing;
import service.ManageListing;
import service.Session;

public class EditListingAction implements MenuAction {
    private final Session session;
    private final ManageListing manageListing;

    public EditListingAction(Session session, ManageListing manageListing) {
        this.session = session;
        this.manageListing = manageListing;
    }

    @Override
    public void execute() {
        System.out.println("Editing listing...");

        User currentUser = session.getCurrentUser();

        if (currentUser instanceof Homeowner homeowner) {
            System.out.println("Feature not yet implemented. ");
            //1. Show owners listings (check they have listings available already)
            //2. Select listing by ID
            //3. Modify fields
            //4. Save via file manager
            
        } else {
            System.out.println("Only homeowners can edit listings. ");
        }

    }
}