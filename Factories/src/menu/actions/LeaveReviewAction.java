package menu.actions;
import model.User;

public class LeaveReviewAction implements MenuAction {
    private final User user;

    public LeaveReviewAction(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        System.out.println("Leaving review...");
        // Call LeaveReview
    }
}