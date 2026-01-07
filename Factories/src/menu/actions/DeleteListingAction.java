package menu.actions;
import model.User;
import model.Homeowner;
import model.RoomListing;
import service.ManageListing;
import service.Session;

public class DeleteListingAction implements MenuAction {
    private final Session session;
    private final ManageListing manageListing;

    public DeleteListingAction(Session session, ManageListing manageListing) {
        this.session = session;
        this.manageListing = manageListing;
    }

    @Override
    public void execute() {
        System.out.println("Deleting listing...");
        
        User currentUser = session.getCurrentUser();

        if (currentUser instanceof Homeowner homeowner) {
            System.out.println("Feature not yet implemented. ");
            //1. Show owners listings (check they have listings available already)
            //2. Select listing by ID
            //3. Remove from list
            //4. Commit changes to file but deleting line
            
        } else {
            System.out.println("Only homeowners can delete listings. ");
        }
    }
}