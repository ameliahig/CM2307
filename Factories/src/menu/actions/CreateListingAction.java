package menu.actions;
import model.User;
import model.Homeowner;
import model.RoomListing;
import service.ManageListing;
import service.Session;
import java.time.LocalDate;
import java.util.*;

public class CreateListingAction implements MenuAction {
    private final Session session;
    private final ManageListing manageListing;
    private Scanner userEntry = new Scanner(System.in);

    public CreateListingAction(Session session, ManageListing manageListing) {
        this.session = session;
        this.manageListing = manageListing;
    }

    @Override
    public void execute() {
        System.out.println("Creating new listing...");

        User currentUser = session.getCurrentUser();

        if (currentUser instanceof Homeowner homeowner) {
            RoomListing listing = manageListing.createListing(homeowner);
            session.getListings().add(listing);
            manageListing.saveNew(listing);
            System.out.println("Listing successfully created. ");
        } else {
            System.out.println("Only homeowners can create listings. ");
        }
    }
}