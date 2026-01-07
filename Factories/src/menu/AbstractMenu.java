package menu;
import java.util.Map;
import java.util.HashMap;
import menu.actions.MenuAction;

public abstract class AbstractMenu implements Menu {
    protected Map<Integer, MenuAction> actions = new HashMap<>(); //number choice, action

    @Override 
    public void handleSelection(int choice) {
        MenuAction action = actions.get(choice); 
        if (action != null) {
            action.execute();
        } else {
            System.out.println("Invalid choice. ");
        }
    }
}

        // while (true) {
        //     try {
        //         if (action != null) {
        //             action.execute();
        //         } else {
        //             System.out.println("Invalid choice. ");
        //         }
        //     } catch (Exception e) {
        //         System.out.println("Error occured: " + e.getMessage());
        //     }
        // }