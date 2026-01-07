package menu;
import service.Session;
import menu.actions.*;

public class BaseAdminMenu extends AbstractMenu {

    public BaseAdminMenu(Session session) {
        actions.put(1, new LogoutAction(session));
        actions.put(2, new ManageDisputeAction());
        actions.put(3, new DeactivateAccountAction());
    }

    @Override
    public void show() {
        System.out.println("1. Logout");
        System.out.println("2. Manage Dispute");
        System.out.println("3. Deactivate Account");
    };
}

// public class BaseAdminMenu implements Menu {
//     @Override
//     public void show() {
//         System.out.println("1. Manage Dispute");
//         System.out.println("2. Deactivate Account");
//     };
// }