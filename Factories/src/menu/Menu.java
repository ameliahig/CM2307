package menu;
import menu.actions.*;

public interface Menu {
    void show();
    void handleSelection(int choice);
}