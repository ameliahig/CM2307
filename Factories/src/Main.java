import factory.*;
import model.*;
import service.*;
import menu.*;
import other.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        UserFactory studentFactory = new StudentFactory();
        UserFactory homeownerFactory = new HomeownerFactory();
        UserFactory adminFactory = new AdminFactory();

        List<User> users = new ArrayList<>();

        AccountLoader.loadAccounts("../resources/accounts.txt", studentFactory, homeownerFactory, adminFactory, users);

        LoginService loginServ = new LoginService(users);
        User loggedInUser = null;
        Scanner userEntry = new Scanner(System.in);  

        while (loggedInUser == null) {
            System.out.print("Username: ");
            String username = userEntry.nextLine();
            
            if (!loginServ.userExists(username.toLowerCase())) {
                System.out.println("User not found. ");
                System.out.println("Retry? Type R");
                System.out.println("Don't have an account? Create one. Type C");
                System.out.print("Response: ");

                String choice = userEntry.nextLine();
                if (choice.toLowerCase().charAt(0) == 'c') {
                    CreateNewUser creator = new CreateNewUser(users, studentFactory, homeownerFactory);
                    loggedInUser = creator.createUser();
                    System.out.println("New user " + loggedInUser.getUsername() + " created.");
                    break;
                }
                continue; // goes back to username entry
            }
            
            System.out.print("Password: ");
            String password = userEntry.nextLine(); 

            if (loginServ.validPassword(username.toLowerCase(), password)) {
                loggedInUser = loginServ.login(username.toLowerCase(), password);
            } else {
                System.out.println("Password invalid. Please try again. ");
            }
            

        }

        System.out.println("You are logged in! Welcome, " + loggedInUser.getUsername() + "!");

        // Setting up listings
        List<RoomListing> listings = new ArrayList<>();
        ListingFileManagement listingFileTranslator = new ListingFileManagement();
        // TODO: listingFM.loadAll(listings); (recommended later)
        ManageListing manageListing = new ManageListing(listingFileTranslator, listings);

        // Setting up menu
        Session session = new Session(loggedInUser, listings);
        Menu menu;

        if (loggedInUser instanceof Admin) {
            menu = new BaseAdminMenu(session);
        } else {
            menu = new BaseUserMenu(loggedInUser, session);

            if (loggedInUser instanceof Student) {
                menu = new StudentMenuDecorator(menu, loggedInUser);
            } else if (loggedInUser instanceof Homeowner) {
                menu = new HomeownerMenuDecorator(menu, loggedInUser, session, manageListing);
            }
        }

        int choice;
        while (session.isActive()) {
            System.out.println("");
            System.out.println("MENU");
            menu.show();
            System.out.print("Select an option: ");
            choice = userEntry.nextInt();
            menu.handleSelection(choice);
        }

        System.out.println("You have been logged out. ");
    
    }
}        