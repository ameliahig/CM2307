package menu;
import menu.actions.*;

public abstract class MenuDecorator implements Menu {
    protected Menu wrappedMenu;

    protected MenuDecorator(Menu wrappedMenu) {
        this.wrappedMenu = wrappedMenu;
    }

    @Override
    public void show() {
        wrappedMenu.show();
    }

    @Override
    public void handleSelection(int choice) {
        wrappedMenu.handleSelection(choice);
    }
}